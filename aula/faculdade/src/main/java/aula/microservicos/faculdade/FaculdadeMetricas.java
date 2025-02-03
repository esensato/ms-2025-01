package aula.microservicos.faculdade;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component("faculdade.metricas")
public class FaculdadeMetricas {

    FaculdadeMetricas(MeterRegistry registry) {

        registry.counter("faculdade_count", Tags.of("total_alunos", "10"));

    }

}
