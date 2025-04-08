package aula.microservicos.faculdade;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

@Component
public class FaculdadeMetrics {

    public FaculdadeMetrics(MeterRegistry registry) {
        System.out.println(registry);
        registry.counter("total.alunos", Tags.of("total", "10"));
    }

}
