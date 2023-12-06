package com.example.cardapio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.company.Company;
import com.example.cardapio.domain.company.CompanyRequestDTO;
import com.example.cardapio.domain.company.CompanyResposeDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.repositories.CompanyRepository;
import com.example.cardapio.repositories.UserRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{uuid}")
    public Company getCompany(String uuid) {
        Company company = repository.findByUuid(uuid);
        return company;
    }

    @PostMapping
    public ResponseEntity<CompanyResposeDTO> saveCompany(@RequestBody CompanyRequestDTO data, @AuthenticationPrincipal User user) {
        if(user.getCompany() != null) {
            return ResponseEntity.badRequest().build();
        }
        
        Company company = new Company(data);
        repository.save(company);

        user.setCompany(company);
        userRepository.save(user);

        return ResponseEntity.status(201).body(new CompanyResposeDTO(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCompany(Long id, CompanyRequestDTO data) {
        Company company = repository.findById(id).get();
        company.setData(data);
        repository.save(company);
        return ResponseEntity.ok().build();
    }
}
