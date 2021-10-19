USE rest01_db;

INSERT INTO `client` (`id`, `nom`, `prenom`) VALUES
('LB', 'Ben'),
('LB', 'Vince');

INSERT INTO `livre` (`id`, `auteur`, `titre`) VALUES
('Jules Verne', 'Voyage au centre de la Terre'),
('Willima Gibson', 'Neuromancien'),
('Bernard Clavel', 'Amarok'),
('Simenon', 'Les frères Rico');

INSERT INTO `emprunt` (`id`, `date_debut`, `date_fin`, `delai`, `id_client`) VALUES
('2022-03-10', '2022-03-11', 10, 1),
('2022-03-10', '2022-03-11', 10, 2),
('2022-03-10', '2022-03-11', 10, 1);

INSERT INTO `compo` (`id_liv`, `id_emp`) VALUES
(1, 1),
(2, 1),
(3, 1),
(3, 2),
(2, 3),
(4, 3);
