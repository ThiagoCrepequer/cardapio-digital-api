package com.example.cardapio.domain.cardapio;


import jakarta.validation.constraints.NotBlank;

public record CardapioRequestDTO(
    @NotBlank(message = "O campo 'title' é obrigatório") String title,
    @NotBlank(message = "O campo 'price' é obrigatório") Integer price,
    @NotBlank(message = "O campo 'image' é obrigatório") String image,
    @NotBlank(message = "O campo 'type' é obrigatório") CardapioType type,
    @NotBlank(message = "O campo 'description' é obrigatório") String description,
    @NotBlank(message = "O campo 'size' é obrigatório") String size
) {}
