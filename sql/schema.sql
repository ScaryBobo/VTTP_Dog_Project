drop database if exist dogDatabase;

create dogDatabase;

use dogDatabase;

create table user (
    user_id int(8) not null,
    username varchar(64) not null,
    password varchar(64) not null,
    email varchar(64) not null,
    primary key(user_id);
);

create table generated_images_tbl (
    generated_id int auto_increment, 
    user_id int (8) not null,
    username varchar (64) not null,
    imagesUrl varchar(256),
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,

    primary key(generated_id),
    constraint fk_user_id
    foreign key(user_id) 
    references user(user_id)
);