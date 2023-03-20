CREATE TABLE products
(
   product_id       BIGSERIAL NOT NULL PRIMARY KEY,
   product_name     VARCHAR NOT NULL,
   price            INT NOT NULL,
   description       TEXT,
   count_in_stock    INT,
   id_type           INT NOT NULL REFERENCES type_product (id_type)


  );

INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy1', 125, 'its cool', 1, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('флажки', 125, 'в прекрасном цвете', 1, 1);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('toy2', 105, 'Это круто', 3, 2);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('wings', 125, 'розовые', 1, 3);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('wings', 130, 'белые', 2, 3);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('цветочек', 30, 'Это круто', 1, 1);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('венок', 40, 'Красивый белый венок', 5, 1);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('грибки', 125, 'Это круто', 1, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('mobil', 125, 'В прекрасном цвете', 2, 2);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('фикус', 125, 'Это круто', 2, 4);
INSERT INTO products (product_name,price, description, count_in_stock, id_type )
VALUES ('flags', 125, 'Это круто', 1, 4);