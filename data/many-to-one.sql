create table product(
id serial primary key,
name varchar(255),
description text,
price integer);

create table product_photo(
id serial primary key,
url varchar(255),
product_id int references product(id));
