package microservicos.aluno;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    // Tratamento das validacoes dos atributos das entidades
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

}
