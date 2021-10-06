create database many_to_one;
\c many_to_one;
create table persons(
id serial primary key,
name varchar(255));
create table mailboxes(
id serial primary key,
name varchar(255),
persons_id int references persons(id));