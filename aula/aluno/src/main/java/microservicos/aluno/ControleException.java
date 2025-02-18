package microservicos.aluno;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControleException {

    // Tratamento especial para a exceção AlunoInexistenteException
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Aluno não localizado!")
    @ExceptionHandler(AlunoInexistenteException.class)
    public void handleException(AlunoInexistenteException e) {
    }

}
