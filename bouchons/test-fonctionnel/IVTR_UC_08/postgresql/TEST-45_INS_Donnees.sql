-- Jeu de données du TEST-45
-- Vidage des tables
BEGIN;

DELETE FROM reflex_zone_d_embarquement;
DELETE FROM reflex_zone_de_lieu;
DELETE FROM reflex_lieu_d_arret;
DELETE FROM reflex_groupe_de_lieu;

-- Insertion d'un groupe de lieu qui sera modifié
INSERT INTO reflex_groupe_de_lieu(id, id_ref_a, id_version, nom)
VALUES (nextval('seq_reflex_groupe_de_lieu'), '110156','123675',"Aéroport d'Orly");

-- Insertion d'un lieu d'arrêt qui sera modifié
INSERT INTO reflex_lieu_d_arret(id, id_ref_a, id_version, nom, id_type_arret, libelle_type_arret, reflex_groupe_de_lieu_id)
VALUES (nextval('seq_reflex_lieu_d_arret'), '21100','114261',"AEROPORT D'ORLY (TERMINAL OUEST)",'','', currval('seq_reflex_groupe_de_lieu'));

-- Insertion d'une zone de lieu qui sera modifiée
INSERT INTO reflex_zone_de_lieu(id, id_ref_a, id_version, nom, id_type_arret, libelle_type_arret, reflex_lieu_d_arret_id)
VALUES (nextval('seq_reflex_zone_de_lieu'), '30','114260',"AEROPORT D'ORLY (TERMINAL OUEST)",'5','Arrêt de bus', nextval('seq_reflex_lieu_d_arret'));

-- Insertion d'une zone d'embarquement qui sera modifiée
INSERT INTO reflex_zone_d_embarquement(id, id_ref_a, id_version, nom, id_type_arret, libelle_type_arret, srs_name, x_coord, y_coord, reflex_zone_lieu_id)
VALUES (nextval('seq_reflex_zone_d_embarquement'), '109311','114257','AEROPORT ORLY ORLYRAIL','5','Arrêt de bus','2154','000000.000','000000.000', currval('seq_reflex_zone_de_lieu'));


-- Insertion d'un groupe de lieu qui sera supprimé
INSERT INTO reflex_groupe_de_lieu(id, id_ref_a, id_version, nom)
VALUES (nextval('seq_reflex_groupe_de_lieu'), '1','1',"Aéroport");

-- Insertion d'un lieu d'arrêt qui sera supprimé
INSERT INTO reflex_lieu_d_arret(id, id_ref_a, id_version, nom, id_type_arret, libelle_type_arret, reflex_groupe_de_lieu_id)
VALUES (nextval('seq_reflex_lieu_d_arret'), '1','1','AEROPORT','','', currval('seq_reflex_groupe_de_lieu'));

-- Insertion d'une zone de lieu qui sera supprimée
INSERT INTO reflex_zone_de_lieu(id, id_ref_a, id_version, nom, id_type_arret, libelle_type_arret, reflex_lieu_d_arret_id)
VALUES (nextval('seq_reflex_zone_de_lieu'), '1','1','AEROPORT','5','Arrêt de bus', nextval('seq_reflex_lieu_d_arret'));

-- Insertion d'une zone d'embarquement
INSERT INTO reflex_zone_d_embarquement(id, id_ref_a, id_version, nom, type_arret, id_type_arret, libelle_type_arret, srs_name, x_coord, y_coord, reflex_zone_lieu_id)
VALUES (nextval('seq_reflex_zone_d_embarquement'), '1','1','AEROPORT','5','5','Arrêt de bus','2154','000000.000','000000.000', currval('seq_reflex_zone_de_lieu'));

COMMIT;