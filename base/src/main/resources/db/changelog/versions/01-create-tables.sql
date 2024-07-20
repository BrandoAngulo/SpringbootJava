-- 1. Crear la tabla roles si no existe
CREATE TABLE IF NOT EXISTS roles
(
    id          SERIAL PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Insertar el rol 'ADMIN' si no existe
INSERT INTO roles(descripcion) VALUES ('ADMIN')
ON CONFLICT (descripcion) DO NOTHING;

-- 3. Crear la tabla usuario si no existe
CREATE TABLE IF NOT EXISTS usuario
(
    id      SERIAL PRIMARY KEY,
    nombre  VARCHAR(255),
    role_id INT REFERENCES roles (id)
);

-- 4. Insertar usuario si no existe
INSERT INTO usuario(nombre, role_id) VALUES ('s1', 1)
ON CONFLICT (id) DO NOTHING;
