create database outerjointask;
\c outerjointask;
create table departments(
    id serial primary key,
    name text
);
create table employees(
    id serial primary key,
    name text,
    dep_id int references departments(id)
);
insert into departments(name) values('direction');
insert into departments(name) values('sales');
insert into departments(name) values('warehouse');
insert into departments(name) values('it');
insert into departments(name) values('marketing');
insert into employees(name, dep_id) values('Oleg Ivanovich', 1);
insert into employees(name, dep_id) values('Irina', 2), values('Tatiana', 2), values('Inna', 2);                                                          ^
insert into employees(name, dep_id) values('Irina', 2), ('Tatiana', 2), ('Inna', 2);
insert into employees(name, dep_id) values('Semen', 3);
insert into employees(name, dep_id) values('Fedor', 4), ('Eugeniy', 4), ('Katerina', 4);

select * from departments d left join employees e on d.id = e.dep_id;
select * from departments d right join employees e on d.id = e.dep_id;
select * from departments d full join employees e on d.id = e.dep_id;
select * from departments d cross join employees e;

select d.name from departments d left join employees e on d.id = e.dep_id where e.id is null;

select * from departments d left join employees e on d.id = e.dep_id;
select * from employees e right join departments d on d.id = e.dep_id;

select * from departments d right join employees e on d.id = e.dep_id;
select * from employees e left join departments d on e.dep_id = d.id;

create table teens(
    id serial primary key,
    name text,
    gender boolean
);

insert into teens(name, gender) values ('Petr', true), ('Ivan', true), ('Egor', true);
insert into teens(name, gender) values ('Inna', false), ('Nina', false), ('Svetlana', false);

select * from teens t1 cross join teens t2 where t1.gender != t2.gender and t1.gender = true;



