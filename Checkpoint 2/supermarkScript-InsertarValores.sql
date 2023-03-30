use supermark;

-- PROGRAMA PROBADO CON LOS SIGUIENTES VALORES (FALTO HACER MAS CASOS DE PRUEBA)
-- FALTA TABLA CATEGORIA

-- INSERTO VALORES A LA TABLA Producto
INSERT INTO Producto (descripcion, marca, cantStock, precio, categoria)
VALUES
('Galletas OREO', 'OREO', 10, 10, 'GALLETAS'),
('Galletas PEPITOS', 'PEPITOS', 10, 20, 'GALLETAS'),
('Galletas RUMBA', 'RUMBA', 12, 100, 'GALLETAS');

-- INSERTO VALORES A LA TABLA Usuario
INSERT INTO Usuario (nombre, apellido, email, username, password, isAdmin)
VALUES
('Pepe', 'Perez', 'pepe@gmail.com', 'pepito', 'pepe123', 1),
('Pepa', 'Paz', 'pepa@gmail.com', 'pepita', 'pepa123', 0)
;

-- INSERTO VALORES A LA TABLA Cliente
INSERT INTO Domicilio (calle, numero)
VALUES
('Belgrano', 91)
;

INSERT INTO Cliente(idUsuario, idDomicilio)
VALUES
(1, 1)
;

-- INSERTO VALORES A LA TABLA Carrito
INSERT INTO Carrito (idCliente)
VALUES
(1)
;

-- INSERTO VALORES A LA TABLA CarritoxProducto
INSERT INTO CarritoxProducto (idCarrito, idProducto, cantidad)
VALUES
(1, 1, 1),
(1, 2, 2)
;

-- INSERT INTO Compra(idCarrito, idCliente)
-- VALUES
-- (1, 1)
-- ;

-- INSERT INTO Factura(idCompra)
-- VALUES
-- (1)
-- ;

-- CONSULTAS
SELECT * FROM Usuario;
SELECT * FROM Cliente;
SELECT * FROM Carrito;
SELECT * FROM Producto;