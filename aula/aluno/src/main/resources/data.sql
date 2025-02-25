DROP TABLE IF EXISTS tab_aluno;

CREATE TABLE tab_aluno (
 ra VARCHAR(30) NOT NULL PRIMARY KEY,
 nome VARCHAR(30) NOT NULL,
 disciplinas VARCHAR(1000) NOT NULL
);

insert into tab_aluno(ra, nome, disciplinas) values ('100', 'Joao Silva', 'Dev Mobile');

insert into tab_aluno(ra, nome, disciplinas) values ('200', 'Maria Pereira', 'Design');

insert into tab_aluno(ra, nome, disciplinas) values ('300', 'Marcos Pereira', 'Programação Cobol');