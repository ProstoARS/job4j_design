create table product(
id serial primary key,
name varchar(255),
description text,
price integer);

create table consumer(
id serial primary key,
fname varchar(255),
lname varchar(255));

create table consumer_product(
consumer_id int references consumer(id),
product_id int references product(id));