create table house
(
    id               bigserial        not null
        constraint property_pkey
            primary key,
    offer_type       varchar(20)      not null,
    offer_cost       double precision not null,
    area_square_foot double precision,
    address          varchar(200),
    street           varchar(200),
    city             varchar(200),
    status           varchar(200),
    broker_id        bigint,
    store_id         varchar,
    is_deleted       integer,
    created_date     date,
    created_by       varchar,
    version          bigint,
    updated_by       varchar,
    updated_date     date
);

alter table house
    owner to postgres;

INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (1, 'BUY', 200, 100, 'ciranjang', 'ciranjang', 'cianjur', 'ACTIVE', null, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (2, 'BUY', 200, 100, 'ciranjang', 'ciranjang', 'cianjur', 'ACTIVE', 14, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (4, 'BUY', 100, 100, 'ciranjang', 'ciranjang', 'cianjur', 'ACTIVE', null, 'UTAMA', 1, null, null, 1, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (3, 'BUY', 200, 100, 'ciranjang', 'ciranjang', 'cianjur', 'ACTIVE', 13, 'UTAMA', 1, '2021-07-12', 'system', 2, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (6, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 13, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (7, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 13, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (10, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 12, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (5, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 12, 'UTAMA', 0, null, null, 1, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (8, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 12, 'UTAMA', 1, '2021-07-12', 'system', 1, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (9, 'BUY', 100, 100, 'ciranjang 2', 'ciranjang', 'cianjur', 'ACTIVE', 12, 'UTAMA', 1, '2021-07-12', 'system', 1, 'system', '2021-07-12');
INSERT INTO public.house (id, offer_type, offer_cost, area_square_foot, address, street, city, status, broker_id, store_id, is_deleted, created_date, created_by, version, updated_by, updated_date) VALUES (11, 'BUY', 100, 100, 'ciranjang 10', 'ciranjang', 'cianjur', 'ACTIVE', 13, 'UTAMA', 0, '2021-07-12', 'system', 0, 'system', '2021-07-12');