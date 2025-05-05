create table users(username varchar(50) not null primary key,password varchar(500) not null, enabled boolean not null);

create table authorities (username varchar(50) not null, authority varchar(50) not null, constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username,authority);

insert into users(username, password, enabled) values ('admin', '{noop}admin', true);

insert into authorities (username, authority) values ('admin', 'admin');

insert into users(username, password, enabled) values ('user', '{noop}user', true);

insert into authorities (username, authority) values ('user', 'admin');

insert into users(username, password, enabled) values ('sa', '{noop}password', true);

insert into authorities (username, authority) values ('sa', 'admin');