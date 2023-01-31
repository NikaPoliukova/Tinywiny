create table type_product
(
    id_type      BIGSERIAL NOT NULL PRIMARY KEY,
    type_name    varchar
    );

INSERT INTO type_product (type_name)
VALUES ('decorations');
INSERT INTO type_product (type_name)
VALUES ('mobiles');
INSERT INTO type_product (type_name)
VALUES ('wings');
INSERT INTO type_product (type_name)
VALUES ('toys');
