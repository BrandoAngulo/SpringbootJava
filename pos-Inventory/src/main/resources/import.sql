INSERT INTO categoria(descripcion)
VALUES ('Aluminio'),
       ('Ceramica'),
       ('Vidrio');
INSERT INTO proveedor(nit, nombre, telefono)
VALUES ('112121122121', 'Aluminios cali', '231231111'),
       ('112121122122', 'Ceramicas cali', '231231112'),
       ('112121122123', 'Vidrios cali', '231231113');
INSERT INTO producto(cantidad, categoria, precio, proveedor, descripcion, nombre)
VALUES (10, 1, 1.500, 1, 'Cucharon para sopa', 'Cucharon'),
       (10, 2, 10.000, 2, 'Piso blanco', 'Ceramica blanca'),
       (10, 3, 11.000, 3, 'Jarron para flores', 'Jarron 10cm');
