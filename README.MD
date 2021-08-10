Provides access to Postgres DB and allows you to create,
read and modify user data:
- FIO
- Birth Date
- User Name 
- email
- Foto

Use: SpringBoot, Jpa, Postgres DB

###  What needs to prepare
- make docker image
> docker pull postgres

- if you want to configure Postgres config file
> docker run -i --rm postgres cat /usr/share/postgresql/postgresql.conf.sample > my-postgres.conf

- change my-postgres.conf

- run postgres with new custom config
> docker run -d --name postgres -p 5432:5432 -v "$PWD/my-postgres.conf":/etc/postgresql/postgresql.conf -e POSTGRES_PASSWORD=123456 postgres -c 'config_file=/etc/postgresql/postgresql.conf'

- creating a database in a container

> docker exec -it postgres bash

> psql -U postgres

> CREATE DATABASE UNIKOOM;

- create table

> CREATE TABLE person (
>     id SERIAL PRIMARY KEY NOT NULL,
>     user_name text,
>     fio text,
>     email text,
>     birth_date date,
>     sex boolean);
 
> CREATE SEQUENCE JPA_SEQUENCE START WITH 1 INCREMENT BY 1 NO CYCLE;

- Optionally, you can set a couple of values.

> insert into person (id, user_name, fio, email, birth_date, sex)
> VALUES(1, 'petr','Petr Avakin', 'petr@mail.ru', '1990-01-05', true);
 
> insert into person (id, user_name, fio, email, birth_date, sex)
> VALUES(2, 'olga','Olga Avakina', 'olga@mail.ru', '1990-12-01', false);