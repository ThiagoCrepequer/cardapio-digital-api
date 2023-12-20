package com.example.cardapio.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {
    private String message;
    private int code;
}
