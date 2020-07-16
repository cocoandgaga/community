-- auto-generated definition
create table NOTIFICATION
(
    ID         BIGINT auto_increment,
    NOTIFIER   BIGINT        not null,
    RECEIVER   BIGINT        not null,
    "orderId"  BIGINT        not null,
    TYPE       INT           not null,
    GMT_CREATE BIGINT        not null,
    STATUS     INT default 0 not null,
    constraint NOTIFICATION_PK
        primary key (ID)
);

