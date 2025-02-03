package com.example.calculadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("calculadora")
public class CalculadoraAutoWired {

    // Injeção de dependência por AutoWired (vide beans-autowired.xml)
    @Autowired
    @Qualifier("subtrair")
    private Operacao operacao;

    public float executar(float n1, float n2) {
        return operacao.executar(n1, n2);
    }

}
