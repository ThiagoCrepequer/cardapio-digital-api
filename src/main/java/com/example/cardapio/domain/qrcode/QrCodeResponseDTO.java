package com.example.cardapio.domain.qrcode;

import org.springframework.beans.factory.annotation.Value;

public record QrCodeResponseDTO(String url) {
    public QrCodeResponseDTO(@Value("${url.base}") String url_base, QrCode qrCode) {
        this(url_base + qrCode.getUrl());
    }
}
