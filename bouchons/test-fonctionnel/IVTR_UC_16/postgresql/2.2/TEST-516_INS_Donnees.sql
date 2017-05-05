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
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'estimatedTimetable', 'TRUE');

INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES ('1','SNCF','SNCF-ACCES', 'PRODUCTEUR_DIFFUSEUR', '2.2', true, 'http://10.222.9.4:8080/SNCF-test116', true, true,'http://10.222.9.4:8080/SNCF-test249',true,'http://10.222.9.4:8080/bouchon-diffuseur',10,1,'RPC',false,true);
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, diffuseurgeneralmessageused, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, diffuseurgeneralmessageurl) 
VALUES ('2','RATP','100WSIVSIRI','PRODUCTEUR_DIFFUSEUR', '2.2', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.4:8080/RATP-test249',true ,10,1,'RPC',true,false,'http://10.222.9.205:8080/Ano209');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES ('3','KEOLIS','KEOLIS','PRODUCTEUR_DIFFUSEUR', '2.2', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.4:8080/KEOLIS-test245',10,1,'RPC',true,false);


INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('1','STIF:StopPoint:Q:108925:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('2','STIF:StopPoint:Q:109311:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('3','STIF:StopPoint:Q:109979:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('4','STIF:StopPoint:Q:109956:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('5','STIF:StopPoint:Q:108937:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('6','STIF:StopPoint:Q:108991:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('7','STIF:StopPoint:Q:108990:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('8','STIF:StopPoint:Q:109003:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('9','STIF:StopPoint:Q:109004:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('10','STIF:StopPoint:Q:109753:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('11','STIF:StopPoint:Q:109132:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('12','STIF:StopPoint:Q:109921:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('13','STIF:StopPoint:Q:109142:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('14','STIF:StopPoint:Q:109846:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('15','STIF:StopPoint:Q:109223:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('16','STIF:StopPoint:Q:108955:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('17','STIF:StopPoint:Q:108956:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('18','STIF:StopPoint:Q:108978:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('19','STIF:StopPoint:Q:109811:','FALSE','1');
INSERT INTO arret_producteur (id, monitoringref, souhaite, participant_id) VALUES ('20','STIF:StopPoint:Q:109812:','FALSE','1');

INSERT INTO operator (id, name, participant_id, active, code) VALUES ('1','RATP','2',true ,'RATP');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true ,'SNCF');

INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('1','AMIV:Line::004004009:','1');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('2','AMIV:Line::004004011:','2');
INSERT INTO ligne_producteur (id, lineref, participant_id) VALUES ('3','AMIV:Line::004004011:','3');

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
