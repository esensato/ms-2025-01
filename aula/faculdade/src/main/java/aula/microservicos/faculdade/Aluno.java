package aula.microservicos.faculdade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aluno")
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8080"),
        @Server(url = "http://ambiente-teste.com") }, info = @Info(title = "Sistema Acadêmico", description = "Sistema para controle de matriculas de alunos", version = "1.0"))
public class Aluno {

    @Autowired
    private AlunoDAO dao;

    @Value("${nome.aluno}")
    String nomeAlunoProperty;

    // instancia log para a classe Aluno
    private static final Logger logger = LoggerFactory.getLogger(Aluno.class);

    // consultar um aluno
    // localhost:8080/aluno?id=100
    @GetMapping
    public ResponseEntity<AlunoBean> getAluno(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id) {
        logger.debug("Obtendo o aluno: " + id);
        if (id > 100) {
            return new ResponseEntity<AlunoBean>(new AlunoBean(), HttpStatus.OK);
        } else {
            return new ResponseEntity<AlunoBean>(new AlunoBean(), HttpStatus.NOT_FOUND);
        }
    }

    // /aluno
    @Operation(summary = "Lista alunos", description = "Obtem a lista de todos os alunos", tags = { "aluno" })
    @GetMapping("/todos")
    public ResponseEntity<Iterable<AlunoBean>> todosAlunos() throws AlunoInexistenteException {

        if (dao.count() > 0) {
            return new ResponseEntity<Iterable<AlunoBean>>(dao.findAll(), HttpStatus.OK);
        } else
            throw new AlunoInexistenteException();
    }

    @GetMapping("/curso/{curso}")
    public ResponseEntity<Iterable<AlunoBean>> todosAlunosPorCurso(@PathVariable String curso)
            throws AlunoInexistenteException {

        return new ResponseEntity<Iterable<AlunoBean>>(dao.findByCurso(curso), HttpStatus.OK);

    }

    @GetMapping("/curso/{curso}/{turma}")
    public ResponseEntity<Iterable<AlunoBean>> todosAlunosPorCursoTurma(@PathVariable String curso,
            @PathVariable String turma)
            throws AlunoInexistenteException {

        return new ResponseEntity<Iterable<AlunoBean>>(dao.findByCursoAndTurma(curso, turma), HttpStatus.OK);

    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Iterable<AlunoBean>> todosAlunosPorNome(@PathVariable String nome)
            throws AlunoInexistenteException {

        return new ResponseEntity<Iterable<AlunoBean>>(dao.findByNomeLike("%" + nome + "%"), HttpStatus.OK);

    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<String>("Pong", HttpStatus.OK);
    }

    // localhost:8080/aluno/100
    @Operation(summary = "Obter dados aluno", description = "Obtem os dados de um aluno pelo código", tags = {
            "aluno" }, parameters = { @Parameter(name = "idAluno", required = false, description = "Id do Aluno") })

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrou o aluno", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AlunoBean.class)) }),
            @ApiResponse(responseCode = "400", description = "Id do aluno inválido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Aluno não localizado", content = @Content) })

    @GetMapping("/{id}")
    public ResponseEntity<AlunoBean> getAlunoByPath(@PathVariable Integer id) throws AlunoInexistenteException {
        logger.debug("Obtendo o aluno: " + id);

        if (dao.existsById(id)) {

            AlunoBean ret = dao.findById(id).get();
            return new ResponseEntity<AlunoBean>(ret, HttpStatus.OK);

        } else {
            throw new AlunoInexistenteException();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remover(@PathVariable Integer id) throws AlunoInexistenteException {
        logger.debug("Removendo o aluno: " + id);

        if (dao.existsById(id)) {

            dao.deleteById(id);
            return new ResponseEntity<String>("Aluno removido", HttpStatus.OK);

        } else {
            throw new AlunoInexistenteException();
        }

    }

    // {"id": 100, "turma": "teste"}
    @PostMapping
    public ResponseEntity<Integer> cadastrar(@Valid @RequestBody AlunoBean aluno) throws IdDuplicadoException {
        logger.debug("Cadastrando o aluno: " + aluno.getNome());

        // if (aluno.getId() == 100) throw new IdDuplicadoException();

        dao.save(aluno);

        return new ResponseEntity<Integer>(aluno.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Integer id, @RequestBody AlunoBean aluno)
            throws AlunoInexistenteException {
        logger.debug("Atualizando o aluno: " + aluno.getNome());

        if (dao.existsById(id)) {

            AlunoBean bcoDados = dao.findById(id).get();
            bcoDados.setCurso(aluno.getCurso());
            bcoDados.setTurma(aluno.getTurma());
            bcoDados.setNome(aluno.getNome());
            dao.save(bcoDados);
            return new ResponseEntity<String>("Aluno atualizado", HttpStatus.OK);

        } else {
            throw new AlunoInexistenteException();
        }

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

}
