package com.example.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.domain.qrcode.QrCode;

public interface QrCodeRepository extends JpaRepository<QrCode, Long> {
    QrCode findByUserId(Long userId);
}
