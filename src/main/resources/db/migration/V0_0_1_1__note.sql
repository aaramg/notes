create table if not exists note
(
    id          bigint       not null
        constraint note_pkey
            primary key,
    created     timestamp    not null,
    updated     timestamp    not null,
    description varchar(255),
    title       varchar(255) not null,
    user_id     bigint
        constraint user_id_fk
            references "user"
);

create sequence if not exists note_sequence
    increment by 50;
