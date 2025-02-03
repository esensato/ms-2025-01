package aula.microservico.programacaoreativa;

import java.time.Duration;
import reactor.core.publisher.Mono;

public class ProdutorMensagemMono {
    public static void main(String[] args) throws InterruptedException {
        ConsumidorMensagemMono cm = new ConsumidorMensagemMono();
        ConsumidorMensagemMono cm2 = new ConsumidorMensagemMono();

        // enviar uma mensagem única ao consumidor
        Mono<String> m = Mono.just("Alo").delayElement(Duration.ofSeconds(5)).log();
        System.out.println("Subscribe");
        m.subscribe(cm::exibirMensagem);
        m.subscribe(cm2::exibirMensagem);

        Thread.sleep(10000);

        System.out.println("FIM");

    }
}
