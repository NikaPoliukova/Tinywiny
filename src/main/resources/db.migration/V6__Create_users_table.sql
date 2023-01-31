CREATE TABLE users
 (
  user_id      BIGSERIAL NOT NULL UNIQUE,
  username     VARCHAR NOT NULL,
  password     VARCHAR NOT NULL,
  created_at   TIMESTAMP NOT NULL DEFAULT now(),
  email        VARCHAR,
  phone_number VARCHAR,
  role         VARCHAR NOT NULL DEFAULT 'USER',

  PRIMARY KEY (user_id)
  );

INSERT INTO users ( username, password, email, phone_number)
VALUES ('nika', '$2a$12$4k0YIr9v/ckNISWo7A3JVeZfo.YMM9Q6FBiK.VBexru3UYhYjSGWa', 'nika@mail.ru', +37529111555888);
INSERT INTO users ( username, password, email, phone_number)
VALUES ('user2', '$2a$12$WsnlLBmoEmgGzwkx.2sbF.Kkv28AzH2GOx6ZJsDdlBoMidv4R0ZDu', 'nika@mail.ru', +37529111555828);
INSERT INTO users ( username, password, email, phone_number)
VALUES ('user3', '$2a$12$J6ugEhxhQTfDcIO5uXq14Oi5ecplwx9qTlQceLwysnWqJho1gxPVC', 'nika@mail.ru', +37529111555883);
INSERT INTO users ( username, password, email, phone_number)
VALUES ('user4', '$2a$12$J6ugEhxhQTfDcIO5uXq14Oi5ecplwx9qTlQceLwysnWqJho1gxPVC', 'nika@mail.ru', +37529111555888);
INSERT INTO users ( username, password, email, phone_number)
VALUES ('user5', '$2a$12$J6ugEhxhQTfDcIO5uXq14Oi5ecplwx9qTlQceLwysnWqJho1gxPVC', 'nika@mail.ru', +37529111555888);
INSERT INTO users ( username, password, email, phone_number)
VALUES ('user6', '$2a$12$J6ugEhxhQTfDcIO5uXq14Oi5ecplwx9qTlQceLwysnWqJho1gxPVC', 'nika@mail.ru', +37529111555888);
INSERT INTO users ( username, password, email, phone_number, role)
VALUES ('kata', '$2a$12$J6ugEhxhQTfDcIO5uXq14Oi5ecplwx9qTlQceLwysnWqJho1gxPVC', 'nika@mail.ru', +37529111555888, 'ADMIN');

