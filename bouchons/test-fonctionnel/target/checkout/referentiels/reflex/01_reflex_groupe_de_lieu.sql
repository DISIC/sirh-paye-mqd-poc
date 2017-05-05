BEGIN;

-- Suppression du référentiel existant
DELETE FROM reflex_zone_d_embarquement;
DELETE FROM reflex_zone_de_lieu;
DELETE FROM reflex_lieu_d_arret;
DELETE FROM reflex_groupe_de_lieu;

-- Insertion des groupes de lieux
INSERT INTO reflex_groupe_de_lieu (id, id_ref_a, id_version, nom) VALUES (1, '110156', '123675', 'Aéroport d''Orly');

COMMIT;
