

BEGIN;

-- Purge des tables
DELETE FROM operator;
DELETE FROM arret_producteur;
DELETE FROM arret_diffuseur;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_producteur;
DELETE FROM participant; -- NB: A supprimer en dernier sinon constraint violation
DELETE FROM site;


--Insertion
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, producteursubscribeused, producteursubscribeurl) 
VALUES ('1','SNCF','SNCF-ACCES', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://10.222.9.214:8080/SNCF-test116', true, true,'http://10.222.9.214:8080/SNCF-test116',true,'http://10.222.9.214:8080/bouchon-diffuseur',10,1,'RPC',true,false, true, 'http://10.222.9.214:8080/SNCF-test116');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES ('2','RATP','100WSIVSIRI','DIFFUSEUR', '2.4', true,true,'http://10.222.9.214:8080/bouchon-producteur',true,'http://10.222.9.214:8080/RATP-test126',10,1,'RPC',true,false);

INSERT INTO operator (id, name, participant_id, active, code) VALUES ('1','RATP','2',true, 'RATP');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true, 'SNCF');


UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'COMMUNICATION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108926:',false,2);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108925',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108926:',1);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108924:',2);

COMMIT;
