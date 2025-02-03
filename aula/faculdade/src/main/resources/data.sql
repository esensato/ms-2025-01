DROP TABLE IF EXISTS tab_aluno;

CREATE TABLE tab_aluno (
    id_aluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    turma VARCHAR(10) NOT NULL,
    curso VARCHAR(50) DEFAULT NULL
);

DROP TABLE IF EXISTS tab_disciplina;
CREATE TABLE tab_disciplina (
    id_disciplina INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    carga_horaria INTEGER NOT NULL
);

DROP TABLE IF EXISTS tab_matricula;

CREATE TABLE tab_matricula (
    id_matricula INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT NOT NULL,
    id_disciplina INT NOT NULL,
    status_matricula VARCHAR(1) NOT NULL
);

CREATE TABLE USERS (
    USERNAME VARCHAR2(128) PRIMARY KEY,
    PASSWORD VARCHAR2(128) NOT NULL,
    ENABLED  VARCHAR2(10) NOT NULL
);


CREATE TABLE AUTHORITIES (
    USERNAME VARCHAR2(128) NOT NULL,
    AUTHORITY VARCHAR2(128) NOT NULL
);

insert into tab_aluno(nome, turma, curso) values ('Pedro da Silva', '3AB', 'SI');
insert into tab_aluno(nome, turma, curso) values ('Mariana Oliveira', '4AB', 'SI');

insert into tab_disciplina(nome, carga_horaria) values ('Dev Mobile', 70);
insert into tab_disciplina(nome, carga_horaria) values ('Microservices', 80);

insert into tab_matricula(id_aluno, id_disciplina, status_matricula) values (1, 1, 'A');

commit;



