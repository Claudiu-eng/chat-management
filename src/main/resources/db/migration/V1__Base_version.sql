create table message
(
    seen      bit          null,
    timestamp bigint       null,
    id        binary(16)   not null
        primary key,
    receiver  varchar(255) null,
    sender    varchar(255) null,
    text      varchar(255) null
);

