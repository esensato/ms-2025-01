package aula.microservicos.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/seguranca")
public class SecurityController {

    @Autowired
    SecurityRepository repo;

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
        return new ResponseEntity<String>("Acesso ao serviço de usuário", HttpStatus.OK);
    }

    @GetMapping("/logintoken")
    public ResponseEntity<String> logintoken() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = repo.findById(auth.getName()).get();

        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles());

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", usuario.getEmail());
        claims.put("role", usuario.getRoles());

        Key key = Keys
                .hmacShaKeyFor("minha_chave_super_secreta_para_geracao_do_algoritmo".getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setSubject(usuario.getEmail())
                .setClaims(claims)
                .signWith(key)
                .compact();

        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logintoken(@RequestBody Map<String, Object> dados) {

        String usuario = (String) dados.get("usuario");
        String senha = (String) dados.get("senha");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("username", usuario);
        requestBody.add("password", senha);
        requestBody.add("client_id", "seguranca");
        requestBody.add("grant_type", "password");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:9090/realms/seguranca/protocol/openid-connect/token",
                new HttpEntity<>(requestBody, headers), String.class);

        JSONObject json = new JSONObject(response.getBody());
        System.out.println(json);
        return response;
    }

}
