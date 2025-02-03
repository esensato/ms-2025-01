package aula.microservicos.faculdade;

import java.util.ArrayList;
import java.util.List;

public class DisciplinasAlunoBean {

    private AlunoBean aluno;

    public AlunoBean getAluno() {
        return aluno;
    }

    public void setAluno(AlunoBean aluno) {
        this.aluno = aluno;
    }

    private List<DisciplinaBean> disciplinas = new ArrayList<DisciplinaBean>();

    public void addDisciplina(DisciplinaBean disciplina) {
        disciplinas.add(disciplina);
    }

    public List<DisciplinaBean> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaBean> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
