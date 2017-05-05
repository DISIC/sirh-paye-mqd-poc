-- Jeu de données du TEST-346 c'est un exemple ce n'est pas un jeu de donnée qui respecte tout les cas métier prévu

BEGIN;
-- Purge des tables
DELETE FROM arret_producteur;
DELETE FROM operator;
DELETE FROM participant;

--Insertion
INSERT INTO participant (id, name, participantref, siriversion, address, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, partenaireactif) 
VALUES (1,'SNCF','SNCF','2.4','http://localhost:8088/bouchon-producteur',10,1,'RPC',false,true,true);

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) VALUES (1,null,'STIF:StopPoint:Q:17',null,1);

COMMIT;