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
 
 
insert into type(name) values ('�������� ��������');
insert into type(name) values ('������������� �������');
insert into type(name) values ('���');
insert into type(name) values ('����');
insert into type(name) values ('�������');

insert into product(name, type_id, expired_date, price) values ('������', 3, date '2021-12-31', 895);
insert into product(name, type_id, expired_date, price) values ('����������', 3, date '2021-12-3', 355);
insert into product(name, type_id, expired_date, price) values ('�������', 3, date '2022-02-01', 1355);
insert into product(name, type_id, expired_date, price) values ('��������', 3, date '2022-01-05', 1530);
insert into product(name, type_id, expired_date, price) values ('������', 1, date '2021-12-20', 85);
insert into product(name, type_id, expired_date, price) values ('������', 1, date '2022-01-10', 130);
insert into product(name, type_id, expired_date, price) values ('�����', 1, date '2021-12-26', 70);
insert into product(name, type_id, expired_date, price) values ('����� "���������"', 2, date '2021-12-29', 47);
insert into product(name, type_id, expired_date, price) values ('���� "���������"', 2, date '2022-01-04', 58);
insert into product(name, type_id, expired_date, price) values ('���� "���������"', 2, date '2021-12-23', 41);
insert into product(name, type_id, expired_date, price) values ('����� "��������"', 2, date '2022-01-12', 35);
insert into product(name, type_id, expired_date, price) values ('��������', 4, date '2022-01-17', 750);
insert into product(name, type_id, expired_date, price) values ('�������', 4, date '2022-01-03', 450);
insert into product(name, type_id, expired_date, price) values ('��������', 4, date '2021-12-22', 500);
insert into product(name, type_id, expired_date, price) values ('������', 4, date '2022-01-08', 322);
insert into product(name, type_id, expired_date, price) values ('��������� "����������"', 5, date '2022-01-01', 85);
insert into product(name, type_id, expired_date, price) values ('��������� "��������"', 5, date '2021-12-30', 110);
insert into product(name, type_id, expired_date, price) values ('��������� "�������� ��������"', 5, date '2021-12-28', 85);
insert into product(name, type_id, expired_date, price) values ('��������� "�������"', 5, date '2021-12-24', 45);
insert into product(name, type_id, expired_date, price) values ('��������� "��������� ���������"', 5, date '2022-01-02', 63);
insert into product(name, type_id, expired_date, price) values ('���� "��������� ����"', 5, date '2022-01-15', 430);

select * from product join type on product.type_id = type.id where type.name = '���';

select * from product where name like '%���������%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(p.type_id) from product p join type t on p.type_id = t.id group by t.name;

select * from product p join type t on p.type_id = t.id where t.name = '�������� ��������' or t.name = '���';

select t.name from product p join type t on p.type_id = t.id group by t.name having count(p.type_id) < 5;

select * from product p join type t on p.type_id = t.id;