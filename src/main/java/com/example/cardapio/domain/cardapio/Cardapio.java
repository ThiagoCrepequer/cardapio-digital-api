package com.example.cardapio.domain.cardapio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cardapio")
@Entity(name = "cardapio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private Integer price;
    private CardapioType type;
    private String description;
    private String size;

    public Cardapio(CardapioRequestDTO data) {
        setData(data);
    }

    public void setData(CardapioRequestDTO data) {
        this.title = data.title();
        this.image = data.image();
        this.price = data.price();
        this.type = data.type();
        this.description = data.description();
        this.size = data.size();
    }
}
