INSERT INTO utilisateur (id, username, mail, password) VALUES
('9b25833d-53d4-4384-9ddb-cb00ec9a53f8', 'mrrobot', 'loremIpsum@gmail.com', '$2a$10$2.XqnIeUQCQnDcOTyP6wa..lTEUT28JF7g5Z2tMkLS3wl/R7/fqE2'),
('2c8b0c9e-3cee-4fce-bf29-c46b5432a470', 'Alexandra', 'Alexandra@gmail.com', '$2a$10$IdxuW/IPXMx0HHRuxs61mOw7aqVXgQh/P3CaD9OxKlFc/n26oh2p.'),
('c8ec49cd-d2af-4c34-889f-de6a0ac9e652', 'Gymcou', 'leo@orange.fr', '$2a$10$rN4lrJUDJ2xj4z0BZvrqsO92XpMJ06U62mwyvxU3QmN258U/ayxvO'),
('e618fc7b-7905-476c-bc84-ecfef9d9f22d', 'EcmaScript', 'javascript@hotmail.fr', '$2a$10$Ka6c/2b2//Ta7JecPf2saObpH.jhmJ6JSKem31BpbPW6uI/yeH52K');

INSERT INTO serie (id, nom, ville, map_latitude, map_longitude, map_zoom, dist, idUtilisateur) VALUES
('71e058af-cf5f-4614-be3b-ec50835954e3', 'serieNancy', 'Nancy', 48.66686499999999, 6.134240999999999, 12, 500, '9b25833d-53d4-4384-9ddb-cb00ec9a53f8'),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4', 'serieParis', 'Paris', 48.9021449, 2.4699208, 13, 500, 'e618fc7b-7905-476c-bc84-ecfef9d9f22d');

INSERT INTO partie (id, token, nb_photos, status, score, idJoueur, idSerie) VALUES
('26f024c8-afa1-4ce6-8030-e27151b53fa5', '8b96ce70-4f1d-4d4c-b69f-9bda744e800f', 5, false, 42, 'Orion', '71e058af-cf5f-4614-be3b-ec50835954e3'),
('deb6ba1e-a788-4569-846b-4501039539f8', '29f7a8b4-d24c-4715-9d0d-ece82d8dea9d"', 10, false, 73, 'Munky', '71e058af-cf5f-4614-be3b-ec50835954e3'),
('c893815d-2660-49ff-b8a7-46c67a66d1d7', 'c78f25c0-4751-4a14-b595-f040f4c136f4', 7, false, 50,'Gollum', '71e058af-cf5f-4614-be3b-ec50835954e3'),
('dc00e2ca-9fa4-45d6-93b9-0bbbcd658e08', '968312bd-edd2-42ef-aa34-ee273e1e31d9', 15, true, 12, 'Artorias', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
('11d8dbcc-cab1-46bb-8abd-50c23a69c9d1', '525bbbd8-bf47-43d0-bac6-65821b1b40d9', 8, true, 5, 'mongodb', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4');

INSERT INTO photo (id, descr, position_latitude, position_longitude, url, idSerie) VALUES
(1, 'Jardin Botanique Jean-Marie Pelt', 48.661103, 6.155104, 'Nancy/jardinBotaniqueJMPelt.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(2, 'Place Stanislas', 48.693611, 6.183156, 'NanplaceStanislas.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(3, 'Palais des Ducs de Lorraine', 48.697322, 6.179293, 'Nancy/palaisDesDucsDeLorraine.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(4, 'Cathédrale de Nancy', 48.691525, 6.186118, 'Nancy/cathedraleNancy.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(5, 'Stade Marcel Picot', 48.695675, 6.210751, 'Nancy/stadeMarcelPicot.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(6, 'Porte de la Craffe', 48.699058, 6.177817, 'Nancy/porteDeLaCraffe.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(7, 'Place Carriere', 48.695714, 6.182259, 'Nancy/placeCarriere.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(8, 'Parc Sainte Marie', 48.680357, 6.170744, 'Nancy/parcSainteMarie.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(9, 'Musee des Beaux-Arts', 48.693630, 6.182112, 'Nancy/museeBeauxArts.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),
(10, 'Centre Commercial Saint Sebeastien', 48.688876, 6.181254, 'Nancy/saintSebastien.jpg', '71e058af-cf5f-4614-be3b-ec50835954e3'),

(11, 'Arc de Triomphe', 48.873819, 2.295045, 'Paris/arcTriomphe.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(12, 'Centre Pompidou', 48.860861, 2.352288, 'Paris/centrePompidou.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(13, 'Champs Elysees', 48.871900, 2.301722, 'Paris/champsElysée.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(14, 'Champs de mars', 48.855739, 2.298148, 'Paris/champsMars.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(15, 'DisneyLand Paris', 48.872390, 2.775808, 'Paris/disneyParis.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(16, 'Les invalides', 48.855359, 2.312590, 'Paris/invalides.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(17, 'Jardins du Luxembourg', 48.846215, 2.337311, 'Paris/jardinLuxembourg.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(18, 'Jardin des Tuilleries', 48.863746, 2.328294, 'Paris/jardinTuilleries.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(19, 'Le musee du Louvres', 48.860687, 2.337807, 'Paris/Llouvres.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(20, 'Le Marais', 48.858724, 2.359470, 'Paris/Mmarais.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(21, 'MontMartre', 48.887010, 2.343047, 'Paris/montMartre.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(22, 'Tour Montparnasse', 48.842441, 2.321922, 'Paris/montparnasse.jpeg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(23, 'Notre Dame de Paris ', 48.853153, 2.350491, 'Paris/notreDame.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(24, 'Place de la Condorde', 48.865633, 2.321300, 'Paris/placeConcorde.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(25, 'Sacre Coeur', 48.886944, 2.343072, 'Paris/sacreCoeur.jpeg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(26, 'Tour Eiffel', 48.858561, 2.294825, 'Paris/tourEiffel.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(27, 'Pantheon', 48.846419, 2.346821, 'Paris/pantheon.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(28, 'Grand Palais', 48.866321, 2.312572, 'Paris/grandPalais.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(29, 'Moulin Rouge', 48.884342, 2.332885, 'Paris/moulin.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4'),
(30, 'Les catacombes de Paris', 48.834080, 2.332830, 'Paris/catacombes.jpg', 'd2dfa4d6-6b8e-449d-afb4-c3ce018e35d4');

INSERT INTO serie_photo (listeserie_id, listephotos_id) VALUES
('71e058af-cf5f-4614-be3b-ec50835954e3',1),
('71e058af-cf5f-4614-be3b-ec50835954e3',2),
('71e058af-cf5f-4614-be3b-ec50835954e3',3),
('71e058af-cf5f-4614-be3b-ec50835954e3',4),
('71e058af-cf5f-4614-be3b-ec50835954e3',5),
('71e058af-cf5f-4614-be3b-ec50835954e3',6),
('71e058af-cf5f-4614-be3b-ec50835954e3',7),
('71e058af-cf5f-4614-be3b-ec50835954e3',8),
('71e058af-cf5f-4614-be3b-ec50835954e3',9),
('71e058af-cf5f-4614-be3b-ec50835954e3',10),

('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',11),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',12),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',13),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',14),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',15),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',16),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',17),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',18),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',19),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',20),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',21),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',22),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',23),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',24),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',25),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',26),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',27),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',28),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',29),
('d2dfa4d6-6b8e-449d-afb4-c3ce018e35d4',30);