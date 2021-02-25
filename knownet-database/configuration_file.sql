DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS users;


create table users
(
    id bigserial
        constraint users_pkey
            primary key,
    login varchar(255) not null,
    password varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    rating double precision default 0,
    points integer default 0,
    email varchar(255),
    vk_link varchar(255),
    telegram_link varchar(255),
    phone_number varchar(255),
    user_group varchar(255),
    attended_count integer default 0,
    conducted_count integer default 0
);

alter table users
    owner to knownet_user;

create unique index users_login_uindex
    on users (login);

create unique index users_email_uindex
    on users (email);


create table tags
(
    id bigserial
        constraint tags_pk
            primary key,
    title varchar(255),
    parent_id integer
);

alter table tags
    owner to knownet_user;


create table lessons
(
    id bigserial
        constraint lessons_pk
            primary key,
    teacher_id integer
        constraint lessons_users_id_fk
            references users,
    tag_id integer
        constraint lessons_tags_id_fk
            references tags,
    name varchar(255),
    topic varchar(255),
    difficulty double precision,
    points_to_get integer,
    description varchar(2000),
    skills_to_complete varchar(2000)
);

alter table lessons
    owner to knownet_user;


create table requests
(
    id bigserial
        constraint requests_pk
            primary key,
    teacher_id integer
        constraint requests_users_id_fk
            references users,
    student_id integer
        constraint requests_users_id_fk_2
            references users,
    hidden_for_teacher boolean default false,
    hidden_for_student boolean default false,
    is_finished boolean default false,
    status varchar(255) default 'LESSON_REQUESTED'::character varying,
    lesson_id integer
        constraint requests_lessons_id_fk
            references lessons
);

alter table requests
    owner to knownet_user;
