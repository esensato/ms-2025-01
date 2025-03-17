package microservicos.aluno;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<AlunoEntity, String> {

    // SELECT * FROM TAB_ALUNO WHERE NOME CONTAINS('XPTO')
    public Iterable<AlunoEntity> findByNomeContains(String nome);

    // SELECT * FROM TAB_ALUNO WHERE RA CONTAINS('XPTO')
    public Iterable<AlunoEntity> findByRaContains(String ra);

    // SELECT NOME FROM TAB_ALUNO
    // O que vale é o nome da entidade ao inves do nome da tabela (TAB_ALUNO =
    // AlunoEntity)
    @Query("SELECT a.nome FROM AlunoEntity a ORDER BY a.nome")
    public Iterable<String> listagemNome();
}