package aula.microservicos.faculdade;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoDAO extends CrudRepository<AlunoBean, Integer> {

    // sempre inicia com findBy...
    // incluir nome do atributo
    Iterable<AlunoBean> findByCurso(String curso);

    // SELECT * FROM TAB_CURSO WHERE CURSO = ? AND TURMA = ?
    Iterable<AlunoBean> findByCursoAndTurma(String curso, String turma);

    // SELECT * FROM TAB_ALUNO WHERE NOME LIKE ?
    Iterable<AlunoBean> findByNomeLike(String nome);

    @Query("select a.id from TAB_ALUNO a")
    Iterable<Integer> minhaConsulta();

}
