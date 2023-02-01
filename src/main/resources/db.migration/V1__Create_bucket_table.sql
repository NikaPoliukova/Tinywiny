create table bucket
(
    bucket_id   BIGSERIAL NOT NULL PRIMARY KEY,
    user_id     BIGINT NOT NULL
    );
/*
INSERT INTO bucket (bucket_id, user_id)
VALUES (1,1);
INSERT INTO bucket (bucket_id, user_id)
VALUES (2,2);
INSERT INTO bucket (bucket_id, user_id)
VALUES (3,3);
INSERT INTO bucket (bucket_id, user_id)
VALUES (4,4);
INSERT INTO bucket (bucket_id, user_id)
VALUES (5,5);
INSERT INTO bucket (bucket_id, user_id)
VALUES (6,6);
INSERT INTO bucket (bucket_id, user_id)
VALUES (7,7);


INSERT INTO bucket (user_id)
VALUES (1);
INSERT INTO bucket (user_id)
VALUES (2);
INSERT INTO bucket (user_id)
VALUES (3);
INSERT INTO bucket (user_id)
VALUES (4);
INSERT INTO bucket (user_id)
VALUES (5);
INSERT INTO bucket (user_id)
VALUES (6);
INSERT INTO bucket(user_id)
VALUES (7);
 */