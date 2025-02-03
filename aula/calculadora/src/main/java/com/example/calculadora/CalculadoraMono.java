package com.example.calculadora;

public class CalculadoraMono {

    public static void main(String[] args) {

        if (args[0].equals("somar")) {
            System.out.println(Float.parseFloat(args[1]) + Float.parseFloat(args[2]));
        } else if (args[0].equals("multiplicar")) {
            System.out.println(Float.parseFloat(args[1]) * Float.parseFloat(args[2]));
        }
    }

}
