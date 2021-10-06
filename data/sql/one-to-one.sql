create database one_to_one;
\c one_to_one;
create table vehicles(
id serial primary key,
model varchar(255));
create table numbers(
id serial primary key,
number varchar(255));
create table vehicles_numbers(
id serial primary key,
vehicle_id int references vehicles(id) unique,
number_id int references numbers(id) unique);