package com.example.cardapio.domain.company;

public record CompanyRequestDTO(
    String name,
    String url_logo,
    String description,
    String phone,
    String email,
    String address,
    String city,
    String state,
    String cep,
    String country,
    String cnpj
) {}
