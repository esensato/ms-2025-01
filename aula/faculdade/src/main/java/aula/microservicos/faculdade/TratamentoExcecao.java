package aula.microservicos.faculdade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TratamentoExcecao {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Aluno não localizado!")
    @ExceptionHandler(AlunoInexistenteException.class)
    public void tratamento(AlunoInexistenteException e) {

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Id informado já cadastrado!")
    @ExceptionHandler(IdDuplicadoException.class)
    public void tratamento(IdDuplicadoException e) {

    }

}
