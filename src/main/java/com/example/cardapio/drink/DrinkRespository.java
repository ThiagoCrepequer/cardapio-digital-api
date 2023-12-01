package com.example.cardapio.drink;

import com.example.cardapio.drink.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRespository extends JpaRepository<Drink, Long> {}
