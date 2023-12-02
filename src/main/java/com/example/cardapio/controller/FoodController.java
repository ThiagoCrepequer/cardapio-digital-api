package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getAll() {
        List<FoodResponseDTO> foodList = repository
                .findAll()
                .stream()
                .map(FoodResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(foodList);
    }

    @PostMapping
    public ResponseEntity<Food> salveFood(@RequestBody FoodResponseDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
        return ResponseEntity.status(201).body(foodData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody FoodResponseDTO data) {
        Food foodData = repository.findById(id).orElseThrow();
        foodData.setData(data);
        repository.save(foodData);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getFood(@PathVariable Long id) {
        Food food = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(new FoodResponseDTO(food));
    }
}
