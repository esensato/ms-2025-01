package aula.microservicos.faculdade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matricula")
public class Matricula {

    @Autowired
    MatriculaDAO dao;

    @PutMapping("/{idAluno}/{idDisciplina}")
    public ResponseEntity<MatriculaBean> matricular(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina) {

        MatriculaBean matricula = new MatriculaBean();
        matricula.setIdAluno(idAluno);
        matricula.setIdDisciplina(idDisciplina);
        matricula.setStatus("A");
        dao.save(matricula);
        return new ResponseEntity<MatriculaBean>(matricula, HttpStatus.OK);
    }

    @DeleteMapping("/{idAluno}/{idDisciplina}")
    public ResponseEntity<MatriculaBean> cancelar(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina) {

        ResponseEntity<MatriculaBean> response = new ResponseEntity<MatriculaBean>(HttpStatus.NOT_FOUND);
        MatriculaBean matricula = dao.findByIdAlunoAndIdDisciplina(idAluno, idDisciplina);
        if (matricula != null) {
            matricula.setStatus("C");
            dao.save(matricula);
            response = new ResponseEntity<MatriculaBean>(matricula, HttpStatus.OK);
        } else {

        }
        return response;
    }

}
