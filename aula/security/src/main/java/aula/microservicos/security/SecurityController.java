package aula.microservicos.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguranca")
public class SecurityController {

    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return new ResponseEntity<String>("Segurança com Spring Security", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<String>("Acesso ao serviço de administração", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<String>("Acesso ao serviço de usuario", HttpStatus.OK);
    }

}
