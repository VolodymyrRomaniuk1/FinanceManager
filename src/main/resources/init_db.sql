create table if not exists categories(
    id bigserial NOT NULL,
    name text UNIQUE NOT NULL,
    description text,
    PRIMARY KEY (id)
);
    select setval('categories_id_seq', (select max(id) from public.categories));
    insert into
    categories
(
    name,
    description
)
    values
(
    'categoryName1',
    'Description1'
),
(
    'categoryName2',
    'Description2'
),
(
    'categoryName3',
    'Description3'
),
(
    'categoryName4',
    'Description4'
),
(
    'categoryName5',
    'Description5'
);

create table if not exists public.transactions
(
    id bigserial NOT NULL,
    category_id bigint NOT NULL,
    operation_type text NOT NULL,
    sum double precision NOT NULL,
    date date NOT NULL,
    description text,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
    );

ALTER TABLE IF EXISTS public.transactions
    OWNER to postgres,
    ADD CONSTRAINT check_operation_type
    CHECK (operation_type = 'Spending' OR operation_type = 'Gain');


select setval('transactions_id_seq', (select max(id) from public.transactions));
insert into
    transactions
(
    category_id,
    operation_type,
    sum,
    date,
    description
)
values
    (
        4,
        'Spending',
        93.0,
        '2022-08-01',
        'Description 1'
    ),
    (
        1,
        'Spending',
        10.0,
        '2022-08-02',
        'Description 2'
    ),
    (
        2,
        'Gain',
        500.0,
        '2022-08-03',
        'Description 3'
    )