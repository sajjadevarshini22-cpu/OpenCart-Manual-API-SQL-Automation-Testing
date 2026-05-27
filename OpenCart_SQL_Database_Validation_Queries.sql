-- OpenCart SQL Database Validation Practice Queries
-- Replace oc_ prefix with your configured table prefix if different.
-- Replace emails, customer_id and order_id with real test data from your environment.

-- 1. Verify customer created after registration
SELECT customer_id, firstname, lastname, email, status, approved
FROM oc_customer
WHERE email = 'test@example.com';

-- 2. Verify duplicate email count
SELECT email, COUNT(*) AS email_count
FROM oc_customer
WHERE email = 'test@example.com'
GROUP BY email;

-- 3. Verify address saved for customer
SELECT address_id, firstname, lastname, address_1, city, postcode
FROM oc_address
WHERE customer_id = 101;

-- 4. Verify latest order for customer
SELECT order_id, customer_id, firstname, lastname, email, total, order_status_id, date_added
FROM oc_order
WHERE customer_id = 101
ORDER BY order_id DESC;

-- 5. Verify order product mapping
SELECT order_id, product_id, name, quantity, price, total
FROM oc_order_product
WHERE order_id = 5001;

-- 6. Verify order total components
SELECT title, value, sort_order
FROM oc_order_total
WHERE order_id = 5001
ORDER BY sort_order;

-- 7. Verify order status history
SELECT order_status_id, comment, date_added
FROM oc_order_history
WHERE order_id = 5001;

-- 8. Verify cart rows before checkout, if cart table is used
SELECT customer_id, product_id, quantity, date_added
FROM oc_cart
WHERE customer_id = 101;

-- 9. Verify product stock after order, if subtract stock is enabled
SELECT product_id, model, quantity, stock_status_id
FROM oc_product
WHERE product_id = 43;

-- 10. Verify product details displayed in UI
SELECT product_id, model, price, quantity, status
FROM oc_product
WHERE product_id = 43;
