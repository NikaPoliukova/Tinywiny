create table image
(
    image_id   BIGSERIAL NOT NULL PRIMARY KEY,
    image_name varchar NOT NULL,
    product_id BIGINT NOT NULL
);

INSERT INTO image (image_name,product_id )
VALUES ('image1', 1);
INSERT INTO image (image_name,product_id )
VALUES ('image2', 2);
INSERT INTO image (image_name,product_id )
VALUES ('image3', 3);