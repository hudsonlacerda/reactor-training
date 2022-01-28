DELETE FROM customers;
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM staffs;
DELETE FROM stocks;
DELETE FROM stores;
DELETE FROM brands;
DELETE FROM categories;


INSERT INTO brands
(brand_id, brand_name)
VALUES(1, 'GUN''S PIZZA');

INSERT INTO categories
(category_id, category_name)
VALUES(1, 'GUN');

INSERT INTO stores
(store_id, city, email, phone, state, store_name, street, zip_code)
VALUES(1, 'Maracanaú', 'comercial@ldc.com', '(85)986420419', 'CE', 'Loja da construção', '18', '6666');

INSERT INTO staffs
(staff_id, active, email, first_name, last_name, phone, manager_id, store_id)
VALUES(1, true, 'manager@ldc.com', 'Manager', 'Silva', '8888-8888', NULL, 1);
