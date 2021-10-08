insert into engine(name) values ('carburator'), ('injector'), ('diesel'), ('turbo'), ('direct injection'), ('turbodiesel');
insert into body(name) values ('sedan'), ('coupe'), ('minivan'), ('suv'), ('bus'), ('cabriolet');
insert into gearbox(name) values ('manual'), ('automatic'), ('variator'), ('robot');

insert into vehicle  (name, eng_id, body_id, gbox_id) values ('moskvich-412', 1, 1, 1), ('vaz-2114', 2, 2, 1);
insert into vehicle  (name, eng_id, body_id, gbox_id) values ('landcruiser 70', 3, 4, 2), ('vw passat', 5, 1, 4);