BEGIN;

DELETE FROM communication;


INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');

INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES ('5','RATP','100WSIVSIRI','DIFFUSEUR', '2.4', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.4:8080/RATP-test249',10,1,'RPC',true,false);


INSERT INTO operator (id, name, participant_id, active, code) VALUES ('5','RATP','5',true ,'RATP');

INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'100WSIVSIRI');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,5);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108925:',2);

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = now() at time zone 'GMT' WHERE id = 'OPERATOR_CODE';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'COMMUNICATION';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';

COMMIT;
