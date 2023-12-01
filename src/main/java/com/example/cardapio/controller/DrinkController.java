package com.example.cardapio.controller;

import com.example.cardapio.drink.Drink;
import com.example.cardapio.drink.DrinkResponseDTO;
import com.example.cardapio.drink.DrinkRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    private DrinkRespository repository;

    @GetMapping
    public List<DrinkResponseDTO> getAll() {
        List<DrinkResponseDTO> drinkList =  repository
                .findAll()
                .stream()
                .map(DrinkResponseDTO::new)
                .toList();

        return drinkList;
    }

    @PostMapping
    public Drink saveDrink(@RequestBody DrinkResponseDTO data) {
        Drink drink = new Drink(data);
        repository.save(drink);
        return drink;
    }
}
