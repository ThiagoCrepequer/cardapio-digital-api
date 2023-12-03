package com.example.cardapio.domain.cardapio;

public record CardapioResponseDTO(String title, Integer price, String image, CardapioType type, String description, String size) {
    public CardapioResponseDTO(Cardapio cardapio) {
        this(cardapio.getTitle(), cardapio.getPrice(), cardapio.getImage(), cardapio.getType(), cardapio.getDescription(), cardapio.getSize());
    }
}
