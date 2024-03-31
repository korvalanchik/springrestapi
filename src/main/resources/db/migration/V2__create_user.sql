-- ----------------------------
-- Table structure for user
-- ----------------------------
--DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
    id bigint NOT NULL PRIMARY KEY,
    date_created datetime NOT NULL,
    last_updated datetime NOT NULL,
    password varchar(255) NOT NULL,
    username varchar(100) NOT NULL
);
INSERT INTO "user" VALUES (1, '2024-01-01 10:51:56', '2024-01-01 10:51:56', '$2a$10$7pXT3Q6xnONfSdBNlxvqk.j4lc6dGi.TuSxRez7ddia6PyRKBn3T', 'foolishuser');
INSERT INTO "user" VALUES (2, '2024-01-01 11:51:56', '2024-01-01 11:51:56', '$2a$10$VlCGdMC01TUgNTM47J76T.fpr/L1/WAGimKhoRIKgUwKR1Zon4GE2', 'Vasya' );
INSERT INTO "user" VALUES (3, '2024-01-01 11:52:46', '2024-01-01 11:52:46','$2a$10$1jBFKkUnm52LnsoinvStS.Kbd5oVE233hICGS0fLM5n5hojIjF/zy', 'Valerii' );