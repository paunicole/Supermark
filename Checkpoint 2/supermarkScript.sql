-- ELIMINAR LA BD supermark SI EXISTE
DROP DATABASE IF EXISTS supermark;

-- CREAR LA BD supermark
CREATE DATABASE supermark character set utf8mb3;

-- USAR LA BD supermark
USE supermark;

-- CREAR TABLAS

-- CREAR TABLA Usuario
CREATE TABLE Usuario (
	idUsuario INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    email VARCHAR(20),
	isAdmin BIT DEFAULT 0 -- 0 == false ; 1 == true
);

-- CREAR TABLA Domicilio
CREATE TABLE Domicilio (
	idDomicilio INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	calle VARCHAR(20),
    numero INT
);

-- CREAR TABLA Cliente
CREATE TABLE Cliente (
	idCliente INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT NOT NULL,
    idDomicilio INT NOT NULL,
	descuentoPorcentaje INT DEFAULT 10, -- Descuento del 10% para todos los clientes
	FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idDomicilio) REFERENCES Domicilio(idDomicilio)
);

-- CREAR TABLA Administrador
CREATE TABLE Administrador (
	idAdmin INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUsuario INT NOT NULL,
	FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

-- CREAR TABLA Producto
CREATE TABLE Producto (
	idProducto INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(30),
    marca VARCHAR(30),
	cantStock INT UNSIGNED,
	precio DECIMAL(10,2) UNSIGNED NOT NULL,
    categoria ENUM('GALLETAS','BEBIDAS','CARNES') -- Por ahora ENUM
);

-- CREAR TABLA Carrito
CREATE TABLE Carrito (
	idCarrito INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

-- CREAR TABLA CarritoxProducto
CREATE TABLE CarritoxProducto (
    idCarrito INT NOT NULL,
    idProducto INT NOT NULL,
	cantidad INT,
    PRIMARY KEY (idCarrito, idProducto),
    FOREIGN KEY (idCarrito) REFERENCES Carrito(idCarrito),
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto)
);

CREATE TABLE Compra(
	idCompra INT NOT NULL AUTO_INCREMENT,
    idCarrito INT, 
    idCliente INT,
    PRIMARY KEY (idCompra),
    FOREIGN KEY (idCarrito) REFERENCES Carrito(idCarrito),
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE Factura(
	idFactura INT NOT NULL AUTO_INCREMENT, 
	idCompra INT NOT NULL,
    total DOUBLE,
    totalDescuento DOUBLE,
    PRIMARY KEY (idFactura),
    FOREIGN KEY (idCompra) REFERENCES Compra(idCompra)
);