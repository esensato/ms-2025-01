package microservicos.aluno;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<AlunoEntity, String> {

    // SELECT * FROM TAB_ALUNO WHERE NOME CONTAIN('XPTO')
    public Iterable<AlunoEntity> findByNomeContains(String nome);
}