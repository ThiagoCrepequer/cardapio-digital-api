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
        return repository
                .findAll()
                .stream()
                .map(DrinkResponseDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DrinkResponseDTO getDrink(@PathVariable Long id) {
        Drink drink = repository.findById(id).orElseThrow();
        return new DrinkResponseDTO(drink);
    }

    @PostMapping
    public Drink saveDrink(@RequestBody DrinkResponseDTO data) {
        Drink drink = new Drink(data);
        repository.save(drink);
        return drink;
    }

    @PutMapping("/{id}")
    public Drink updateDrink(@PathVariable Long id, @RequestAttribute DrinkResponseDTO data) {
        Drink drink = repository.findById(id).get();
        drink.setData(data);
        repository.save(drink);
        return drink;
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
