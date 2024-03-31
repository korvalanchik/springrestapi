-- ----------------------------
-- Table structure for note
-- ----------------------------
CREATE TABLE note (
    id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title varchar(200) NOT NULL,
    content varchar(500) NOT NULL,
    date_created datetime,
    last_updated datetime
);