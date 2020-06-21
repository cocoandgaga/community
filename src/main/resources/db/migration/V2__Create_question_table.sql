create table QUESTION
(
    ID           BIGINT auto_increment PRIMARY  KEY,
    TITLE        VARCHAR(50),
    DESCRIPTION  TEXT,
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    CREATOR      BIGINT,
    VIEW_CNT     INT default 0,
    LIKE_CNT     INT default 0,
    COMMENT_CNT  INT default 0,
    TAG          VARCHAR(255)
);

