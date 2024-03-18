create schema if not exists money;

create table if not exists money.currency (
    id serial primary key,
    code varchar not null unique ,
    full_name varchar not null,
    sign varchar not null
);

create table if not exists money.exchange_currency(
    id serial primary key,
    base_id int not null unique,
    target_id int not null unique,
    rate decimal(6) not null,

    constraint exchange_currency_fk_baseId foreign key (base_id)
        references money.currency(id),
    constraint exchange_currency_fk_targetId foreign key (target_id)
        references money.currency(id)
);

