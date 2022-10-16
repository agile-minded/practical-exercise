create schema vector;

create table vector.users (
email varchar not null primary key,
password varchar not null,
firstname varchar,
lastname varchar
);