CREATE TABLE products_in_orders (

  id                     BIGSERIAL NOT NULL PRIMARY KEY,
  product_id             BIGINT references products(product_id),
  order_id               BIGINT references orders(order_id)
  );