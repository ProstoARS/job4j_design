
create table Castomer (
    id serial primary key, 
    name text, age integer, sex boolean
);
insert into Castomer (name, age, sex) values ('Pablo', 38, true);
select * from Castomer;
update Castomer set name = 'Dimon';
update Castomer set age = 40;
delete from Castomer;
select * from Castomer;

