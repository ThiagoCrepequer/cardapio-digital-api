package com.example.cardapio.domain.qrcode;

public record QrCodeResponseDTO(String url) {
    public QrCodeResponseDTO(QrCode qrCode) {
        this(qrCode.getUrl());
    }
}
