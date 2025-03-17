package microservicos.aluno;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tab_aluno")
public class AlunoEntity {

    @Id
    private String ra;

    @Size(min = 10, message = "Nome deve conter no minimo 10 caracteres")
    private String nome;

    private List<String> disciplinas = new ArrayList<String>();

    // botão boreito -> Ação de código -> Generate Getters and Setters.

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
