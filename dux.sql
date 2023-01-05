DROP SCHEMA IF EXISTS `dux`;
CREATE SCHEMA IF NOT EXISTS `dux`;
USE `dux`;

CREATE TABLE IF NOT EXISTS `dux`.`rubro` (
  `id_rubro` BIGINT NOT NULL AUTO_INCREMENT,
  `rubro` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_rubro`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `dux`.`cliente` (
  `id_cliente` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(80) NOT NULL,
  `apellido` VARCHAR(80) NOT NULL,
  `cuit` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `dux`.`producto` (
  `codigo` VARCHAR(50) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `id_rubro` BIGINT NOT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `rubro`
    FOREIGN KEY (`id_rubro`)
    REFERENCES `dux`.`rubro` (`id_rubro`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `dux`.`venta` (
  `id_venta` BIGINT NOT NULL AUTO_INCREMENT,
  `codigo_producto` VARCHAR(50) NOT NULL,
  `fecha` DATE NOT NULL,
  `cantidad` BIGINT NOT NULL,
  `precio_unitario` DOUBLE NOT NULL,
  `id_cliente` BIGINT NOT NULL,
  PRIMARY KEY (`id_venta`),
  CONSTRAINT `producto`
    FOREIGN KEY (`codigo_producto`)
    REFERENCES `dux`.`producto` (`codigo`),
  CONSTRAINT `cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `dux`.`cliente` (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;


INSERT INTO rubro (id_rubro, rubro) VALUES 
(DEFAULT, 'farmacia'),
(DEFAULT, 'bazar'),
(DEFAULT, 'librería'),
(DEFAULT, 'comestible');


INSERT INTO cliente (id_cliente, nombre, apellido, cuit) VALUES 
(DEFAULT, 'Laura', 'Fernandez', '23-30557347-4'),
(DEFAULT, 'Cristian', 'Corgniati', '24-27687539-9'),
(DEFAULT, 'Valentino', 'Perez', '23-40258674-4');

INSERT INTO producto (codigo, nombre, fecha_creacion, id_rubro) VALUES 
('AA1', 'Cartulina', '2022-11-03', 3),
('AA2', 'Lapiz', now(), 3),
('AA3', 'Regla', '2021-07-20', 3),
('AB1', 'Plato', '2022-06-18', 2),
('AB2', 'Wok', '2022-10-30', 2),
('AB3', 'Taza', '2021-12-03', 2),
('AC1', 'Ibuprofeno', '2021-01-03', 1),
('AC2', 'Curitas', '2022-01-11', 1),
('AC3', 'Crema Facial', '2021-11-03', 1),
('AD1', 'Chocolate', '2021-07-15', 4),
('AD2', 'Turrón', '2022-02-15', 4),
('AD3', 'Barra Cereal', '2022-08-08', 4);

INSERT INTO venta (id_venta, codigo_producto, fecha, cantidad, precio_unitario, id_cliente) VALUES 
(DEFAULT, 'AA1', '2021-03-21', 6, 200.00, 1),
(DEFAULT, 'AC3', '2021-03-21', 2, 500.00, 1),
(DEFAULT, 'AC1', now(), 1, 1500.00, 1),
(DEFAULT, 'AA3', '2021-01-04', 4, 230.00, 2),
(DEFAULT, 'AD3', '2022-10-04', 5, 50.00, 2),
(DEFAULT, 'AC3', now(), 1, 1300.00, 2),
(DEFAULT, 'AC1', now(), 1, 800.00, 2),
(DEFAULT, 'AC2', now(), 10, 150.00, 3),
(DEFAULT, 'AB2', now(), 1, 1800.00, 3),
(DEFAULT, 'AB3', now(), 7, 185.30, 3),
(DEFAULT, 'AA2', now(), 6, 489.50, 3);

-- -----------------------------------------------------
-- Consultas:
-- -----------------------------------------------------

-- Todos los productos del rubro "librería", creados hoy.

SELECT p.nombre, p.fecha_creacion, r.rubro 
FROM producto AS p 
INNER JOIN rubro AS r
ON p.id_rubro= r.id_rubro
WHERE p.fecha_creacion = CAST(now() AS DATE) AND r.rubro = "librería";

-- Monto total vendido por cliente (mostrar nombre del cliente y monto).

SELECT (SUM(v.precio_unitario * v.cantidad) ) monto_total_vendido, c.nombre cliente_nombre
FROM venta AS v
INNER JOIN cliente AS c
ON v.id_cliente = c.id_cliente
GROUP BY c.nombre;

-- Cantidad de ventas por producto.

SELECT SUM(v.cantidad) cantidad_vendida, p.nombre producto_nombre
FROM venta AS v
INNER JOIN producto AS p
ON v.codigo_producto = p.codigo
GROUP BY p.nombre;

-- Cantidad de productos comprados por cliente en el mes actual.

SELECT SUM(v.cantidad) cantidad_vendida, v.fecha, c.nombre
FROM venta AS v
INNER JOIN cliente AS c
ON v.id_cliente = c.id_cliente
WHERE year(v.fecha) = year(now()) and month(v.fecha) = month(now())
GROUP BY c.nombre;

-- Ventas que tienen al menos un producto del rubro "bazar".

SELECT v.fecha, v.cantidad, v. precio_unitario, p.nombre producto_nombre, r.rubro
FROM venta AS v
INNER JOIN producto AS p
ON v.codigo_producto = p.codigo
INNER JOIN rubro AS r
ON r.id_rubro = p.id_rubro
HAVING r.rubro = "bazar";

-- Rubros que no tienen ventas en los últimos 2 meses.

SELECT r.id_rubro, r.rubro, v.fecha fecha_facturacion
FROM rubro AS r
INNER JOIN producto AS p
ON r.id_rubro = p.id_rubro
INNER JOIN venta AS v
ON v.codigo_producto = p.codigo
group by r.id_rubro
having PERIOD_DIFF(EXTRACT(MONTH FROM NOW()), EXTRACT(MONTH FROM v.fecha)) <= -2 and !(PERIOD_DIFF(EXTRACT(MONTH FROM NOW()), EXTRACT(MONTH FROM v.fecha)) >= -2);


