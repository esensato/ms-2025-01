package aula.microservicos.faculdade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

// Indica se o serviço está disponível ou não
@Component
public class FaculdadeHealth implements HealthIndicator {

    @Autowired
    private AlunoIntegracao alunoIntegracao;

    @Override
    public Health health() {

        try {
            ResponseEntity<String> ret = alunoIntegracao.health();
            System.out.println(ret.getStatusCode());
        } catch (Exception e) {
            return Health.down().build();
        }
        return Health.up().build();
    }

}
