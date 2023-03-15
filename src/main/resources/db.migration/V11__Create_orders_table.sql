create table orders
(
     order_id                  BIGSERIAL NOT NULL PRIMARY KEY,
     comment_order              text,
     order_date                 TIMESTAMP NOT NULL DEFAULT now(),
     status_order               VARCHAR DEFAULT 'NEW',
     payment_status             VARCHAR DEFAULT 'NOT',
     user_id                    BIGINT NOT NULL references users(user_id),
     delivery_information_id    BIGINT NOT NULL references delivery_information(delivery_information_id),
     id_type                    INT NOT NULL references delivery_type(id_type),
     sum                        INT NOT NULL
);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,sum)
VALUES ('comment', 1, 1, 1, 125);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,sum)
VALUES ('comment2', 2, 2, 1, 35);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,sum)
VALUES ('comment3', 3, 3, 1, 25);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,sum)
VALUES ('comment4', 4, 4, 1, 100);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,sum)
VALUES ('comment5', 5, 5, 1, 100);
