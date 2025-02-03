package com.example.calculadora;

public class Calculadora {

    // injeçao de dependencia (vide beans.xml)
    private Operacao operacao;

    private float n1;

    public void setN1(float n1) {
        this.n1 = n1;
    }

    private float n2;

    public void setN2(float n2) {
        this.n2 = n2;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public float executar(float n1, float n2) {
        return operacao.executar(n1, n2);
    }

    public float executar() {
        return operacao.executar(n1, n2);
    }

}
