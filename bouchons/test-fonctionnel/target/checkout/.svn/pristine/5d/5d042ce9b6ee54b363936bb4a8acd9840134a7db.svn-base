-- Jeu de données du TEST-34
-- Vidage des tables
BEGIN;

update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job checkStatus vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/5 * * * * ?','2012-01-01','SM','{"address":"http://10.222.9.208:8080/test34Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');


COMMIT;
