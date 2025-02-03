package aula.microservico.programacaoreativa;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class ProdutorMensagemFlux {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<Integer>();

        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9).log().subscribe(numeros::add);

        System.out.println(numeros);

    }
}
