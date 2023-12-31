package com.example.cardapio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cardapio.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCompanyUuid(String companyUuid);
    Item findByUuid(String uuid);
    void deleteByUuid(String uuid);
} 
