create table products (id bigserial, title varchar(255), price numeric, primary key(id));

create table users (username varchar(50) primary key, password varchar(100) not null, enabled boolean not null);

create table authorities (username varchar(50) not null, authority varchar(50) not null, foreign key (username) references users (username));

create table orders (id bigserial, username varchar(50), foreign key (username) references users (username), primary key (id));
alter table orders drop constraint orders_username_fkey;

create table order_positions (id bigserial, order_id bigint, product_id bigint, foreign key (order_id) references orders (id), foreign key (product_id) references products (id), primary key (id));

