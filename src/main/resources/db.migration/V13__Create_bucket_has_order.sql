CREATE TABLE bucket_has_order (

  id              BIGSERIAL NOT NULL PRIMARY KEY,
  bucket_id       BIGINT references bucket(bucket_id),
  product_id      BIGINT references users(user_id),
  count           INT
  );