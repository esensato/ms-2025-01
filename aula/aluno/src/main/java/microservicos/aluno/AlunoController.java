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

    // end-point para pesquisar um aluno pelo RA
    // GET http://localhost:8080/aluno/123?incluirDisciplinas=true
    @GetMapping("/{ra}")
    public ResponseEntity<AlunoEntity> obterAluno(@PathVariable Long ra,
            @RequestParam(name = "incluirDisciplinas", required = false, defaultValue = "false") Boolean incluirDisciplinas) {
        logger.debug("Obtendo dados do RA: " + ra);
        logger.debug("incluirDisciplinas: " + incluirDisciplinas);
        ResponseEntity<AlunoEntity> resposta;
        if (ra == 100) {
            resposta = new ResponseEntity<AlunoEntity>(new AlunoEntity(), HttpStatus.NOT_FOUND);
        } else {
            AlunoEntity aluno = new AlunoEntity();
            if (incluirDisciplinas) {
                aluno.getDisciplinas().add("Desenvolvimento Micro Servicos");
            }
            aluno.setRa(ra.toString());
            aluno.setNome("Joao da Silva");
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
    public ResponseEntity<String> atualizarAluno(@PathVariable String ra, @RequestBody AlunoEntity novoAlunoEntity) {

        logger.debug(
                ra + " - " + novoAlunoEntity.getNome() + " - " + novoAlunoEntity.getDisciplinas());
        return new ResponseEntity<String>("Aluno atualizado: " + novoAlunoEntity.getNome(), HttpStatus.OK);

    }

    // Remove um aluno
    // curl --location --request DELETE 'http://localhost:8080/aluno/200' --header
    // 'Content-Type: application/json'
    @DeleteMapping("/{ra}")
    public ResponseEntity<String> removerAluno(@PathVariable Integer ra) {
        if (ra == 100) {
            throw new AlunoInexistenteException();
        }
        logger.debug("RA: " + ra);
        return new ResponseEntity<String>("Removido aluno: " + ra, HttpStatus.OK);

    }
}
