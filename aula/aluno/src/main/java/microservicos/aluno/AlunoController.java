package microservicos.aluno;

// importação dos logs
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    // Acesso à base de dados de aluno
    @Autowired
    private AlunoRepository repo;

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
        Iterable<AlunoEntity> alunos = repo.findAll();
        return new ResponseEntity<Iterable<AlunoEntity>>(alunos, HttpStatus.OK);
    }

    // retorna total de alunos
    @GetMapping("/total")
    public ResponseEntity<Long> total() {
        return new ResponseEntity<Long>(repo.count(), HttpStatus.OK);
    }

    // obtem todos os alunos pelo nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Iterable<AlunoEntity>> obterAlunoNome(@PathVariable String nome) {

        Iterable<AlunoEntity> alunos = repo.findByNomeContains(nome);
        return new ResponseEntity<Iterable<AlunoEntity>>(alunos, HttpStatus.OK);
    }

    // end-point para pesquisar um aluno pelo RA
    // GET http://localhost:8080/aluno/123?incluirDisciplinas=true
    @GetMapping("/{ra}")
    public ResponseEntity<AlunoEntity> obterAluno(@PathVariable String ra,
            @RequestParam(name = "incluirDisciplinas", required = false, defaultValue = "false") Boolean incluirDisciplinas) {
        logger.debug("Obtendo dados do RA: " + ra);
        logger.debug("incluirDisciplinas: " + incluirDisciplinas);

        // verificar se o ra de fato existe na base
        boolean semResultado = repo.findById(ra).isEmpty();
        logger.debug("Sem resultado? " + semResultado);

        ResponseEntity<AlunoEntity> resposta;
        if (semResultado) {
            resposta = new ResponseEntity<AlunoEntity>(new AlunoEntity(), HttpStatus.NOT_FOUND);
        } else {
            // obtem o aluno por meio do ra
            AlunoEntity aluno = repo.findById(ra).get();
            if (!incluirDisciplinas) {
                // apaga as disciplinas
                aluno.setDisciplinas(new ArrayList<String>());
            }
            resposta = new ResponseEntity<AlunoEntity>(aluno, HttpStatus.OK);
        }
        return resposta;
    }

    // Cria um novo aluno recebendo no corpo da requisição POST
    // os dados do aluno no formato JSON {"ra": "200", "nome": "Maria Silva",
    // "disciplinas": ["Dev Mobile"]}
    // Utilizar o curl ou postman para testar o endpoint:
    // curl --location 'http://localhost:8080/aluno'
    // --header 'Content-Type: application/json'
    // --data '{"ra": "200","nome": "Maria Silva","disciplinas": ["Dev Mobile"]}'
    @PostMapping
    public ResponseEntity<String> criarAluno(@RequestBody AlunoEntity novoAlunoEntity) {
        logger.debug(
                novoAlunoEntity.getRa() + " - " + novoAlunoEntity.getNome() + " - " + novoAlunoEntity.getDisciplinas());

        // cria o novo aluno na base de dados
        repo.save(novoAlunoEntity);
        return new ResponseEntity<String>("Aluno criado: " + novoAlunoEntity.getNome(), HttpStatus.OK);
    }

    // Atualizar os dados de um aluno
    // PUT http://localhost:8080/200 - corpo da requisição os dados
    // curl --location --request PUT 'http://localhost:8080/aluno/200'
    // --header 'Content-Type: application/json'
    // --data '{"nome": "Maria Silva Lima"}'
    @PutMapping("/{ra}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable(required = true) String ra,
            @RequestBody AlunoEntity alunoEntity) {

        // se o ra informado nao existir entao retorna 404
        if (repo.findById(ra).isEmpty()) {
            throw new AlunoInexistenteException();
        } else {
            // save e utilizado tanto para criar um novo registro quanto para atualizar
            // um registro ja existente
            // se id nao existe (ou nulo) então INSERT caso contrario UPDATE
            // garante que o ra (id) está definido
            alunoEntity.setRa(ra);
            repo.save(alunoEntity);
            logger.debug(
                    ra + " - " + alunoEntity.getNome() + " - " + alunoEntity.getDisciplinas());
            return new ResponseEntity<AlunoEntity>(alunoEntity, HttpStatus.OK);
        }

    }

    // Remove um aluno
    // curl --location --request DELETE 'http://localhost:8080/aluno/200' --header
    // 'Content-Type: application/json'
    @DeleteMapping("/{ra}")
    public ResponseEntity<String> removerAluno(@PathVariable(required = true) String ra) {
        // se o ra informado nao existir entao retorna 404
        if (!repo.existsById(ra)) {
            throw new AlunoInexistenteException();
        } else {
            // exclui o registro da base
            repo.deleteById(ra);
            return new ResponseEntity<String>("Aluno excluido: " + ra, HttpStatus.OK);
        }

    }

}
