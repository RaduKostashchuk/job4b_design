\c tracker;
insert into rules(name) values('read');
insert into rules(name) values('create');
insert into rules(name) values('delete');

insert into role(name) values('admin');
insert into role(name) values('user');

insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);
insert into role_rules(role_id, rules_id) values(1, 3);
insert into role_rules(role_id, rules_id) values(2, 1);
insert into role_rules(role_id, rules_id) values(2, 2);

insert into users(name, role_id) values('radu', 1);
insert into users(name, role_id) values('ivan', 2);

insert into category(name) values('error');
insert into category(name) values('warning');
insert into category(name) values('informational');

insert into state(name) values('open');
insert into state(name) values('closed');

insert into item(name, user_id, category_id, state_id)
values('Mouse is not working', 2, 2, 1);