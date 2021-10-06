create database many_to_many;
\c many_to_many;
create table products(
id serial primary key,
name varchar(255));
create table stores(
id serial primary key,
name varchar(255));
create table products_stores(
id serial primary key,
product_id int references products(id),
store_id int references stores(id));
