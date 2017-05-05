-- Jeu de données du TEST-41
-- Vidage des tables
BEGIN;
DELETE FROM PARTICIPANT;
DELETE FROM CHECKSTATUSRESPONSE;
DELETE FROM codifligne;
DELETE FROM reflex_zone_d_embarquement;
DELETE FROM reflex_zone_de_lieu;
DELETE FROM reflex_lieu_d_arret;
DELETE FROM reflex_groupe_de_lieu;
DELETE FROM communication;


INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');


update job_detail set suppressiondemandee = false;

-- insertion d'un partenaire
INSERT INTO Participant (id, participantId, name, participantRef,siriVersion) 
VALUES (1,'SNCF','SNCF','SNCF','2.4');

--insertion d'une ligne
INSERT INTO codifligne(id, code_stif, id_ref_lig_a, id_ref_lig_c, transporteur1, nom)
VALUES (nextval('seq_codifligne'), '4004016', 'A00043', 'C00045', 'Kéolis Devillairs', 'RDV');

-- insertion d'un arrêt
INSERT INTO reflex_groupe_de_lieu(id, version, nom, type, localite, date_debut, date_fin)
VALUES (1, '1.0', 'La Défense', null, 'Puteaux', '01/01/2000', null);

INSERT INTO reflex_lieu_d_arret(id, version, nom, type, accessibilite, date_debut, date_fin, correspondance, reflex_groupe_de_lieu_id)
VALUES (2, '1.0', 'La Défense - Grande Arche', null, true, '01/01/2000', null, true, 1);

INSERT INTO reflex_zone_de_lieu(id, version, nom, type, accessibilite, date_debut, date_fin, reflex_lieu_d_arret_id)
VALUES (3, '1.0', 'La Défense', null, true, '01/01/2000', null, 2);

INSERT INTO reflex_zone_d_embarquement(id, version, nom, type, accessibilite, date_debut, date_fin, geohash, correspondance, reflex_zone_lieu_id)
VALUES (4, '1.0', 'La Défense (Grande Arche)', 1, true, '01/01/2000', null, 'u09w5h07qer7', true, 3);

COMMIT;