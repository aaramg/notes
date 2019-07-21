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

INSERT INTO note ("id", "title", "description", "user_id", "created", "updated")
VALUES (-1, 'Title -1', 'Description -1', -1, current_timestamp, current_timestamp);

INSERT INTO note ("id", "title", "description", "user_id", "created", "updated")
VALUES (-2, 'Title -2', 'Description -2', -2, current_timestamp, current_timestamp);
