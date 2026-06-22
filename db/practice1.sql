-- 1)
DROP TABLE IF EXISTS orderdetail;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers(
	cno INT(5),
	cname VARCHAR(10) NOT NULL,
	address VARCHAR(50) NOT NULL,
	email VARCHAR(20) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	CONSTRAINT PRIMARY KEY (cno)
);

CREATE TABLE orders(
	orderno INT(10),
	orderdate DATE DEFAULT SYSDATE(),
	address VARCHAR(50) NOT NULL,
	phone VARCHAR(20) NOT NULL,
	status VARCHAR(20) NOT NULL CHECK(status IN ('결제완료', '배송중', '배송완료')),
	cno INT(5) NOT NULL,
	CONSTRAINT PRIMARY KEY (orderno),
	CONSTRAINT FOREIGN KEY (cno) REFERENCES customers(cno)
);

CREATE TABLE products(
	pno INT(5),
	pname VARCHAR(20) NOT NULL,
	cost INT(8) NOT NULL DEFAULT(0),
	stock INT(5) NOT NULL DEFAULT(0),
	CONSTRAINT PRIMARY KEY (pno)
);

CREATE TABLE orderdetail(
	orderno INT(10),
	pno INT(5),
	qty INT(5) DEFAULT(0),
	cost INT(8) DEFAULT(0),
	CONSTRAINT FOREIGN KEY (orderno) REFERENCES orders(orderno),
	CONSTRAINT FOREIGN KEY (pno) REFERENCES products(pno),
	CONSTRAINT PRIMARY KEY (orderno, pno)	
);

-- 2)
INSERT INTO products() VALUES
(1001, '삼양라면', 1000, 200),
(1002, '새우깡', 1500, 500),
(1003, '월드콘', 2000, 350),
(1004, '빼빼로', 2000, 700),
(1005, '코카콜라', 1800, 550),
(1006, '환타', 1600, 300);

-- 3)
INSERT INTO customers() VALUES
(101, '김철수', '서울 강남구', 'cskim@naver.com', '899-6666'),
(102, '이영희', '부산 서면', 'yhlee@empal.com', '355-8882'),
(103, '최진국', '제주 동광양', 'jkchoi@gmail.com', '852-5764'),
(104, '강준호', '강릉 홍제동', 'jhkang@hanmail.com', '559-7777'),
(105, '민병국', '대전 전민동', 'bgmin@hotmail.com', '559-8741'),
(106, '오민수', '광주 북구', 'msoh@microsoft.com', '542-9988');


-- 4)
INSERT INTO orders (orderno, orderdate, address, phone, STATUS, cno)
SELECT 1, SUBDATE(CURDATE(), INTERVAL 3 DAY), address, '899-6666', '결제완료', cno
FROM customers
WHERE cno = 101;

INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE cno = 101),
		 pno, 50, cost
FROM products
WHERE pno = 1001;


-- 5)
UPDATE products
SET stock = stock - ( 	SELECT qty
								FROM orderdetail
								WHERE pno = 1001)
WHERE pno = 1001;



-- 6)
INSERT INTO orders (orderno, orderdate, address, phone, STATUS, cno)
SELECT 2, SUBDATE(CURDATE(), INTERVAL 2 DAY), address, '337-5000', '결제완료', cno
FROM customers
WHERE cno = 102;

INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE orderno = 2),
		 pno, 100, cost
FROM products
WHERE pno = 1002;
INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE orderno = 2),
		 pno, 150, cost
FROM products
WHERE pno = 1003;

-- 7)
UPDATE products
SET stock = stock - ( 	SELECT qty
								FROM orderdetail
								WHERE pno = 1002)
WHERE pno = 1002;
UPDATE products
SET stock = stock - ( 	SELECT qty
								FROM orderdetail
								WHERE pno = 1003)
WHERE pno = 1003;

-- 8)
INSERT INTO orders (orderno, orderdate, address, phone, STATUS, cno)
SELECT 3, SUBDATE(CURDATE(), INTERVAL 1 DAY), address, '652-2277', '결제완료', cno
FROM customers
WHERE cno = 106;

INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE orderno = 3),
		 pno, 100, cost
FROM products
WHERE pno = 1004;
INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE orderno = 3),
		 pno, 50, cost
FROM products
WHERE pno = 1005;

-- 9)
UPDATE products
SET stock = stock - ( 	SELECT qty
								FROM orderdetail
								WHERE pno = 1004)
WHERE pno = 1004;
UPDATE products
SET stock = stock - ( 	SELECT qty
								FROM orderdetail
								WHERE pno = 1005)
WHERE pno = 1005;

-- 10)
CREATE OR REPLACE VIEW all_order(orderdate, cname, address, phone, STATUS, pname, cost, qty, total_price)
AS
SELECT	O.orderdate,
			C.cname,
			O.address,
			O.phone,
			O.status,
			P.pname,
			P.cost,
			D.qty,
			(P.cost * D.qty)	
FROM		orders		O
JOIN		orderdetail D ON O.orderno = D.orderno
JOIN customers C   ON O.cno = C.cno
JOIN products P    ON D.pno = P.pno;

SELECT	*
FROM		all_order;

-- 11)
SELECT	orderdate, SUM(total_price)
FROM		all_order
GROUP BY orderdate;

-- 12)
INSERT INTO products() VALUES
(1007, '목캔디', 3000, 500);

-- 13)
INSERT INTO orders (orderno, orderdate, address, phone, STATUS, cno)
SELECT 4, CURDATE(), address, '352-4657', '결제완료', cno
FROM customers
WHERE cno = 103;

INSERT INTO orderdetail(orderno, pno, qty, cost)
SELECT (SELECT MAX(orderno) FROM orders WHERE cno = 103),
		 pno, 200, cost
FROM products
WHERE pno = 1007;


SELECT *
FROM customers;
SELECT *
FROM products;
SELECT *
FROM ORDERS;
SELECT *
FROM ORDERDETAIL;
