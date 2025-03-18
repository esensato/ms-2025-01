DROP TABLE IF EXISTS tab_matricula;

CREATE TABLE tab_matricula (
 id VARCHAR(60) NOT NULL PRIMARY KEY, 
 ra_Aluno VARCHAR(30) NOT NULL ,
 id_Disciplina VARCHAR(30) NOT NULL,
 situacao INTEGER NOT NULL
);