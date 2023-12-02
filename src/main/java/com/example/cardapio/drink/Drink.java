package com.example.cardapio.drink;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "drink")
@Entity(name = "drink")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String size;
    private String image;
    private int price;

    public Drink(DrinkResponseDTO data) {
        setData(data);
    }

    public void setData(DrinkResponseDTO data) {
        this.title = data.title();
        this.size = data.size();
        this.image = data.image();
        this.price = data.price();
    }
}
