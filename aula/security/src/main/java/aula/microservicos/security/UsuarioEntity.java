package aula.microservicos.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// create table usuario (email varchar(50) not null primary key, senha varchar(30) not null, roles varchar(500));

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    private String email;

    private String senha;

    private String roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
