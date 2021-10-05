create table hosts(
	id serial primary key,
	hostname varchar(255),
	ip inet,
	department text,
	isServer boolean
);
insert into hosts (hostname, ip, department, isServer)
values ('mail.enterprise.com', '10.0.0.10/24', 'system administration', true);
update hosts set ip = '192.168.10.1/24';
delete from hosts;
select * from hosts;