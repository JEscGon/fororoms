-- Insert data into the permissions table
INSERT INTO permissions (name) VALUES
('READ'),
('WRITE');

-- Insert data into the roles table
INSERT INTO roles (role_name) VALUES
('ADMIN'),
('USER');

-- Insert data into the users table
INSERT INTO users (username, password, nombre, apellidos, email, fecha_nacimiento, dni, telefono, direccion, pais, codigo_postal, ciudad, iban, fecha_creacion, fecha_edicion) VALUES
('admin', 'adminpass', 'Admin', 'User', 'admin@example.com', '1980-01-01 00:00:00', '12345678A', '123456789', 'Admin Street', 'Admin Country', '12345', 'Admin City', 'ES1234567890123456789012', '2023-01-01 00:00:00', '2023-01-01 00:00:00'),
('user', 'userpass', 'Regular', 'User', 'user@example.com', '1990-01-01 00:00:00', '87654321B', '987654321', 'User Street', 'User Country', '54321', 'User City', 'ES2109876543210987654321', '2023-01-01 00:00:00', '2023-01-01 00:00:00');

-- Insert data into the roles_permissions table
INSERT INTO roles_permissions (role_id, permission_id) VALUES
(1, 1),
(1, 2),
(2, 1);

-- Insert data into the users_roles table
INSERT INTO users_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);