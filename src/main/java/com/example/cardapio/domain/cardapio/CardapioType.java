package com.example.cardapio.domain.cardapio;

public enum CardapioType {
    COMIDA("comida"),
    BEBIDA("bebida");

    private String tipo;

    CardapioType(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
