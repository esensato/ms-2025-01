package microservicos;

import org.springframework.stereotype.Service;

@Service
public class Somar implements Operacao {

    @Override
    public float executar(Integer v1, Integer v2) {
        return v1 + v2;
    }

}