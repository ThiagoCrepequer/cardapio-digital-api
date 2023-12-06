package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.item.Item;
import com.example.cardapio.domain.item.ItemRequestDTO;
import com.example.cardapio.domain.item.ItemResponseDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.repositories.CardapioRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    @Autowired
    private CardapioRepository repository;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAll(@AuthenticationPrincipal User user) {
        List<ItemResponseDTO> cardapioList = repository
                .findByUserId(user.getId())
                .stream()
                .map(ItemResponseDTO::new)
                .toList();

        return ResponseEntity.ok().body(cardapioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getCardapio(@PathVariable Long id) {
        Item cardapio = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(cardapio);
    }

    @PostMapping
    public ResponseEntity<Item> saveCardapio(@RequestBody ItemRequestDTO data, @AuthenticationPrincipal User user) {
        Item cardapio = new Item(data, user);
        repository.save(cardapio);
        return ResponseEntity.status(201).body(cardapio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCardapio(@PathVariable Long id, @RequestBody ItemRequestDTO data, @AuthenticationPrincipal User user) {
        Item cardapio = repository.findById(id).get();
        cardapio.setData(data, user);
        repository.save(cardapio);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
