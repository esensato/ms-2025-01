package aula.microservicos.disciplina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;

@RepositoryRestResource(path = "disciplina")
public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, String> {

    // nao gera a opção de excluir uma disciplina pelo Data Rest
    @Override
    @RestResource(exported = false)
    void deleteById(@NonNull String id);
}
