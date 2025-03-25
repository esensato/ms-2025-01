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

    // gera requisições para o endpoint de Aluno (8080)
    @Autowired
    private AlunoIntegracao alunoIntegracao;

    // gera requisições para o endpoint de Disciplina (8082)
    @Autowired
    private DisciplinaIntegracao disciplinaIntegracao;

    // gera requisições para o endpoint de Matricula (8083)
    @Autowired
    private MatriculaIntegracao matriculaIntegracao;

    @Autowired
    private FaculdadeServico faculdadeServico;

    // Matricular aluno
    @PutMapping("/matricula/{rm}/{idDisciplina}")
    public ResponseEntity<String> matricular(@PathVariable String rm, @PathVariable String idDisciplina) {

        // verificar se o aluno existe
        ResponseEntity<IntegracaoEntity> aluno = alunoIntegracao.obterAluno(rm);
        if (aluno.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<String>("", aluno.getStatusCode());
        }

        // verficar se a disciplina existe
        ResponseEntity<IntegracaoEntity> disciplina = disciplinaIntegracao.obterDisciplina(idDisciplina);

        if (disciplina.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<String>("", disciplina.getStatusCode());
        }

        MatriculaEntity matricula = new MatriculaEntity();

        if (disciplina != null && aluno != null) {

            matricula.setId(disciplina.getBody().getId());
            matricula.setIdDisciplina(disciplina.getBody().getId());
            matricula.setRaAluno(aluno.getBody().getRa());
            matriculaIntegracao.matricular(matricula);

        }

        return new ResponseEntity<String>("Matricula efetuada", HttpStatus.OK);

    }

    // solicita o cancelamento da matricula
    @PutMapping("/matricula/cancelar/solicitar/{rm}/{idDisciplina}")
    public ResponseEntity<CancelamentoMatriculaEstado> solicitarCancelamento(@PathVariable String rm,
            @PathVariable String idDisciplina) {
        return new ResponseEntity<CancelamentoMatriculaEstado>(faculdadeServico.solicitar(), HttpStatus.OK);

    }

    // coordenador aprova o cancelamento
    @PutMapping("/matricula/cancelar/aprovar/coordenador/{rm}/{idDisciplina}")
    public ResponseEntity<CancelamentoMatriculaEstado> aprovarCoordenador(@PathVariable String rm,
            @PathVariable String idDisciplina) {
        return new ResponseEntity<CancelamentoMatriculaEstado>(faculdadeServico.aprovarCoordenador(), HttpStatus.OK);

    }

    // secretaria aprova o cancelamento
    @PutMapping("/matricula/cancelar/aprovar/secretaria/{rm}/{idDisciplina}")
    public ResponseEntity<CancelamentoMatriculaEstado> aprovarSecretaria(@PathVariable String rm,
            @PathVariable String idDisciplina) {
        return new ResponseEntity<CancelamentoMatriculaEstado>(faculdadeServico.aprovarSecretaria(rm, idDisciplina),
                HttpStatus.OK);

    }

}
