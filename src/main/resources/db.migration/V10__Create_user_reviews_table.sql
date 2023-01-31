CREATE TABLE review (

  id               BIGSERIAL NOT NULL PRIMARY KEY,
  text_review      TEXT NOT NULL,
  created_at       TIMESTAMP NOT NULL DEFAULT now(),
  user_id          BIGINT NOT NULL references users(user_id)

  );

  INSERT INTO review (text_review, user_id)
  VALUES ('все круто', 2);
  INSERT INTO review (text_review, user_id)
  VALUES ('все круто', 1);
  INSERT INTO review (text_review, user_id)
  VALUES ('все ужас', 3);
  INSERT INTO review (text_review, user_id)
  VALUES ('все ок', 4);