create database carstore;
\c carstore;
create table engine(
    id serial primary key,
    name text
);
create table body(
    id serial primary key,
    name text
);
create table gearbox(
    id serial primary key,
    name text
);
create table vehicle(
    id serial primary key,
    name text,
    eng_id int references engine(id),
    body_id int references body(id),
    gbox_id int references gearbox(id)
);