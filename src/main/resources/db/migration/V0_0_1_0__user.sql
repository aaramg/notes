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

INSERT INTO "user" ("id", "email", "password_hash", "created", "updated")
VALUES (-1, 'aaramg@gmail.com', '$2a$12$.lR3Zmi4ZLUInYyS9dsKSOMINGItl.5VI2gFoaQ2bGHCv5bWTrccG', current_timestamp,
        current_timestamp);

INSERT INTO "user" ("id", "email", "password_hash", "created", "updated")
VALUES (-2, 'aaramg1@gmail.com', '$2a$12$.lR3Zmi4ZLUInYyS9dsKSOMINGItl.5VI2gFoaQ2bGHCv5bWTrccG', current_timestamp,
        current_timestamp);
