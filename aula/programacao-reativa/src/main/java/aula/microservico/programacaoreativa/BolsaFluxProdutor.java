package aula.microservico.programacaoreativa;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import reactor.core.publisher.Flux;

public class BolsaFluxProdutor {

    public static List<Cotacao> cotacoes = new ArrayList<Cotacao>();
    public Flux<Cotacao> fluxo;

    public void iniciar(int repeticoes) throws InterruptedException {
        Random r = new Random();

        for (int i = 0; i < repeticoes; i++) {
            final int valor = (-50 + r.nextInt(100));
            final int id = r.nextInt(Cotacao.IDS.length);
            // as cotações são direcionadas para o fluxo
            cotacoes.add(new Cotacao(Cotacao.IDS[id], 100 - valor));

        }

        fluxo = Flux.fromIterable(cotacoes).delayElements(Duration.ofSeconds(2));

    }

    public static void main(String[] args) throws InterruptedException {
        BolsaFluxProdutor b = new BolsaFluxProdutor();
        b.iniciar(100);
    }
}
