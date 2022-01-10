create table body(
	id serial primary key,
	name varchar(255)
);

create table engine(
	id serial primary key,
	name varchar(255)
);

create table transmission(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body (name) values ('седан');
insert into body (name) values ('универсал');
insert into body (name) values ('хетчбек');

insert into engine (name) values ('атмосферный бензин 1.4');
insert into engine (name) values ('атмосферный бензин 1.6');
insert into engine (name) values ('турбо бензин 2.0');
insert into engine (name) values ('турбо дизель 2.0');

insert into transmission (name) values ('ручная');
insert into transmission (name) values ('автоматическая');
insert into transmission (name) values ('робот');

insert into car (name, body_id, engine_id, transmission_id)
values ('Skoda Octavia', 1, 2, 2);
insert into car (name, body_id, engine_id, transmission_id)
values ('Toyota Corolla', 1, 1, 1);
insert into car (name, body_id, engine_id, transmission_id)
values ('Saab 9-5', 2, 3, 2);

select car.name as car,
b.name as body,
e.name as engine,
t.name as transmission
from car
join body b
on car.body_id = b.id
join engine e
on car.engine_id = e.id
join transmission t
on car.transmission_id = t.id;

select b.name as unused_body
from body b
left join car c
on c.body_id = b.id
where c.body_id is null;

select e.name as unused_engine
from engine e
left join car c
on c.engine_id = e.id
where c.engine_id is null;

select t.name as unused_transmission
from transmission t
left join car c
on c.transmission_id = t.id
where c.transmission_id is null;