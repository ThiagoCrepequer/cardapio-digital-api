package com.example.cardapio.domain.user;

public record RegisterDTO(String name, String email, String password, UserRole role, String company_id) {}
