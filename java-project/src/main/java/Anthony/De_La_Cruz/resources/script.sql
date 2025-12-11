-- Script: crear base de datos, tabla y 5 registros
CREATE DATABASE IF NOT EXISTS inventario;
USE inventario;

CREATE TABLE IF NOT EXISTS computadora(
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          codigo VARCHAR(20),
    tipo_equipo VARCHAR(50),
    marca VARCHAR(50),
    modelo VARCHAR(50),
    so VARCHAR(50),
    ram VARCHAR(10),
    almacenamiento VARCHAR(20),
    fecha_registro DATE,
    mantenimiento TINYINT(1),
    estado TINYINT(1) DEFAULT 1
    );

INSERT INTO computadora(codigo, tipo_equipo, marca, modelo, so, ram, almacenamiento, fecha_registro, mantenimiento, estado) VALUES
                                                                                                                                ('PC001','Escritorio','HP','ProDesk','Windows 10','8GB','500GB','2024-01-01',0,1),
                                                                                                                                ('PC002','Laptop','Lenovo','ThinkPad','Windows 11','16GB','512GB','2024-02-10',1,1),
                                                                                                                                ('PC003','Laptop','Dell','Latitude','Ubuntu','8GB','256GB','2024-02-15',0,1),
                                                                                                                                ('PC004','All in One','AOC','AIO24','Windows 10','4GB','1TB','2024-03-10',0,1),
                                                                                                                                ('PC005','Escritorio','Acer','Veriton','Windows 11','16GB','1TB','2024-04-03',1,1);
