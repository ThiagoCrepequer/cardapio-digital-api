package com.example.cardapio.domain.item;

public record ItemResponseDTO(String uuid, String title, Integer price, String image, ItemType type, String description, String size) {
    public ItemResponseDTO(Item item) {
        this(item.getUuid(), item.getTitle(), item.getPrice(), item.getImage(), item.getType(), item.getDescription(), item.getSize());
    }
}
