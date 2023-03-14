CREATE TABLE products_in_bucket (

  id                     BIGSERIAL NOT NULL PRIMARY KEY,
  bucket_id             BIGINT references bucket(bucket_id),
  product_id               BIGINT references products(product_id),
  count                 INT NOT NULL
  );

INSERT INTO products_in_bucket (bucket_id,product_id, count)
VALUES (1, 2, 8);
 INSERT INTO products_in_bucket (bucket_id,product_id, count)
VALUES (2, 2, 1);
INSERT INTO products_in_bucket (bucket_id,product_id, count)
VALUES (3, 1, 1);
 INSERT INTO products_in_bucket (bucket_id,product_id, count)
VALUES (3, 1, 1);
 INSERT INTO products_in_bucket (bucket_id,product_id, count)
VALUES (2, 2, 1);