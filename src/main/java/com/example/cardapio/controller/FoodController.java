package com.example.cardapio.controller;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @GetMapping
    public List<FoodResponseDTO> getAll() {
        List<FoodResponseDTO> foodList = repository
                .findAll()
                .stream()
                .map(FoodResponseDTO::new)
                .toList();

        return foodList;
    }


    @PostMapping
    public Food salveFood(@RequestBody FoodResponseDTO data) {
        Food foodData = new Food(data);
        repository.save(foodData);
        return foodData;
    }

    @PutMapping("/{id}")
    public Food updateFood(@PathVariable Long id, @RequestBody FoodResponseDTO data) {
        Food foodData = repository.findById(id).orElseThrow();
        // TODO: melhorar a forma de atualizar
        foodData.setTitle(data.title());
        foodData.setImage(data.image());
        foodData.setPrice(data.price());
        repository.save(foodData);

        return foodData;
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public FoodResponseDTO getFood(@PathVariable Long id) {
        Food food = repository.findById(id).orElseThrow();
        return new FoodResponseDTO(food);
    }
}
