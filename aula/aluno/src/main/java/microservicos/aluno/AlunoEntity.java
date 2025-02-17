package microservicos.aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoEntity {

    private String ra;

    private String nome;

    private List<String> disciplinas = new ArrayList<String>();

    // botão boreito -> Ação de código -> Generate Getters and Setters

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
