package aula.microservicos.eurekacliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "faculdade")
public interface FaculdadeProxy {

    @GetMapping("/aluno/ping")
    public ResponseEntity<String> ping();

}
