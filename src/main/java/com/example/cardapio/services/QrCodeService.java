package com.example.cardapio.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.nio.file.Path;

import com.google.zxing.common.BitMatrix;

@Service
public class QrCodeService {
    public BufferedImage createImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }

        return bufferedImage;
    }

    public void saveImage(BufferedImage image, String filePath) throws IOException {
        File qrCodeFile = new File(filePath);
        Files.createDirectories(Path.of(qrCodeFile.getParent()));

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            Files.write(qrCodeFile.toPath(), baos.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}
