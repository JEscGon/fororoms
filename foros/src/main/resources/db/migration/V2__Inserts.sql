
INSERT INTO `fororoms-foros`.foro (titulo,fecha_creacion,usuario_id) VALUES ('Foro de Tecnología',NOW(),1);
INSERT INTO `fororoms-foros`.foro (titulo,fecha_creacion,usuario_id) VALUES ('Foro de Mods',NOW(),1);
INSERT INTO `fororoms-foros`.foro (titulo,fecha_creacion,usuario_id) VALUES ('Foro de Homebrew',NOW(),1);
INSERT INTO `fororoms-foros`.foro (titulo,fecha_creacion,usuario_id) VALUES ('Foro de CFW',NOW(),2);

INSERT INTO `fororoms-foros`.post (contenido, fecha_creacion, fecha_edicion, foro_id, usuario_id)
VALUES ('Este es el primer post', NOW(), NOW(), 1, 1);
INSERT INTO `fororoms-foros`.post (contenido, fecha_creacion, foro_id, usuario_id)
VALUES ('Este es el segundo post', NOW(), 2, 2);
INSERT INTO `fororoms-foros`.post (contenido, fecha_creacion, foro_id, usuario_id)
VALUES ('Este es el tercer post', NOW(), 1, 1);
INSERT INTO `fororoms-foros`.post (contenido, fecha_creacion,  foro_id, usuario_id)
VALUES ('Este es el cuarto post', NOW(), 2, 1);

INSERT INTO `fororoms-foros`.mensaje (contenido, fecha_publicacion, post_id, usuario_id)
VALUES ('Este es el primer mensaje', NOW(), 1, 1);
INSERT INTO `fororoms-foros`.mensaje (contenido, fecha_publicacion,  post_id, usuario_id)
VALUES ('Este es el segundo mensaje', NOW(), 2, 2);
INSERT INTO `fororoms-foros`.mensaje (contenido, fecha_publicacion, fecha_edicion, post_id, usuario_id)
VALUES ('Este es el tercer mensaje', NOW(), NOW(), 1, 2);
INSERT INTO `fororoms-foros`.mensaje (contenido, fecha_publicacion, fecha_edicion, post_id, usuario_id)
VALUES ('Este es el cuarto mensaje', NOW(), NOW(), 2, 2);

INSERT INTO `fororoms-foros`.post_likes (post_id, usuario_id) VALUES (1, 1);
INSERT INTO `fororoms-foros`.post_likes (post_id, usuario_id) VALUES (2, 2);
INSERT INTO `fororoms-foros`.post_likes (post_id, usuario_id) VALUES (1, 2);
INSERT INTO `fororoms-foros`.post_likes (post_id, usuario_id) VALUES (2, 1);

INSERT INTO `fororoms-foros`.post_dislikes (post_id, usuario_id) VALUES (1, 2);
INSERT INTO `fororoms-foros`.post_dislikes (post_id, usuario_id) VALUES (2, 1);
INSERT INTO `fororoms-foros`.post_dislikes (post_id, usuario_id) VALUES (1, 1);

INSERT INTO `fororoms-foros`.mensaje_likes (mensaje_id, usuario_id) VALUES (1, 1);
INSERT INTO `fororoms-foros`.mensaje_likes (mensaje_id, usuario_id) VALUES (2, 1);
INSERT INTO `fororoms-foros`.mensaje_likes (mensaje_id, usuario_id) VALUES (1, 2);
INSERT INTO `fororoms-foros`.mensaje_likes (mensaje_id, usuario_id) VALUES (2, 2);

INSERT INTO `fororoms-foros`.mensaje_dislikes (mensaje_id, usuario_id) VALUES (1, 2);
INSERT INTO `fororoms-foros`.mensaje_dislikes (mensaje_id, usuario_id) VALUES (2, 1);
INSERT INTO `fororoms-foros`.mensaje_dislikes (mensaje_id, usuario_id) VALUES (1, 1);
