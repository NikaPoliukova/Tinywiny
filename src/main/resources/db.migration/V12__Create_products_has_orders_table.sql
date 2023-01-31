CREATE TABLE product_in_bucket (

  id                     BIGSERIAL NOT NULL PRIMARY KEY,
  bucket_id             BIGINT references bucket(bucket_id),
  product_id               BIGINT references products(product_id),
  count                 INT NOT NULL
  );