package aula.microservicos.matricula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "matricula")
public interface MatriculaRepository extends JpaRepository<MatriculaEntity, String> {

}
