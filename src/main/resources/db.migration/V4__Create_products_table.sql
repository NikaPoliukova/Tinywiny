CREATE TABLE products
(
   product_id       BIGSERIAL NOT NULL PRIMARY KEY,
   product_name     VARCHAR NOT NULL,
   price            INT NOT NULL,
   description       TEXT,
   count_in_stock    INT,
   id_type           INT NOT NULL REFERENCES type_product (id_type),
   image_id          BIGINT REFERENCES image (image_id)

  );

INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy1', 125, 'its cool', 1, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('rainbow', 125, 'Это круто', 1, 1);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy2', 105, 'Это круто', 3, 2);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy1', 125, 'Это круто', 1, 3);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy5', 125, 'Это круто', 1, 1);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('flags', 125, 'Это круто', 1, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy85', 125, 'Это круто', 8, 2);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('wigth', 125, 'Это круто', 2, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('flags', 125, 'Это круто', 1, 4);