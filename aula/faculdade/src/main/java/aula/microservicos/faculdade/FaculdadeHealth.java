package aula.microservicos.faculdade;

import java.io.File;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("arquivo")
public class FaculdadeHealth implements HealthIndicator {

    @Override
    public Health health() {

        File f = new File("/Users/esensato/Documents/espm/repo/ms-2024-01/teste.properties");
        if (f.exists()) {
            return Health.up().build();
        } else {
            return Health.outOfService().build();
        }
    }

}
