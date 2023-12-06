package com.example.cardapio.domain.company;

import jakarta.validation.constraints.NotBlank;

public record CompanyRequestDTO(
    @NotBlank(message = "O campo 'nome' é obrigatório") String name,
    @NotBlank(message = "O campo 'logo' é obrigatório") String url_logo,
    @NotBlank(message = "O campo 'descrição' é obrigatório") String description,
    @NotBlank(message = "O campo 'telefone' é obrigatório") String phone,
    @NotBlank(message = "O campo 'email' é obrigatório") String email,
    @NotBlank(message = "O campo 'endereço' é obrigatório") String address,
    @NotBlank(message = "O campo 'cidade' é obrigatório") String city,
    @NotBlank(message = "O campo 'estado' é obrigatório") String state,
    @NotBlank(message = "O campo 'CEP' é obrigatório") String cep,
    @NotBlank(message = "O campo 'país' é obrigatório") String country
) {}
