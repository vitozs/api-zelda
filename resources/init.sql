create table users(
	id serial primary key,
	name varchar(50) not null,
	age int not null
);

insert into users(name, age) values('Artur', 33), ('Lorenzo', 21);
