package com.example.cardapio.food;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "foods")
@Entity(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private Integer price;

    public Food(FoodResponseDTO data) {
        setData(data);
    }

    public void setData(FoodResponseDTO data) {
        this.title = data.title();
        this.image = data.image();
        this.price = data.price();
    }
}
