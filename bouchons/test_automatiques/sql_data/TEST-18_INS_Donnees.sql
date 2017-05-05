-- Jeu de données du TEST-16
-- Vidage des tables

BEGIN;
DELETE FROM CHECKSTATUS_RESPONSE;


-- Suppression des jobs existants
delete from job_detail;
-- insertion d'un job checkStatus vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','CS','{"address":"http://${ip_producer}:${port_producer}/test18Producer","participantRef":"SNCF-ACCES"}','SNCF-ACCES');

COMMIT;
