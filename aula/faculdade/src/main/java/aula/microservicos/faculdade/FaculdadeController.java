package aula.microservicos.faculdade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/faculdade")
@OpenAPIDefinition(info = @Info(title = "Sistema Faculdade", contact = @Contact(email = "edson.sensato@espm.br")))
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

    @Operation(summary = "Matricular aluno", description = "Efetua a matrícula de um aluno em uma disciplina", tags = {
            "matricula" })
    @Parameters(value = { @Parameter(name = "rm", description = "Registro de matrícula"),
            @Parameter(name = "idDisciplina", description = "Identificação da disciplina") })

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matrícula efetivada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Aluno ou disciplina não localizado", content = @Content) })

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

        return new ResponseEntity<String>("{\"msg\": \"Matricula efetuada\"}", HttpStatus.OK);

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
