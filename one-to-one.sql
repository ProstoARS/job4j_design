create table medical_card(
id serial primary key,
number integer,
description text);

create table person(
id serial primary key,
fname varchar(255),
lname varchar(255));

create table person_medical_card(
person_id int references person(id) unique,
medical_card_id int references medical_card(id) unique);