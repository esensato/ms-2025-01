package aula.microservicos.faculdade;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaDAO extends CrudRepository<MatriculaBean, Integer> {

    // http://localhost:8080/api/matricula/search/buscarAlunoEDisciplina?idAluno=1&idDisciplina=1
    @RestResource(path = "buscarAlunoEDisciplina")
    public MatriculaBean findByIdAlunoAndIdDisciplina(int idAluno, int idDisciplina);

    // http://localhost:8080/api/matricula/search/buscarTodasDisciplinasPorAluno?idAluno=1
    @RestResource(path = "buscarTodasDisciplinasPorAluno")
    public List<MatriculaBean> findAllByIdAluno(int idAluno);

    // http://localhost:8080/api/matricula/search/buscarTodasMatriculasPorDisciplina?idDisciplina=1
    @RestResource(path = "buscarTodasMatriculasPorDisciplina")
    public List<MatriculaBean> findAllByIdDisciplina(int idDisciplina);
}
