-- Jeu de données du TEST-16
-- Vidage des tables

BEGIN;
DELETE FROM CHECKSTATUS_RESPONSE;


-- Suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';
-- insertion d'un job checkStatus vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE,PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','2012-01-01','CS','{"address":"http://10.222.9.208:8080/test18Producer","participantRef":"SNCF-ACCES"}','SNCF-ACCES');

COMMIT;