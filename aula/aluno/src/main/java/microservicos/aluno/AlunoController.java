package microservicos.aluno;

// importação dos logs
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    // Classe de servicos de Aluno
    @Autowired
    private AlunoService service;

    @Value("${microservicos.aluno.mensagem}")
    private String mensagem;

    // GET http://localhost:8080/aluno/
    @GetMapping
    public ResponseEntity<String> health() {
        logger.debug("Requisicao recebida");
        return new ResponseEntity<String>(mensagem, HttpStatus.OK);
    }

    // retorna todos os alunos cadastrados
    @GetMapping("/todos")
    public ResponseEntity<Iterable<AlunoEntity>> todos() {
        // retorna todos os alunos cadastrados na base
        Iterable<AlunoEntity> alunos = service.todos();
        return new ResponseEntity<Iterable<AlunoEntity>>(alunos, HttpStatus.OK);
    }

    // retorna total de alunos
    @GetMapping("/total")
    public ResponseEntity<Long> total() {
        return new ResponseEntity<Long>(service.total(), HttpStatus.OK);
    }

    // obtem todos os alunos pelo nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Iterable<AlunoEntity>> obterAlunoNome(@PathVariable String nome) {

        Iterable<AlunoEntity> alunos = service.obterAlunoNome(nome);
        return new ResponseEntity<Iterable<AlunoEntity>>(alunos, HttpStatus.OK);
    }

    // obtem todos os nomes dos alunos
    @GetMapping("/nomes")
    public ResponseEntity<Iterable<String>> obterNomes() {

        Iterable<String> alunos = service.obterNomes();
        return new ResponseEntity<Iterable<String>>(alunos, HttpStatus.OK);
    }

    // obtem todos os alunos pelo ra
    @GetMapping("/ra/{ra}")
    public ResponseEntity<Iterable<AlunoEntity>> obterAlunoRa(@PathVariable String ra) {

        Iterable<AlunoEntity> alunos = service.obterAlunoRa(ra);
        return new ResponseEntity<Iterable<AlunoEntity>>(alunos, HttpStatus.OK);
    }

    // end-point para pesquisar um aluno pelo RA
    // GET http://localhost:8080/aluno/123?incluirDisciplinas=true
    @GetMapping("/{ra}")
    public ResponseEntity<AlunoEntity> obterAluno(@PathVariable String ra,
            @RequestParam(name = "incluirDisciplinas", required = false, defaultValue = "false") Boolean incluirDisciplinas) {

        AlunoEntity aluno = service.obterAluno(ra, incluirDisciplinas);
        if (aluno == null) {
            return new ResponseEntity<AlunoEntity>(aluno, HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<AlunoEntity>(aluno, HttpStatus.OK);
    }

    // Cria um novo aluno recebendo no corpo da requisição POST
    // os dados do aluno no formato JSON {"ra": "200", "nome": "Maria Silva",
    // "disciplinas": ["Dev Mobile"]}
    // Utilizar o curl ou postman para testar o endpoint:
    // curl --location 'http://localhost:8080/aluno'
    // --header 'Content-Type: application/json'
    // --data '{"ra": "200","nome": "Maria Silva","disciplinas": ["Dev Mobile"]}'
    @PostMapping
    public ResponseEntity<String> criarAluno(@RequestBody @Valid AlunoEntity novoAlunoEntity) {
        logger.debug(
                novoAlunoEntity.getRa() + " - " + novoAlunoEntity.getNome() + " - " + novoAlunoEntity.getDisciplinas());

        // cria o novo aluno na base de dados
        return new ResponseEntity<String>("Aluno criado: " + service.criarAluno(novoAlunoEntity), HttpStatus.OK);
    }

    // Atualizar os dados de um aluno
    // PUT http://localhost:8080/200 - corpo da requisição os dados
    // curl --location --request PUT 'http://localhost:8080/aluno/200'
    // --header 'Content-Type: application/json'
    // --data '{"nome": "Maria Silva Lima"}'
    @PutMapping("/{ra}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable(required = true) String ra,
            @RequestBody @Valid AlunoEntity alunoEntity) {

        AlunoEntity aluno = service.atualizarAluno(ra, alunoEntity);
        return new ResponseEntity<AlunoEntity>(aluno, HttpStatus.OK);

    }

    // Remove um aluno
    // curl --location --request DELETE 'http://localhost:8080/aluno/200' --header
    // 'Content-Type: application/json'
    @DeleteMapping("/{ra}")
    public ResponseEntity<String> removerAluno(@PathVariable(required = true) String ra) {

        return new ResponseEntity<String>(service.removerAluno(ra), HttpStatus.OK);

    }

}
