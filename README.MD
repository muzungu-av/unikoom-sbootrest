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
> docker pull postgres:13

- if you want to configure Postgres config file
> docker run -i --rm postgres:13 cat /usr/share/postgresql/postgresql.conf.sample > my-postgres.conf

- change my-postgres.conf

- run postgres with new custom config
> docker run -d --name postgres
> -p 5432:5432
> -v "$PWD/my-postgres.conf":/etc/postgresql/postgresql.conf
> -v /DATA/tmp_dockers_mount/postgres:/var/lib/postgresql/data
> -e POSTGRES_PASSWORD=123456
> -e PGDATA=/var/lib/postgresql/data
> postgres:13
> -c 'config_file=/etc/postgresql/postgresql.conf'

- creating a database in a container

> docker exec -it postgres bash

> psql -U postgres

> CREATE DATABASE UNIKOOM;

- create tables
 
> CREATE TABLE person ( \
>   id SERIAL PRIMARY KEY NOT NULL, \
>   user_name text NOT NULL, \
>   fio text, \
>   email text NOT NULL, \
>   birth_date date, \
>   sex boolean, \
>   UNIQUE (user_name), \
>   UNIQUE (email));
 
> CREATE SEQUENCE JPA_SEQUENCE START WITH 1 INCREMENT BY 1 NO CYCLE;

> CREATE TABLE foto ( \
>   id SERIAL PRIMARY KEY NOT NULL, \
>   file_hash text NOT NULL, \
>   file_name text NOT NULL, \
>   person_id int NOT NULL, \
>   UNIQUE (file_hash, person_id), \
>   CONSTRAINT person_id \
>   FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE)

- Optionally, you can set a couple of values.

> INSERT INTO person (id, user_name, fio, email, birth_date, sex) \
> VALUES (1, 'petr','Petr Avakin', 'petr@mail.ru', '1990-01-05', true);
 
> INSERT INTO person (id, user_name, fio, email, birth_date, sex) \
> VALUES (2, 'olga','Olga Avakina', 'olga@mail.ru', '1990-12-01', false);

