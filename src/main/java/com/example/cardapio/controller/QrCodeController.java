package com.example.cardapio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.qrcode.QrCode;
import com.example.cardapio.domain.qrcode.QrCodeResponseDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.repositories.QrCodeRepository;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @Autowired
    private QrCodeRepository repository;

    @GetMapping
    public ResponseEntity<QrCodeResponseDTO> generate(@AuthenticationPrincipal User user) {
        QrCode qrCode = repository.findByUserId(user.getId());
        
        if(qrCode == null) {
            qrCode = new QrCode(user);
            repository.save(qrCode);
        }

        return ResponseEntity.ok().body(new QrCodeResponseDTO(qrCode));
    }
}
