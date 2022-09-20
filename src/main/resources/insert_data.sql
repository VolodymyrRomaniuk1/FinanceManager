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

insert into users(username, password, role)
values
    (
        'user',
        '$2a$12$BwLMuhfm6tGFK.5DO8e.nuskvUEL7WE86aElJMalwGLt79R0Es4Yu',
        'USER'
    ),
    (
        'admin',
        '$2a$12$RCiBOWr8brCM90fsDBAfWeEioJ0R.bQLiSkKKeRRN51X1R.xUUFNi',
        'ADMIN'
    )