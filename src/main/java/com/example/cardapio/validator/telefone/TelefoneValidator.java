package com.example.cardapio.validator.telefone;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    private static final List<Integer> CODIGOS_DDD = Arrays.asList(
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            21, 22, 24, 27, 28, 31, 32, 33, 34,
            35, 37, 38, 41, 42, 43, 44, 45, 46,
            47, 48, 49, 51, 53, 54, 55, 61, 62,
            64, 63, 65, 66, 67, 68, 69, 71, 73,
            74, 75, 77, 79, 81, 82, 83, 84, 85,
            86, 87, 88, 89, 91, 92, 93, 94, 95,
            96, 97, 98, 99
        );

    @Override
    public void initialize(Telefone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null) {
            return false;
        }

        String digitsOnly = telefone.replaceAll("\\D", "");
        
        return isValidPhoneNumber(digitsOnly);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 || !phoneNumber.matches("\\d+")) {
            return false;
        }
    
        int ddd = Integer.parseInt(phoneNumber.substring(0, 2));
        return CODIGOS_DDD.contains(ddd);
    }
}

