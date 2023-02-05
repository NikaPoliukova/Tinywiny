CREATE TABLE delivery_information (

  delivery_information_id         BIGSERIAL NOT NULL PRIMARY KEY,
  customer_name                   VARCHAR NOT NULL,
  customer_last_name               VARCHAR NOT NULL,
  customer_surname                VARCHAR NOT NULL,
  address_delivery                 VARCHAR,
  user_id                          BIGINT NOT NULL references users(user_id)
    );

INSERT INTO delivery_information (customer_name, customer_last_name,customer_surname,address_delivery,user_id)
VALUES ('Nika', 'Nika', 'Nika', 'City', 1);
INSERT INTO delivery_information (customer_name, customer_last_name,customer_surname,address_delivery,user_id)
VALUES ('Nika', 'Nika', 'Nika', 'City', 2);
INSERT INTO delivery_information (customer_name, customer_last_name,customer_surname,address_delivery,user_id)
VALUES ('Nika', 'Nika', 'Nika', 'City', 3);
INSERT INTO delivery_information (customer_name, customer_last_name,customer_surname,address_delivery,user_id)
VALUES ('Nika', 'Nika', 'Nika', 'City', 4);
INSERT INTO delivery_information (customer_name, customer_last_name,customer_surname,address_delivery,user_id)
VALUES ('Nika', 'Nika', 'Nika', 'City', 5);