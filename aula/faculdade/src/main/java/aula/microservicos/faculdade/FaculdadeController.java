package aula.microservicos.faculdade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculdade")
public class FaculdadeController {

    @Autowired
    private AlunoIntegracao alunoIntegracao;

    @Autowired
    private DisciplinaIntegracao disciplinaIntegracao;

    // Matricular aluno
    @PutMapping("/matricula/{rm}/{idDisciplina}")
    public ResponseEntity<IntegracaoEntity> matricular(@PathVariable String rm, @PathVariable String idDisciplina) {

        // verificar se o aluno existe
        ResponseEntity<IntegracaoEntity> aluno = alunoIntegracao.obterAluno(rm);

        // verficar se a disciplina existe
        ResponseEntity<IntegracaoEntity> disciplina = disciplinaIntegracao.obterDisciplina(idDisciplina);

        IntegracaoEntity retorno = new IntegracaoEntity();
        retorno.setId(disciplina.getBody().getId());
        retorno.setRa(aluno.getBody().getRa());

        return new ResponseEntity<IntegracaoEntity>(retorno, HttpStatus.OK);

    }
}
