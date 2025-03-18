package aula.microservicos.faculdade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "disciplina", url = "localhost:8081")
public interface DisciplinaIntegracao {

    @GetMapping("/api/disciplina/{id}")
    public ResponseEntity<IntegracaoEntity> obterDisciplina(@PathVariable String id);

}
