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
     discount_id                INT references discount(discount_id)
);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,discount_id)
VALUES ('comment', 1, 1, 1, 1);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,discount_id)
VALUES ('comment2', 2, 2, 1, 1);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,discount_id)
VALUES ('comment3', 3, 3, 1, 1);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,discount_id)
VALUES ('comment4', 4, 4, 1, 1);
INSERT INTO orders (comment_order,user_id, delivery_information_id, id_type,discount_id)
VALUES ('comment5', 5, 5, 1, 1);
