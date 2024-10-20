-- Insertar datos en la tabla foro
INSERT INTO foro (titulo) VALUES ('Foro de Tecnología');
INSERT INTO foro (titulo) VALUES ('Foro de Deportes');

-- Insertar datos en la tabla post
INSERT INTO post (titulo, contenido, foro_id) VALUES
('Últimas tendencias en IA', 'En este post discutimos las últimas tendencias en inteligencia artificial...', 1),
('Novedades en desarrollo web', 'Aquí se presentan las últimas novedades en el desarrollo web moderno...', 1),
('Resultados de la última jornada de fútbol', 'En este post analizamos los resultados de la última jornada de la liga...', 2),
('Las mejores jugadas de la NBA', 'Aquí recopilamos las mejores jugadas de la NBA de esta temporada...', 2);

-- Insertar datos en la tabla mensaje
INSERT INTO mensaje (contenido, post_id) VALUES
('¡Excelente artículo! Me encantó la parte sobre las tendencias en IA.', 1),
('Estoy de acuerdo, el desarrollo web está evolucionando rápidamente.', 2),
('Gran análisis de los resultados. ¿Qué opinan del próximo partido?', 3),
('Me encantan las jugadas de la NBA, especialmente las de este año.', 4),
('¿Alguien tiene recomendaciones de libros sobre IA?', 1),
('¿Qué tecnologías están utilizando para el desarrollo web?', 2);