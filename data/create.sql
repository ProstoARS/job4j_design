create table rules(
id serial primary key,
name varchar(255),
description text);

create table role(
id serial primary key,
name varchar(255));

create table role_rules(
role_id int references role(id),
rules_id int references rules(id));

create table users(
id serial primary key,
fname varchar(255),
lname varchar(255),
email varchar(30),
role_id int references role(id));

create table state(
id serial primary key,
name varchar(255));

create table category(
id serial primary key,
name varchar(255),
description text);

create table item(
id serial primary key,
name varchar(255),
description text,
users_id int references users(id),
category_id int references category(id),
state_id int references state(id));

create table comments(
id serial primary key,
description text,
item_id int references item(id));

create table attachs(
id serial primary key,
file_name varchar(255),
url text,
item_id int references item(id));

