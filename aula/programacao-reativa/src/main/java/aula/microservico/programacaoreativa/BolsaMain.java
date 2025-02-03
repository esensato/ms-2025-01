package aula.microservico.programacaoreativa;

public class BolsaMain {

    public static void main(String[] args) throws InterruptedException {

        BolsaFluxProdutor bolsa = new BolsaFluxProdutor();
        bolsa.iniciar(100);
        new HomeBrokerFluxConsumidor(bolsa.fluxo);

        while (true)
            ;
    }
}