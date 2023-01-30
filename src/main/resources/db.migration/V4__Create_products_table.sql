create table type_product
(
    id_type      BIGSERIAL PRIMARY KEY,
    type_name    varchar
    );
CREATE TABLE products
(
   product_id       BIGSERIAL  PRIMARY KEY,
   product_name     VARCHAR NOT NULL,
   price            INT NOT NULL,
   description       TEXT,
   count_in_stock    INT,
   id_type           INT NOT NULL REFERENCES type_product (id_type),
   image_id          BIGINT REFERENCES image (image_id)

  );
