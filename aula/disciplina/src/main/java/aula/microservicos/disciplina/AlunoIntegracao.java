package aula.microservicos.disciplina;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import aula.microservicos.aluno.AlunoEntity;

@FeignClient(name = "aluno", url = "localhost:8080")
public interface AlunoIntegracao {

    @GetMapping("/aluno/ra/{ra}")
    public ResponseEntity<List<AlunoEntity>> obterAluno(@PathVariable String ra);

}
