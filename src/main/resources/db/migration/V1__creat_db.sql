-- ----------------------------
-- Table structure for note
-- ----------------------------
CREATE TABLE note (
    id bigint NOT NULL PRIMARY KEY,
    user_id bigint NOT NULL,
    title varchar(200) NOT NULL,
    content varchar(500) NOT NULL,
    date_created datetime,
    last_updated datetime
);

INSERT INTO note VALUES ( 1, 3, 'My first note', 'I took out loan: $1000', '2024-01-31 10:51:56', '2024-01-31 10:51:56');
INSERT INTO note VALUES ( 2, 3, 'My second note', 'I took out loan: $2000', '2024-02-21 10:51:56', '2024-02-21 10:51:56');
INSERT INTO note VALUES ( 3, 3, 'My third note', 'I returned the loan: $3000', '2024-03-21 10:51:56', '2024-03-21 10:51:56');

