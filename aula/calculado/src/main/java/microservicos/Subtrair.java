package microservicos;

import org.springframework.stereotype.Service;

@Service
public class Subtrair implements Operacao {

    @Override
    public float executar(Integer n1, Integer n2) {
        return n1 - n2;
    }

}
