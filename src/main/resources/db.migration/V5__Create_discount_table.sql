CREATE TABLE  discount
(
  discount_id      SERIAL NOT NULL PRIMARY KEY,
  size             INT NOT NULL,
  sum              INT NOT NULL
  );

  INSERT INTO discount (size,sum )
  VALUES (3, 50);
  INSERT INTO discount (size,sum )
  VALUES (5, 100);
  INSERT INTO discount (size,sum )
  VALUES (8, 150);
  INSERT INTO discount (size,sum )
  VALUES (10, 200);