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

