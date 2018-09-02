create table users (
  id int auto_increment primary key,
  name varchar(20) not null,
  address varchar(100)
);

insert into users(id, name, address) VALUES (1, 'queryholic', 'Korea, Republic of');
insert into users(id, name, address) VALUES (2, 'parkstar', 'Korea, Republic of');