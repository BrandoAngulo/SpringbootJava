-- ChangeSet 1
-- Tag Database
-- Tag: roles-ct

-- Create Table roles
CREATE TABLE roles
(
    id          SERIAL PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL UNIQUE
);

-- ChangeSet 2
-- Tag Database
-- Tag: rol-insert

-- Insert into roles
INSERT INTO roles (descripcion)
VALUES ('ADMIN');

-- ChangeSet 3
-- Tag Database
-- Tag: usuario-ct

-- Create Table usuario
CREATE TABLE usuario
(
    id      SERIAL PRIMARY KEY,
    nombre  VARCHAR(100),
    role_id INT,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES roles (id)
);
-- ChangeSet 4
-- Tag Database
-- Tag: usuario-insert

-- Insert into usuario
INSERT INTO usuario (nombre, role_id)
VALUES ('prueba', 1);

-- Rollback for ChangeSet 1
-- TRUNCATE TABLE roles RESTART IDENTITY;
DROP TABLE roles CASCADE;

-- Rollback for ChangeSet 2
-- DELETE FROM roles WHERE descripcion = 'ADMIN';

-- Rollback for ChangeSet 3
-- TRUNCATE TABLE usuario RESTART IDENTITY;
-- DROP TABLE usuario CASCADE;

-- Rollback for ChangeSet 4
DELETE
FROM usuario
WHERE nombre = 'prueba'
  AND role_id = 1;
