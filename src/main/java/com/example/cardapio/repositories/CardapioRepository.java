package com.example.cardapio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.domain.item.Item;

public interface CardapioRepository extends JpaRepository<Item, Long> {
    List<Item> findByUserUuid(String userUuid);
} 
