CREATE TABLE products_in_order (

  id              BIGSERIAL NOT NULL PRIMARY KEY,
  order_id        BIGINT references orders(order_id),
  product_id      BIGINT  references products(product_id),
  count           INT NOT  NULL
  );

      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (1, 2, 1);
      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (2, 2, 1);
          INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (1, 1, 1);
      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (2, 3, 1);
      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (3, 2, 1);
      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (4, 2, 1);
      INSERT INTO products_in_order (order_id,product_id, count )
    VALUES (5, 2, 1);
