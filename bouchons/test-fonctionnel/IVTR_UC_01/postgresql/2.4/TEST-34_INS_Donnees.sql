-- Jeu de données du TEST-34
-- Vidage des tables
BEGIN;

delete from job_detail;


-- insertion d'un job checkStatus vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/5 * * * * ?','SM','{"address":"http://10.222.9.208:8080/test34Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');


COMMIT;
