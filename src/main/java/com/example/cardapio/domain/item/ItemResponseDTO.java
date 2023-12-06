package com.example.cardapio.domain.item;

public record ItemResponseDTO(String title, Integer price, String image, ItemType type, String description, String size) {
    public ItemResponseDTO(Item cardapio) {
        this(cardapio.getTitle(), cardapio.getPrice(), cardapio.getImage(), cardapio.getType(), cardapio.getDescription(), cardapio.getSize());
    }
}
