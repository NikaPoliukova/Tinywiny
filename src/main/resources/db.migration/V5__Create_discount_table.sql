CREATE TABLE  discount
(
  discount_id      SERIAL NOT NULL PRIMARY KEY,
  size             INT NOT NULL
  );

  INSERT INTO discount (size)
  VALUES (3);
  INSERT INTO discount (size)
  VALUES (5);
  INSERT INTO discount (size)
  VALUES (8);
  INSERT INTO discount (size)
  VALUES (10);