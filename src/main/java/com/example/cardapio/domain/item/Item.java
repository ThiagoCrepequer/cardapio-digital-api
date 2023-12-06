package com.example.cardapio.domain.item;

import com.example.cardapio.domain.company.Company;
import com.example.cardapio.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;
    private String title;
    private String image;
    private Integer price;
    private ItemType type;
    private String description;
    private String size;

    public Item(ItemRequestDTO data, User user) {
        setData(data, user);
    }

    public void setData(ItemRequestDTO data, User user) {
        this.company = user.getCompany();
        this.title = data.title();
        this.image = data.image();
        this.price = data.price();
        this.type = data.type();
        this.description = data.description();
        this.size = data.size();
    }
}
