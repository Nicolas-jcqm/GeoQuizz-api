INSERT INTO utilisateur (id, username, mail, password) VALUES
(1, 'MrRobot', 'loremIpsum@gmail.com', 'password');

INSERT INTO serie (id, ville, map_latitude, map_longitude, map_zoom, dist) VALUES
(1, 'Nancy', 48.66686499999999, 6.134240999999999, 10, 500);

INSERT INTO partie (id, token, nb_photos, status, score, idJoueur, idSerie) VALUES
(1, 'jnfskfd466dg4dg4f', 10, true, 12, 1, 1);

INSERT INTO photo (id, descr, position_latitude, position_longitude, url, idSerie) VALUES
(1, 'Jardin Botanique Jean-Marie Pelt', 48.661103, 6.155104, 'Nancy/jardinBotaniqueJMPelt.jpg', 1),
(2, 'Place Stanislas', 48.693611, 6.183156, 'Nancy/placeStanislas.jpg', 1),
(3, 'Palais des Ducs de Lorraine', 48.697322, 6.179293, 'Nancy/palaisDesDucsDeLorraine.jpg.jpg', 1),
(4, 'Cathédrale de Nancy', 48.691525, 6.186118, 'Nancy/cathedraleNancy.jpg', 1),
(5, 'Stade Marcel Picot', 48.695675, 6.210751, 'Nancy/stadeMarcelPicot.jpg', 1),
(6, 'Porte de la Craffe', 48.699058, 6.177817, 'Nancy/porteDeLaCraffe.jpg', 1),
(7, 'Place Carriere', 48.695714, 6.182259, 'Nancy/placeCarriere.jpg', 1),
(8, 'Parc Sainte Marie', 48.680357, 6.170744, 'Nancy/parcSainteMarie.jpg', 1),
(9, 'Musee des Beaux-Arts', 48.693630, 6.182112, 'Nancy/museeBeauxArts.jpg', 1),
(10, 'Centre Commercial Saint Sebeastien', 48.688876, 6.181254, 'Nancy/saintSebastien.jpg', 1);

