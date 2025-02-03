package com.example.calculadora;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalculadoraSpring {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Operacao op1 = (Operacao) ctx.getBean(args[0]);

        System.out.println(op1.executar(Float.parseFloat(args[1]), Float.parseFloat(args[2])));

        // exemplo de injeção de dependência

        System.out.println("-- Resultado Calculadora --");
        Calculadora calc = (Calculadora) ctx.getBean("calculadora");
        System.out.println(calc.executar(Float.parseFloat(args[1]), Float.parseFloat(args[2])));
        System.out.println(calc.executar());

        System.out.println("-- Autowired --");
        ApplicationContext ctx2 = new ClassPathXmlApplicationContext("beans-autowired.xml");
        CalculadoraAutoWired calcAutoWired = (CalculadoraAutoWired) ctx2.getBean("calculadora");
        System.out.println(calcAutoWired.executar(Float.parseFloat(args[1]), Float.parseFloat(args[2])));
    }
}
