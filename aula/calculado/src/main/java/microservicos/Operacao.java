package microservicos;

import org.springframework.stereotype.Service;

@Service
public interface Operacao {
    float executar(Integer v1, Integer v2);
}
