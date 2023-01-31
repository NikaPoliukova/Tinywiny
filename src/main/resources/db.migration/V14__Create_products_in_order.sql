CREATE TABLE products_in_order (

  id              BIGSERIAL NOT NULL PRIMARY KEY,
  order_id        BIGINT references orders(order_id),
  product_id      BIGINT  references products(product_id),
  count           INT NOT NULL
  );