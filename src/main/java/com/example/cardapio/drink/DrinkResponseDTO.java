package com.example.cardapio.drink;

public record DrinkResponseDTO(Long id, String title, String size, String image, int discountPercentage, int price) {
    public DrinkResponseDTO(Drink drink) {this(drink.getId(), drink.getTitle(), drink.getSize(), drink.getImage(), drink.getDiscountPercentage(), drink.getPrice());}
}
