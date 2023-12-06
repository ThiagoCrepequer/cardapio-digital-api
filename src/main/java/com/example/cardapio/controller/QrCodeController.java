package com.example.cardapio.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {
    @Autowired
    private QrCodeRepository repository;

    @Autowired
    private QrCodeService service;

    @Value("${qrcode.directory}")
    private String qrCodeDirectory;

    @Value("${url.base}")
    private String url_base;

    @GetMapping
    public ResponseEntity<QrCodeResponseDTO> generate(@AuthenticationPrincipal User user) {
        QrCode qrCode = repository.findByUserId(user.getId());
        
        if(qrCode == null) {
            String image_name = UUID.randomUUID().toString() + ".png";

            try {
                BitMatrix bitMatrix = new MultiFormatWriter().encode(url_base + "?id=" + user.getUuid(), BarcodeFormat.QR_CODE, 250, 250);
                BufferedImage image = service.createImage(bitMatrix);
                service.saveImage(image, qrCodeDirectory + "/" + image_name);
            } catch (WriterException | IOException e) {
                throw new RuntimeException("Erro ao gerar o QR code", e);
            }

            qrCode = new QrCode(user, image_name);
            repository.save(qrCode);
        }

        return ResponseEntity.ok().body(new QrCodeResponseDTO(url_base, qrCode));
    }
}
