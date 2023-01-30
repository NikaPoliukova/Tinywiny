CREATE TABLE delivery_information (

  delivery_information_id         BIGSERIAL NOT NULL PRIMARY KEY,
  customer_name                   VARCHAR NOT NULL,
  customer_last_name               VARCHAR NOT NULL,
  customer_surname                VARCHAR NOT NULL,
  address_delivery                 VARCHAR NOT NULL,
  user_id                          BIGINT NOT NULL references users(user_id)

    );
