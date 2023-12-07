package com.example.cardapio.domain.company;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CNPJ;

import com.example.cardapio.domain.item.Item;
import com.example.cardapio.domain.qrcode.QrCode;
import com.example.cardapio.domain.user.User;
import com.example.cardapio.validator.telefone.Telefone;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "company")
@Entity(name = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Item> cardapios = new ArrayList<Item>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> user = new ArrayList<User>();
    
    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
    private QrCode qrcode;
    
    private String name;
    private String url_logo;
    private String description;
    @Telefone private String phone;
    @Email private String email;
    @CNPJ private String cnpj;
    private String address;
    private String city;
    private String state;
    private String cep;
    private String country;

    @PrePersist
    private void prePersist() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }

    public Company(CompanyRequestDTO data) {
        setData(data);
    }

    public void setData(CompanyRequestDTO data) {
        this.name = data.name();
        this.url_logo = data.url_logo();
        this.description = data.description();
        this.phone = data.phone();
        this.email = data.email();
        this.address = data.address();
        this.city = data.city();
        this.state = data.state();
        this.cep = data.cep();
        this.country = data.country();
        this.cnpj = data.cnpj();
    }
}
