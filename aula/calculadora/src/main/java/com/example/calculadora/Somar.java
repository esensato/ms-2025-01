package com.example.calculadora;

public class Somar implements Operacao {

    @Override
    public float executar(float n1, float n2) {
        return n1 + n2;
    }

}
