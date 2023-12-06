package com.example.cardapio.domain.item;


import jakarta.validation.constraints.NotBlank;

public record ItemRequestDTO(
    @NotBlank(message = "O campo 'title' é obrigatório") String title,
    @NotBlank(message = "O campo 'price' é obrigatório") Integer price,
    @NotBlank(message = "O campo 'image' é obrigatório") String image,
    @NotBlank(message = "O campo 'type' é obrigatório") ItemType type,
    @NotBlank(message = "O campo 'description' é obrigatório") String description,
    @NotBlank(message = "O campo 'size' é obrigatório") String size
) {}
