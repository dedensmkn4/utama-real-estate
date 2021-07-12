create table broker
(
    id           bigserial    not null
        constraint broker_pkey
            primary key,
    broker_code  varchar(20)  not null,
    broker_name  varchar(100) not null,
    store_id     varchar,
    is_deleted   integer,
    created_date date,
    created_by   varchar,
    version      bigint,
    updated_by   varchar,
    updated_date date
);

alter table broker
    owner to postgres;

INSERT INTO public.broker (id, broker_code, broker_name, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (13, 'BKRAB', 'Jajang Test', 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.broker (id, broker_code, broker_name, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (12, 'BKRA', 'Jajang Test', 'UTAMA', 0, '2021-07-11', 'username', 4, 'system', '2021-07-12');
INSERT INTO public.broker (id, broker_code, broker_name, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (15, 'BRKAB', 'JAJANG', 'UTAMA', 0, '2021-07-12', 'system', 3, 'system', '2021-07-12');