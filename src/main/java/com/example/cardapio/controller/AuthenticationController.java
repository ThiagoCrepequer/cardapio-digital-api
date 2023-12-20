package com.example.cardapio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.company.Company;
import com.example.cardapio.domain.user.AuthenticationRequestDTO;
import com.example.cardapio.domain.user.LoginResponseDTO;
import com.example.cardapio.domain.user.RegisterDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.exceptions.UserNotFoundException;
import com.example.cardapio.infra.secutiry.TokenService;
import com.example.cardapio.repositories.CompanyRepository;
import com.example.cardapio.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TokenService tokenService;

    private HttpHeaders headers = new HttpHeaders();


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationRequestDTO data) {
        var token = this.getToken(data);
        this.headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(null, this.headers, HttpStatus.OK);
        
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Company company = companyRepository.findByUuid(data.company_id());
        User newUser = new User(data.email(), encryptedPassword, data.role(), company);
        this.repository.save(newUser);

        var auth = new AuthenticationRequestDTO(data.email(), data.password());
        var token = this.getToken(auth);

        this.headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(null, this.headers, HttpStatus.CREATED);
    }

    private String getToken(AuthenticationRequestDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            
            if (auth == null || !auth.isAuthenticated()) {
                throw new UserNotFoundException("Usuário não encontrado ou credenciais inválidas");
            }

            return tokenService.generateToken((User) auth.getPrincipal());
        } catch (Exception e) {
            throw e;
        }
    }
}

