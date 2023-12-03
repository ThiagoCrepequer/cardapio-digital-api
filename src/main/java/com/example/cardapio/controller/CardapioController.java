package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.cardapio.CardapioResponseDTO;
import com.example.cardapio.repositories.CardapioRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cardapio.domain.cardapio.Cardapio;
import com.example.cardapio.domain.cardapio.CardapioRequestDTO;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    @Autowired
    private CardapioRepository repository;

    @GetMapping
    public ResponseEntity<List<CardapioResponseDTO>> getAll() {
        List<CardapioResponseDTO> cardapioList = repository
                .findAll()
                .stream()
                .map(CardapioResponseDTO::new)
                .toList();

        return ResponseEntity.ok().body(cardapioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> getCardapio(@PathVariable Long id) {
        Cardapio cardapio = repository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(cardapio);
    }

    @PostMapping
    public ResponseEntity<Cardapio> saveCardapio(@RequestBody CardapioRequestDTO data) {
        Cardapio cardapio = new Cardapio(data);
        repository.save(cardapio);
        return ResponseEntity.status( 201).body(cardapio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCardapio(@PathVariable Long id, @RequestBody CardapioRequestDTO data) {
        Cardapio cardapio = repository.findById(id).get();
        cardapio.setData(data);
        repository.save(cardapio);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
