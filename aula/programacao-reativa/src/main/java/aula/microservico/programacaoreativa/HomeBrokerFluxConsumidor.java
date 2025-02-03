package aula.microservico.programacaoreativa;

import reactor.core.publisher.Flux;

public class HomeBrokerFluxConsumidor {

    public HomeBrokerFluxConsumidor(Flux<Cotacao> fluxo) throws InterruptedException {
        // inscreve o método comprar para processar a ação quando recevida
        fluxo.subscribe(this::comprar);
        System.out.println(">> INICIADO <<");
        long gastatempo = 0;
        // somente para indicar que alguma atividade está sendo realizada...
        while (true) {
            gastatempo++;
            if (gastatempo > 100000000L) {
                System.out.print("-");
                gastatempo = 0;
            }
        }
    }

    // processa a açào quando recebida
    public void comprar(Cotacao cotacao) {
        System.out.println(">>> Processando: " + cotacao.getIdEmpresa() + " por " + cotacao.getValor());
    }
}
