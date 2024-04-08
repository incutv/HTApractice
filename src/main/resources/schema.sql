DROP TABLE IF EXISTS notice;

CREATE TABLE notice(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(30),
    content VARCHAR(100),
    who VARCHAR(30),
    views INT DEFAULT 0,
    createDate TIMESTAMP NOT NULL,
    updateDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS shedlock (
    name VARCHAR(64)            NOT NULL,
    lock_until TIMESTAMP        NOT NULL,
    locked_at TIMESTAMP         NOT NULL,
    locked_by VARCHAR(255)      NOT NULL,
    PRIMARY KEY (name)
);