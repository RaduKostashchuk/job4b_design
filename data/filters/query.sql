select p.name, p.expired_date, p.price from product as p join type as t on p.type_id = t.id where t.name like 'СЫР';
select * from product where lower(name) like '%мороженое%';
select * from product where expired_date < current_date;
select * from product order by price desc limit 1;
select t.name, count(p.id) from product as p join type as t on p.type_id = t.id group by t.name;
select p.name, p.expired_date, p.price from product as p join type as t on p.type_id = t.id where t.name = 'СЫР' or t.name = 'МОЛОКО';
select t.name, count(p.id) from product as p join type as t on p.type_id = t.id group by t.name having count(p.name) < 10;
select p.name, p.expired_date, p.price, t.name from product as p join type as t on p.type_id = t.id;