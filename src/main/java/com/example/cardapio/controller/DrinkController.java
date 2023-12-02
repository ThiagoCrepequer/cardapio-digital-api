package com.example.cardapio.controller;

import com.example.cardapio.drink.Drink;
import com.example.cardapio.drink.DrinkResponseDTO;
import com.example.cardapio.drink.DrinkRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    private DrinkRespository repository;


    @GetMapping
    public ResponseEntity<List<DrinkResponseDTO>> getAll() {
        List<DrinkResponseDTO> drinkList = repository
                .findAll()
                .stream()
                .map(DrinkResponseDTO::new)
                .toList();

        return ResponseEntity.ok().body(drinkList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable Long id) {
        Drink drink = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(drink);
    }

    @PostMapping
    public ResponseEntity<Drink> saveDrink(@RequestBody DrinkResponseDTO data) {
        Drink drink = new Drink(data);
        repository.save(drink);
        return ResponseEntity.status(201).body(drink);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDrink(@PathVariable Long id, @RequestBody DrinkResponseDTO data) {
        Drink drink = repository.findById(id).get();
        drink.setData(data);
        repository.save(drink);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
