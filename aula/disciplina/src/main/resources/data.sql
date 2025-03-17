DROP TABLE IF EXISTS tab_disciplina;

CREATE TABLE tab_disciplina (
 id VARCHAR(30) NOT NULL PRIMARY KEY,
 nome VARCHAR(30) NOT NULL,
 carga_horaria INTEGER NOT NULL
);

insert into tab_disciplina(id, nome, carga_horaria) values ('100', 'Dev Mobile', 80);

insert into tab_disciplina(id, nome, carga_horaria) values ('200', 'Design', 40);

insert into tab_disciplina(id, nome, carga_horaria) values ('300', 'Programação Cobol', 120);