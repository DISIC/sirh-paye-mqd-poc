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
DELETE FROM site;
DELETE FROM communication;


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

INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('1','SNCF','SNCF-ACCES', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://${ip_producer}:${port_producer}/SNCF-test116', true, true,'http://${ip_producer}:${port_producer}/SNCF-test249',true,'http://${ip_producer}:${port_producer}/bouchon-diffuseur',10,1,'RPC',false,true, 'HTTP');

INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, diffuseurgeneralmessageused, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, diffuseurgeneralmessageurl, diffuseurlinesdiscoveryused, diffuseurlinesdiscoveryurl, protocole) 
VALUES ('2','RATP','100WSIVSIRI','DIFFUSEUR', '2.2', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://${ip_producer}:${port_producer}/RATP-test249',true ,10,1,'RPC',true,false,'http://10.222.9.205:8080/Ano209',true,'http://${ip_producer}:${port_producer}/RATP-test249', 'HTTP');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('3','KEOLIS','KEOLIS','DIFFUSEUR', '2.2', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://${ip_producer}:${port_producer}/KEOLIS-test245',10,1,'RPC',true,false, 'HTTP');



INSERT INTO operator (id, name, participant_id, active, code) VALUES ('1','RATP','2',true ,'RATP');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true ,'SNCF');


INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('1','AMIV:Line::004004009:','1');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('2','AMIV:Line::004004009:','2');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('3','AMIV:Line::004004009:','3');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('4','AMIV:Line::004004011:','1');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('5','AMIV:Line::004004011:','2');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('6','AMIV:Line::004004011:','3');



INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'100WSIVSIRI');
INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'KEOLIS');
INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'SNCF-ACCES');


UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = now() at time zone 'GMT' WHERE id = 'OPERATOR_CODE';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'COMMUNICATION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'SUBSCRIPTION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'LIGNE_PRODUCTEUR';




COMMIT;
