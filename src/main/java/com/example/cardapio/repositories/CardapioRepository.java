package com.example.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.domain.cardapio.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {} 
