package aula.microservicos.disciplina;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import aula.microservicos.aluno.AlunoEntity;

// Permite executar código realizando a injeção de dependência
@Component
public class DisciplinaTeste implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DisciplinaTeste.class);

    @Autowired
    private AlunoIntegracao integracao;

    @Override
    public void run(String... args) throws Exception {
        logger.info("---> Servico iniciado...");

        System.out.println("--------- OPEN FEIGN ---------");
        ResponseEntity<List<AlunoEntity>> resposta = integracao.obterAluno("200");
        if (resposta.getStatusCode() == HttpStatusCode.valueOf(200)) {

            List<AlunoEntity> alunos = resposta.getBody();
            if (alunos != null) {
                for (AlunoEntity aluno : alunos) {
                    System.out.println(aluno.getNome());
                }
            }
        }
    }

}
