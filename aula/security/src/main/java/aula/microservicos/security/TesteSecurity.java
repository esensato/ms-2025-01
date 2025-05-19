package aula.microservicos.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class TesteSecurity implements CommandLineRunner {

    @Autowired
    SecurityRepository repo;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("------> REQUISICAO");

        System.out.println("------> Total de usuarios na tabela USUARIO: " + repo.count());

        UsuarioEntity usuario = repo.findById("admin@spring.com").get();

        System.out.println(usuario.getEmail());
        System.out.println(usuario.getRoles());

        // RestClient restClient = RestClient.create();
        // ResponseEntity<String> response = restClient.get()
        // .uri("http://localhost:8080/seguranca/admin")
        // .header("Authorization", "Basic YWRtaW46YWRtaW4=")
        // .retrieve()
        // .toEntity(String.class);
        // System.out.println(response.getStatusCode());
        // System.out.println(response.getBody());

        System.out.println("------> GERACAO TOKEN");

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", "admin@spring.com");
        claims.put("role", "ROLE_ADMIN");
        claims.put("nome", "Edson A Sensato");

        Key key = Keys
                .hmacShaKeyFor("minha_chave_super_secreta_para_geracao_do_algoritmo".getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .setSubject("admin@spring.com")
                .setClaims(claims)
                .signWith(key)
                .compact();

        System.out.println(jwt);

        Object body = Jwts.parserBuilder().setSigningKey(key).build().parse(
                "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9BRE1JTiIsImVtYWlsIjoiYWRtaW5Ac3ByaW5nLmNvbSJ9.9MVuTuk84TEqr3LkxYEUdoMtbjSPNjIlQWL0q3qFIYksmxtbirTAn8Bmq7lZBcnv")
                .getBody();

        System.out.println(body);
    }

}