package com.example.cardapio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cardapio.domain.item.Item;
import com.example.cardapio.domain.item.ItemRequestDTO;
import com.example.cardapio.domain.item.ItemResponseDTO;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.repositories.ItemRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cardapio")
public class ItemController {
    @Autowired
    private ItemRepository repository;

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAll(@RequestParam(required = false) String id, @AuthenticationPrincipal User user) {   
        if (id == null && user == null) {
            return ResponseEntity.badRequest().build();
        }
        
        List<ItemResponseDTO> cardapioList = repository
                .findByCompanyUuid(user != null ? user.getCompany().getUuid() : id)
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
        return ResponseEntity.ok().body(cardapioList);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Item> getCardapio(@PathVariable String uuid) {
        Item cardapio = repository.findByUuid(uuid);
        return ResponseEntity.ok().body(cardapio);
    }

    @PostMapping
    public ResponseEntity<Item> saveCardapio(@RequestBody ItemRequestDTO data, @AuthenticationPrincipal User user) {
        Item cardapio = new Item(data, user);
        repository.save(cardapio);
        return ResponseEntity.status(201).body(cardapio);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Void> updateCardapio(@PathVariable String uuid, @RequestBody ItemRequestDTO data, @AuthenticationPrincipal User user) {
        Item cardapio = repository.findByUuid(uuid);
        cardapio.setData(data, user);
        repository.save(cardapio);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable String uuid) {
        repository.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
    