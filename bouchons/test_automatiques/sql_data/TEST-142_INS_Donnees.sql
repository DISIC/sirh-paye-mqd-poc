

BEGIN;

-- Purge des tables
DELETE FROM operator;
DELETE FROM arret_producteur;
DELETE FROM arret_diffuseur;
DELETE FROM ligne_producteur;
DELETE FROM arret_ligne;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_producteur;
DELETE FROM participant; -- NB: A supprimer en dernier sinon constraint violation
DELETE FROM site;
DELETE FROM communication;

--Insertion
INSERT INTO participant (id, name, participantref, siriversion, partenaireActif, typepartenaire, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('1','SNCF','SNCF-ACCES','2.4',true,'PRODUCTEUR_DIFFUSEUR', true,'http://${ip_producer}:${port_producer}/SNCF-test133',true,'http://${ip_producer}:${port_producer}/bouchon-diffuseur',10,1,'RPC',true,false, 'HTTP');


INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true, 'SNCF');

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,1);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108926:',1);

INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'subscribe', 'TRUE');

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'COMMUNICATION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_PRODUCTEUR';

COMMIT;
