package aula.microservicos.faculdade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "aluno", url = "localhost:8080")
public interface AlunoIntegracao {

    @GetMapping("/aluno/{rm}")
    public ResponseEntity<IntegracaoEntity> obterAluno(@PathVariable String rm);

    @GetMapping("/aluno")
    public ResponseEntity<String> health();

}
