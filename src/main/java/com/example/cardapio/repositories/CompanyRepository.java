package com.example.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.domain.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByUuid(String uuid);
} 
    