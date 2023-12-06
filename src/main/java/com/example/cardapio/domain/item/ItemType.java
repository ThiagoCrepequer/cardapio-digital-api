package com.example.cardapio.domain.item;

public enum ItemType {
    COMIDA("comida"),
    BEBIDA("bebida");

    private String tipo;

    ItemType(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
