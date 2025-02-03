package aula.microservico.programacaoreativa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class BolsaWebFlux {

    BolsaFluxProdutor b = null;

    public BolsaWebFlux() {
        b = new BolsaFluxProdutor();
        try {
            b.iniciar(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public RouterFunction<ServerResponse> mensagem() {
        return route(GET("/mensagem"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(Mono.just("Endpoint Reactive"), String.class));
    }

    @Bean
    public RouterFunction<ServerResponse> cotacao() {
        return route(GET("/cotacao"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Flux.fromIterable(BolsaFluxProdutor.cotacoes).delayElements(Duration.ofSeconds(2)),
                                Cotacao.class));
    }

}
