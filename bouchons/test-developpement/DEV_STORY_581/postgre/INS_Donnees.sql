-- Jeu de données du TEST-01

BEGIN;


-- suppression des jobs existants
delete from job_detail;
DELETE FROM checkstatus_response;
DELETE FROM operator_code;
DELETE FROM OPERATOR;

DELETE FROM arret_producteur;

DELETE FROM PARTICIPANT;




-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','SM','{"address":"http://localhost:8080/test1Producer","version":"2.2","monitoringRef":"STIF:StopPoint:Q:108940:"}');
INSERT INTO checkstatus_response(id, status, service_started_time, response_timestamp, responder_ref, request_message_ref, error_condition)
VALUES (1, TRUE, null, null, 'RATP',null, null);
INSERT INTO participant (ID, name,nombrearretparrequete,nombrerequeteparminute,participantref,siriversion,style) VALUES (1,'SNCF',1,2,'SNCF-ACCES','2.2','RPC');
INSERT INTO participant (ID, name,nombrearretparrequete,nombrerequeteparminute,participantref,siriversion,style) VALUES (2,'RATP',1,2,'RATP','2.2','RPC');

INSERT INTO operator(id, name, participant_id, active)
VALUES (1, 'SNCF-ACCES', 1, true);

INSERT INTO operator_code(id, operatorref, operator_id)
VALUES (1,'SNCF-ACCES', 1);



    
COMMIT;