# create table roles (id bigint not null auto_increment, name varchar(255), primary key (id));
# create table users (id bigint not null auto_increment, age integer not null, name varchar(255), password varchar(255), surname varchar(255), username varchar(255), primary key (id));
# create table users_roles (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id));
# alter table users add constraint unique (username);
# alter table users_roles add constraint foreign key (role_id) references roles (id);
# alter table users_roles add constraint foreign key (user_id) references users (id);