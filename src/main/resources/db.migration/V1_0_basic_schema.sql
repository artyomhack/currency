create schema if not exists money;

create table if not exists money.currency (
    id serial primary key,
    code varchar(3) not null,
    price NUMERIC not null
);

create table if not exists money.exchange_currency(
    id serial primary key,
    base_id int not null ,
    target_id int not null ,
    rate decimal(6) not null,

    constraint exchange_currency_fk_baseId foreign key (base_id)
        references money.currency(id) on update cascade on delete restrict,
    constraint exchange_currency_fk_targetId foreign key (target_id)
        references money.currency(id) on update cascade on delete restrict
);

