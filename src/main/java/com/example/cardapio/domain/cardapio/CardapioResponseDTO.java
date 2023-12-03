package com.example.cardapio.domain.cardapio;

public record CardapioResponseDTO(String title, Integer price, String image, String type, String description, String size) {
    public CardapioResponseDTO(Cardapio cardapio) {
        this(cardapio.getTitle(), cardapio.getPrice(), cardapio.getImage(), cardapio.getType(), cardapio.getDescription(), cardapio.getSize());
    }
}
