CREATE TABLE post_offices
 (
  id_post_office        BIGSERIAL NOT NULL PRIMARY KEY,
  post_address          VARCHAR NOT NULL,
  id_type              BIGINT NOT NULL references delivery_type(id_type)

  );
  INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 1', 2);
    INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 2', 2);
    INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 3', 2);
    INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 4', 2);
    INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 5', 2);
    INSERT INTO post_offices (post_address, id_type)
  VALUES ('Улица 6', 2);