-- Jeu de données du TEST-15
-- Vidage des tables

BEGIN;
DELETE FROM CHECKSTATUS_RESPONSE;


-- Suppression des jobs existants
delete from job_detail;
-- insertion d'un job checkStatus vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/5 * * * * ?','CS','{"address":"http://10.222.9.205:8080/test15Producer","participantRef":"SNCF-ACCES"}','SNCF-ACCES');

COMMIT;