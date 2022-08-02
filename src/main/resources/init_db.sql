create table if not exists categories(
    id bigserial NOT NULL,
    name text,
    description text
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
),
(
    'categoryName6',
    'Description6'
),
(
    'categoryName7',
    'Description7'
),
(
    'categoryName8',
    'Description8'
),
(
    'categoryName9',
    'Description9'
),
(
    'categoryName10',
    'Description10'
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
    operation_type,
    sum,
    date,
    description
)
values
    (

    ),