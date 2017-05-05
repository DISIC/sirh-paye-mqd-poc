BEGIN;

-- Suppression des partenaires
DELETE FROM checkstatus_response;
DELETE FROM operator;
DELETE FROM arret_producteur;
DELETE FROM arret_diffuseur;
DELETE FROM ligne_producteur;
DELETE FROM arret_ligne;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_diffuseur_gm;
DELETE FROM subscription_producteur;
DELETE FROM participant; -- NB: A supprimer en dernier sinon constraint violation
DELETE FROM communication;
DELETE FROM site;

INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopPointDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopPointDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'linesDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'linesDiscovery', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'estimatedTimetable', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'subscribe', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'subscribe', 'TRUE');


INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('1','SNCF','SNCF-ACCES', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://10.222.9.4:8080/SNCF-test116', true, true,'http://10.222.9.4:8080/SNCF-test249',true,'http://10.222.9.4:8080/bouchon-diffuseur',10,1,'RPC',false,true, 'HTTP');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, diffuseurgeneralmessageused, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, diffuseurgeneralmessageurl, protocole) 
VALUES ('2','RATP','100WSIVSIRI','DIFFUSEUR', '2.4', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.4:8080/RATP-test249',true ,10,1,'RPC',true,false,'http://10.222.9.205:8080/Ano209', 'HTTP');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('3','KEOLIS','KEOLIS','DIFFUSEUR', '2.4', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.4:8080/KEOLIS-test245',10,1,'RPC',true,false, 'HTTP');


INSERT INTO operator (id, name, participant_id, active, code) VALUES ('1','RATP','2',true ,'RATP');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true ,'SNCF');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('3','SNCF-ACCES','1',true, 'SNCF-ACCES');


INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'100WSIVSIRI');
INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'KEOLIS');
INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'SNCF-ACCES');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108940:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108926:',false,2);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108925',3);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108925',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108940',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108926',1);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108926',2);

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = now() at time zone 'GMT' WHERE id = 'OPERATOR_CODE';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'COMMUNICATION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'SUBSCRIPTION_DIFFUSEUR';


-- suppression des jobs existants
delete from job_detail;

INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) VALUES (nextval('seq_job_detail'),'*/20 * * * * ?','SM', 'SNCF-ACCES', '{"address":"http://${ip_producer}:${port_producer}/injection","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF, SIRIREQUESTTYPE, REQUETE) VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','SNCF-ACCES','GM','{"address":"http://${ip_producer}:${port_producer}/injection","version":"2.4","participantRef":"SNCF-ACCES"}');


COMMIT;
