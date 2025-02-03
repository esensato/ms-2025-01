package aula.microservicos.faculdade;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaDAO extends CrudRepository<DisciplinaBean, Integer> {

}
