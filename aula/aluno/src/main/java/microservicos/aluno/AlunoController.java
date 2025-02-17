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
    public ResponseEntity<String> obterAluno(@PathVariable Long ra,
            @RequestParam(name = "incluirDisciplinas", required = false, defaultValue = "false") Boolean incluirDisciplinas) {
        logger.debug("Obtendo dados do RA: " + ra);
        logger.debug("incluirDisciplinas: " + incluirDisciplinas);
        ResponseEntity<String> resposta;
        if (ra == 100) {
            resposta = new ResponseEntity<String>("Aluno nao localizado", HttpStatus.NOT_FOUND);
        } else {
            String disciplinas = "";
            if (incluirDisciplinas) {
                disciplinas = " - Desenvolvimento Micro Servicos";
            }
            resposta = new ResponseEntity<String>("Joao da Silva" + disciplinas, HttpStatus.OK);
        }
        return resposta;
    }

}
