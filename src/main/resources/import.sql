INSERT INTO tb_user (id, name, email, phone, password) VALUES (1, 'Maria Bronw', 'maria@gmail.com', '988888888', '123456');
INSERT INTO tb_user (id, name, email, phone, password) VALUES (2, 'Alex Green', 'alex@gmail.com', '977777777', '123456');

INSERT INTO tb_category(id, name) VALUES (1, 'Electronics');
INSERT INTO tb_category(id, name) VALUES (2, 'Books');
INSERT INTO tb_category(id, name) VALUES (3, 'Computers');

INSERT INTO tb_product(id, name, description, price, img_url) VALUES (1, 'The Lord of the Rings', 'Lorem ipsum dolor sit amet, consectetur', 90.5, 'img_01.jpg');
INSERT INTO tb_product(id, name, description, price, img_url) VALUES (2, 'Smart TV', 'Lorem ipsum dolor sit amet, consectetur', 2190.0, 'img_02.jpg');
INSERT INTO tb_product(id, name, description, price, img_url) VALUES (3, 'MackBook PRO', 'Lorem ipsum dolor sit amet, consectetur', 1250.0, 'img_03.jpg');
INSERT INTO tb_product(id, name, description, price, img_url) VALUES (4, 'PC Gamere', 'Lorem ipsum dolor sit amet, consectetur', 1200.0, 'img_04.jpg');
INSERT INTO tb_product(id, name, description, price, img_url) VALUES (5, 'Rails for Durmmies', 'Lorem ipsum dolor sit amet, consectetur', 100.99, 'img_05.jpg');

INSERT INTO tb_product_category (product_id, category_id) values (1, 2);
INSERT INTO tb_product_category (product_id, category_id) values (2, 1);
INSERT INTO tb_product_category (product_id, category_id) values (2, 3);
INSERT INTO tb_product_category (product_id, category_id) values (3, 3);
INSERT INTO tb_product_category (product_id, category_id) values (4, 3);
INSERT INTO tb_product_category (product_id, category_id) values (5, 2);

INSERT INTO tb_order (id, moment, order_status, user_id) VALUES (1, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 1, 1);
INSERT INTO tb_order (id, moment, order_status, user_id) VALUES (2, TIMESTAMP WITH TIME ZONE '2019-01-20T20:50:07.12345Z', 0, 2);
INSERT INTO tb_order (id, moment, order_status, user_id) VALUES (3, TIMESTAMP WITH TIME ZONE '2020-12-25T20:50:07.12345Z', 0, 1);



INSERT INTO tb_order_item (id, quantity, price, product_id, order_id) VALUES (1, 2,90.5,1, 1);
INSERT INTO tb_order_item (id, quantity, price, product_id, order_id) VALUES (2, 1,1250,3, 1);
INSERT INTO tb_order_item (id, quantity, price, product_id, order_id) VALUES (3, 2,1250,3, 2);
INSERT INTO tb_order_item (id, quantity, price, product_id, order_id) VALUES (4, 2,100.9,5, 3);