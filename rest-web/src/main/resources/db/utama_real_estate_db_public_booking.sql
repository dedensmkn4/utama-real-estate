create table booking
(
    id               bigserial    not null
        constraint booking_pkey
            primary key,
    customer_name    varchar(100) not null,
    customer_address varchar(100) not null,
    final_price      double precision,
    house_id         bigint,
    store_id         varchar,
    is_deleted       integer,
    created_date     date,
    created_by       varchar,
    version          bigint,
    updated_by       varchar,
    updated_date     date
);

alter table booking
    owner to postgres;

INSERT INTO public.booking (id, customer_name, customer_address, final_price, house_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (1, 'agung', 'bandung', 200, 1, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.booking (id, customer_name, customer_address, final_price, house_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (2, 'agung', 'bandung', 200, 11, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');