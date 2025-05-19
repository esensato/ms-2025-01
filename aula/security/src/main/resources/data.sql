create table users(username varchar(50) not null primary key,password varchar(30) not null, enabled boolean not null);

create table authorities (username varchar(50) not null, authority varchar(50) not null, constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

insert into users(username, password, enabled) values ('admin', '{noop}admin', true);

insert into authorities (username, authority) values ('admin', 'admin');

insert into users(username, password, enabled) values ('user', '{noop}user', true);

insert into authorities (username, authority) values ('user', 'admin');

insert into users(username, password, enabled) values ('sa', '{noop}password', true);

insert into authorities (username, authority) values ('sa', 'admin');

insert into users(username, password, enabled) values ('aluno', '{noop}aluno', true);

insert into authorities (username, authority) values ('aluno', 'user');

-- Minha tabela de usuarios (JPA)

drop table usuario;

create table usuario (email varchar(50) not null primary key, senha varchar(30) not null, roles varchar(500));

insert into usuario (email, senha, roles) values ('admin@spring.com', '{noop}admin', 'ROLE_ADMIN');

insert into usuario (email, senha, roles) values ('user1@spring.com', '{noop}user1', 'ROLE_USER');

insert into usuario (email, senha, roles) values ('user2@spring.com', '{noop}user2', 'ROLE_USER;ROLE_ADMIN');
