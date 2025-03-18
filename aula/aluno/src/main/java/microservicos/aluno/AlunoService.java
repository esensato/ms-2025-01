package microservicos.aluno;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Com esta anotacao a classe passa a ser reconhecida pelo Spring
// E pode ser injetada com o @Autowired
@Service
public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    // Acesso à base de dados de aluno
    @Autowired
    private AlunoRepository repo;

    // retorna todos os alunos cadastrados
    public Iterable<AlunoEntity> todos() {
        // retorna todos os alunos cadastrados na base
        Iterable<AlunoEntity> alunos = repo.findAll();
        return alunos;
    }

    // retorna total de alunos
    public Long total() {
        return repo.count();
    }

    // obtem todos os alunos pelo nome
    public Iterable<AlunoEntity> obterAlunoNome(String nome) {

        Iterable<AlunoEntity> alunos = repo.findByNomeContains(nome);
        return alunos;
    }

    // obtem todos os nomes dos alunos
    public Iterable<String> obterNomes() {

        Iterable<String> alunos = repo.listagemNome();
        return alunos;
    }

    // obtem todos os alunos pelo ra
    public Iterable<AlunoEntity> obterAlunoRa(String ra) {

        Iterable<AlunoEntity> alunos = repo.findByRaContains(ra);
        return alunos;
    }

    // end-point para pesquisar um aluno pelo RA
    public AlunoEntity obterAluno(String ra, Boolean incluirDisciplinas) {
        logger.debug("Obtendo dados do RA: " + ra);
        logger.debug("incluirDisciplinas: " + incluirDisciplinas);

        // verificar se o ra de fato existe na base
        boolean semResultado = repo.findById(ra).isEmpty();
        logger.debug("Sem resultado? " + semResultado);

        AlunoEntity resposta = null;
        if (semResultado) {
            resposta = null;
        } else {
            // obtem o aluno por meio do ra
            resposta = repo.findById(ra).get();
            logger.debug("Aluno = " + semResultado);
            if (!incluirDisciplinas) {
                // apaga as disciplinas
                resposta.setDisciplinas(new ArrayList<String>());
            }

        }
        return resposta;
    }

    // Cria um novo aluno recebendo no corpo da requisição POST
    // os dados do aluno no formato JSON {"ra": "200", "nome": "Maria Silva",
    // "disciplinas": ["Dev Mobile"]}
    public String criarAluno(AlunoEntity novoAlunoEntity) {
        logger.debug(
                novoAlunoEntity.getRa() + " - " + novoAlunoEntity.getNome() + " - " + novoAlunoEntity.getDisciplinas());

        // cria o novo aluno na base de dados
        repo.save(novoAlunoEntity);
        return novoAlunoEntity.getNome();
    }

    // Atualizar os dados de um aluno
    public AlunoEntity atualizarAluno(String ra, AlunoEntity alunoEntity) {
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
            return alunoEntity;
        }

    }

    // Remove um aluno
    public String removerAluno(String ra) {
        // se o ra informado nao existir entao retorna 404
        if (!repo.existsById(ra)) {
            throw new AlunoInexistenteException();
        } else {
            // exclui o registro da base
            repo.deleteById(ra);
            return "Aluno excluido: " + ra;
        }

    }
}
