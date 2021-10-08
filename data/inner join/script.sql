create database postoffice;
\c postoffice;
create table senders(
	id serial primary key,
	name varchar(255)
);
create table packages(
	id serial primary key,
	name varchar(255),
	sender_id int references senders(id)
);
insert into senders(name) values('Ivan');
insert into senders(name) values('Egor');
insert into senders(name) values('Inna');
insert into packages(name, sender_id) values('Spare parts', 1);
insert into packages(name, sender_id) values('Winter tyres', 1);
insert into packages(name, sender_id) values('Cell phone', 2);
insert into packages(name, sender_id) values('Touchpad', 2);
select p.name from packages as p join senders as s on p.sender_id = s.id where s.name = 'Egor';
select s.name, count(p.id) from packages as p join senders as s on p.sender_id = s.id group by s.name;
select count(distinct(s.name)) from packages as p join senders as s on p.sender_id = s.id;
join senders as s on p.sender_id = s.id;