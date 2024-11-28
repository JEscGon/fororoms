CREATE TABLE IF NOT EXISTS foro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT,
    fecha_creacion TIMESTAMP,
    fecha_edicion TIMESTAMP,
    foro_id BIGINT NOT NULL,
    usuario_id BIGINT,
    FOREIGN KEY (foro_id) REFERENCES foro(id)
);

CREATE TABLE IF NOT EXISTS mensaje (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido VARCHAR(500) NOT NULL,
    fecha_publicacion TIMESTAMP NOT NULL,
    fecha_edicion TIMESTAMP,
    post_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post(id)
);

CREATE TABLE IF NOT EXISTS post_likes (
    post_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (post_id, usuario_id),
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS post_dislikes (
    post_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (post_id, usuario_id),
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS mensaje_likes (
    mensaje_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (mensaje_id, usuario_id),
    FOREIGN KEY (mensaje_id) REFERENCES mensaje(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS mensaje_dislikes (
    mensaje_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    PRIMARY KEY (mensaje_id, usuario_id),
    FOREIGN KEY (mensaje_id) REFERENCES mensaje(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


