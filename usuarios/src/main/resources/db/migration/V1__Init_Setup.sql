CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    fecha_nacimiento TIMESTAMP,
    dni VARCHAR(255) UNIQUE NOT NULL,
    telefono VARCHAR(255) UNIQUE,
    direccion VARCHAR(255),
    pais VARCHAR(255),
    codigo_postal VARCHAR(255),
    ciudad VARCHAR(255),
    iban VARCHAR(255) UNIQUE,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_edicion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO `fororoms-usuarios`.roles (role_name) values ('ROLE_ADMIN');
INSERT INTO `fororoms-usuarios`.roles (role_name) values ('ROLE_USER');

INSERT INTO `fororoms-usuarios`.users (username, password, nombre, apellidos, email, fecha_nacimiento, dni, telefono, direccion, pais, codigo_postal, ciudad, iban, fecha_creacion, fecha_edicion) VALUES
('admin', '$2a$10$9tUr9iZZOemhCZFy.7QRFOCDxbYR/3GkkqukFI8wO35RDV7Km2TRm', 'Admin', 'User', 'admin@example.com', '1980-01-01 00:00:00', '12345678A', '123456789', 'Admin Street', 'Admin Country', '12345', 'Admin City', 'ES1234567890123456789012', '2023-01-01 00:00:00', '2023-01-01 00:00:00');
INSERT INTO `fororoms-usuarios`.users (username, password, nombre, apellidos, email, fecha_nacimiento, dni, telefono, direccion, pais, codigo_postal, ciudad, iban, fecha_creacion, fecha_edicion) VALUES
('user', '$2a$10$7pnvhzxog4YwOg05CVJBvOQW5LWjlCyUPnoK/X3HjRI8NcYC/S.Ye', 'Regular', 'User', 'user@example.com', '1990-01-01 00:00:00', '87654321B', '987654321', 'User Street', 'User Country', '54321', 'User City', 'ES2109876543210987654321', '2023-01-01 00:00:00', '2023-01-01 00:00:00');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);