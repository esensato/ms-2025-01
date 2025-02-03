package com.example.calculadora;

import org.springframework.stereotype.Service;

@Service
public class Subtrair implements Operacao {

    @Override
    public float executar(float n1, float n2) {
        return n1 - n2;
    }

}
