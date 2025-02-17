package microservicos.aluno;

// importação dos logs
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

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

    @PostMapping
    public ResponseEntity<String> criarAluno(@RequestBody AlunoEntity novoAlunoEntity) {
        logger.debug(
                novoAlunoEntity.getRa() + " - " + novoAlunoEntity.getNome() + " - " + novoAlunoEntity.getDisciplinas());
        return new ResponseEntity<String>("Aluno criado: " + novoAlunoEntity.getNome(), HttpStatus.OK);
    }

}
