create database devpeople;
\c devpeople;
create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into devices(name, price) values('smartphone', 3000);
insert into devices(name, price) values('tablet', 5000);
insert into devices(name, price) values('notebook', 10000);

insert into people(name) values('Ivan'), ('Egor'), ('Stepan');

insert into devices_people(device_id, people_id) values(1, 1);
insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(2, 2);
insert into devices_people(device_id, people_id) values(3, 3);

select avg(devices.price) from devices;

select p.name, avg(d.price) from devices_people as dp join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id group by p.name;

select p.name, avg(d.price) from devices_people as dp join people as p on dp.people_id = p.id
join devices as d on dp.device_id = d.id group by p.name having avg(d.price) > 5000;