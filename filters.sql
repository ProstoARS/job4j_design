create database universam;

 create table type(
 id serial primary key, 
 name varchar(255));
 
 create table product(
 id serial primary key,
 name varchar(255),
 type_id int references type(id),
 expired_date timestamp,
 price float);
 
 
insert into type(name) values ('Молочные продукты');
insert into type(name) values ('Хлебобулочные изделия');
insert into type(name) values ('Сыр');
insert into type(name) values ('Мясо');
insert into type(name) values ('Десерты');

insert into product(name, type_id, expired_date, price) values ('Ламбер', 3, date '2021-12-31', 895);
insert into product(name, type_id, expired_date, price) values ('Российский', 3, date '2021-12-3', 355);
insert into product(name, type_id, expired_date, price) values ('Массдам', 3, date '2022-02-01', 1355);
insert into product(name, type_id, expired_date, price) values ('Пармезан', 3, date '2022-01-05', 1530);
insert into product(name, type_id, expired_date, price) values ('Молоко', 1, date '2021-12-20', 85);
insert into product(name, type_id, expired_date, price) values ('Йогурт', 1, date '2022-01-10', 130);
insert into product(name, type_id, expired_date, price) values ('Кефир', 1, date '2021-12-26', 70);
insert into product(name, type_id, expired_date, price) values ('Батон "Городской"', 2, date '2021-12-29', 47);
insert into product(name, type_id, expired_date, price) values ('Хлеб "Дарницкий"', 2, date '2022-01-04', 58);
insert into product(name, type_id, expired_date, price) values ('Хлеб "Столичный"', 2, date '2021-12-23', 41);
insert into product(name, type_id, expired_date, price) values ('Батон "Нарезной"', 2, date '2022-01-12', 35);
insert into product(name, type_id, expired_date, price) values ('Баранина', 4, date '2022-01-17', 750);
insert into product(name, type_id, expired_date, price) values ('Свинина', 4, date '2022-01-03', 450);
insert into product(name, type_id, expired_date, price) values ('Говядина', 4, date '2021-12-22', 500);
insert into product(name, type_id, expired_date, price) values ('Курица', 4, date '2022-01-08', 322);
insert into product(name, type_id, expired_date, price) values ('Пироженое "Корзиночка"', 5, date '2022-01-01', 85);
insert into product(name, type_id, expired_date, price) values ('Пироженое "Наполеон"', 5, date '2021-12-30', 110);
insert into product(name, type_id, expired_date, price) values ('Мороженое "Сахарная трубочка"', 5, date '2021-12-28', 85);
insert into product(name, type_id, expired_date, price) values ('Мороженое "Пломбир"', 5, date '2021-12-24', 45);
insert into product(name, type_id, expired_date, price) values ('Мороженое "Вафельный стаканчик"', 5, date '2022-01-02', 63);
insert into product(name, type_id, expired_date, price) values ('Торт "Трухлявый пень"', 5, date '2022-01-15', 430);

select * from product join type on product.type_id = type.id where type.name = 'Сыр';

select * from product where name like '%Мороженое%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(p.type_id) from product p join type t on p.type_id = t.id group by t.name;

select * from product p join type t on p.type_id = t.id where t.name = 'Молочные продукты' or t.name = 'Сыр';

select t.name from product p join type t on p.type_id = t.id group by t.name having count(p.type_id) < 5;

select * from product p join type t on p.type_id = t.id;