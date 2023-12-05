package com.example.cardapio.domain.cardapio;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.cardapio.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private User user;
    private String title;
    private String image;
    private Integer price;
    private CardapioType type;
    private String description;
    private String size;

    public Cardapio(CardapioRequestDTO data, UserDetails userDetails) {
        setData(data, userDetails);
    }

    public void setData(CardapioRequestDTO data, UserDetails userDetails) {
        this.title = data.title();
        this.image = data.image();
        this.price = data.price();
        this.type = data.type();
        this.description = data.description();
        this.size = data.size();
    }
}
