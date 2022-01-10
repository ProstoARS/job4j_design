create database example_join;

create table departaments(
id serial primary key,
name varchar(255));

create table emploers(
name varchar(255),
dep_id int references departaments(id));

insert into departaments(name) values('Лукоил');
insert into departaments(name) values('Кириши');
insert into departaments(name) values('Газпром');
insert into departaments(name) values('Хачик нефть');
insert into departaments(name) values('Узбек сервис');

insert into emploers(name, dep_id) values('Петров', 2);
insert into emploers(name, dep_id) values('Иванов', 2);
insert into emploers(name, dep_id) values('Сидоров', 2);
insert into emploers(name, dep_id) values('Цукерман', 3);
insert into emploers(name, dep_id) values('Лавринович', 3);
insert into emploers(name, dep_id) values('Йоффе', 3);
insert into emploers(name, dep_id) values('Зильбер', 3);
insert into emploers(name, dep_id) values('Левандовский', 1);
insert into emploers(name, dep_id) values('Вуйчик', 1);
insert into emploers(name, dep_id) values('Ковальский', 1);
insert into emploers(name, dep_id) values('Пурген', 1);
insert into emploers(name) values('Стасюк');
insert into emploers(name) values('Кашкин');

select d.name, e.name from departaments d left join emploers e on e.dep_id = d.id;

select d.name, e.name from departaments d right join emploers e on e.dep_id = d.id;

select d.name, e.name from departaments d full join emploers e on e.dep_id = d.id;

select d.name, e.name from departaments d cross join emploers e;

select d.name, e.name from departaments d left join emploers e on e.dep_id = d.id where e.dep_id is null;

select e.name, d.name from emploers e left join departaments d on e.dep_id = d.id;

select e.name, d.name from departaments d right join emploers e on e.dep_id = d.id;

create table teens(
id serial primary key, 
name varchar(255), 
gender char);

insert into teens(name, gender) values('Серёжа', 'м');
insert into teens(name, gender) values('Маша', 'ж');
insert into teens(name, gender) values('Петя', 'м');
insert into teens(name, gender) values('Таня', 'ж');
insert into teens(name, gender) values('Коля', 'м');
insert into teens(name, gender) values('Федя', 'м');
insert into teens(name, gender) values('Женя', 'ж');
insert into teens(name, gender) values('Наташа', 'ж');
insert into teens(name, gender) values('Матвей', 'м');
insert into teens(name, gender) values('Ульяна', 'ж');
insert into teens(name, gender) values('Платон', 'м');

select m.name as man, w.name as woman from teens m, teens w where m.gender = 'м' and w.gender = 'ж';

