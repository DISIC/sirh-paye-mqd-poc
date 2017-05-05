-- Jeu de données du TEST-05
-- Vidage des tables
BEGIN;


-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2012-01-01','SM','{"address":"http://10.222.9.205:8080/test5Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}', 'SNCF-ACCES');

COMMIT;