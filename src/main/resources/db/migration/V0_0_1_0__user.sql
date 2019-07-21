create table if not exists "user"
(
    id            bigint       not null
        constraint user_pkey
            primary key,
    created       timestamp    not null,
    updated       timestamp    not null,
    email         varchar(255) not null
        constraint email_uk
            unique,
    password_hash varchar(255) not null
);

create sequence if not exists user_sequence
    increment by 50;

