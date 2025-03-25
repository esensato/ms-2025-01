package aula.microservicos.faculdade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "matricula", url = "localhost:8083")
public interface MatriculaIntegracao {

    @PostMapping("/api/matricula")
    public ResponseEntity<MatriculaEntity> matricular(@RequestBody MatriculaEntity matricula);

    @DeleteMapping("/api/matricula/{idMatricula}")
    public ResponseEntity<MatriculaEntity> excluir(@PathVariable String idMatricula);

}
