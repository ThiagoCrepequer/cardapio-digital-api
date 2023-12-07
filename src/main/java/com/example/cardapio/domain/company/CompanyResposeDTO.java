package com.example.cardapio.domain.company;

public record CompanyResposeDTO(
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
) {
    public CompanyResposeDTO(Company company) {
        this(
            company.getName(), 
            company.getUrl_logo(), 
            company.getDescription(), 
            company.getPhone(), 
            company.getEmail(), 
            company.getAddress(), 
            company.getCity(), 
            company.getState(), 
            company.getCep(), 
            company.getCountry(),
            company.getCnpj()
        );
    }    
}
