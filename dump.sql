create database myStore;
use myStore;


drop table if not exists users;

create table users(
	id int primary key auto_increment,
    login varchar(30),
    pass varchar(10)
);
create table goods(
	id int primary key auto_increment,
    name varchar(30),
    imgStr varchar(10),
    price int,
    capacity int
);
create table orders(
	id int primary key auto_increment,
    login varchar(10),
    adress varchar(20),
    email varchar(50),
    name varchar(30),
    price int,
    capacity int
);

use myStore;
insert into goods(name,imgStr,price,capacity)values('Iphone 7','ip7.png',322,32);
insert into goods(name,imgStr,price,capacity)values('Iphone 6s','ip6s.png',123,64);
insert into goods(name,imgStr,price,capacity)values('Iphone 8','ip8.png',156,128);
insert into goods(name,imgStr,price,capacity)values('Iphone 10','ip10.png',123,32);
insert into goods(name,imgStr,price,capacity)values('Iphone Se','ipse.png',23,64);



select * from goods;

select * from users;	

select * from orders;