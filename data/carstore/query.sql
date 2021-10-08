select v.name, e.name, b.name, g.name from vehicle v
join engine e on v.eng_id = e.id
join body b on v.body_id = b.id
join gearbox g on v.gbox_id = g.id;

select * from engine e left join vehicle v on e.id = v.eng_id where v.name is null;
select * from body b left join vehicle v on b.id = v.body_id where v.name is null;
select * from gearbox g left join vehicle v on g.id = v.gbox_id where v.name is null;