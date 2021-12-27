create database group;

create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values (iPhone, 130000);
insert into devices(name, price) values (headphone, 15000);
insert into devices(name, price) values (iWotch, 25000);
insert into devices(name, price) values (macbook, 163000);

insert into people(name) values('Вася');
insert into people(name) values('Петя');
insert into people(name) values('Саша');
insert into people(name) values('Даша');

insert into devices_people(device_id, people_id) values (1, 1), (1, 3);
insert into devices_people(device_id, people_id) values (2, 2), (2, 4), (3,1), (4, 1), (4, 2), (4, 4);

select avg(price) from devices;

select p.name, avg(d.price) from devices_people dp 
join people p on dp.people_id = p.id 
join devices d on dp.device_id = d.id group by p.name;

select p.name, avg(d.price) from devices_people dp 
join people p on dp.people_id = p.id 
join devices d on dp.device_id = d.id group by p.name having avg(d.price) > 100000;

