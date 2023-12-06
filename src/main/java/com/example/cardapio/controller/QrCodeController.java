package com.example.cardapio.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.qrcode.QrCode;
import com.example.cardapio.domain.qrcode.QrCodeResponseDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.repositories.QrCodeRepository;
import com.example.cardapio.services.QrCodeService;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @Autowired
    private QrCodeRepository repository;

    @Autowired
    private QrCodeService qrCodeService;

    @Value("${qrcode.directory}")
    private String qrCodeDirectory;

    @Value("${url.base}")
    private String url_base;

    @GetMapping
    public ResponseEntity<QrCodeResponseDTO> generate(@AuthenticationPrincipal User user) {
        QrCode qrCode = repository.findByCompanyUuid(user.getCompany().getUuid());
        
        if(qrCode == null) {
            String image_name = UUID.randomUUID().toString() + ".png";
            String url = url_base + "?id=" + user.getUuid();
            
            qrCodeService.generateQrCode(url, qrCodeDirectory + image_name);

            qrCode = new QrCode(user, image_name);
            repository.save(qrCode);
        }

        return ResponseEntity.ok().body(new QrCodeResponseDTO(url_base, qrCode));
    }
}
