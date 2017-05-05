-- Jeu de données du TEST-03
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';

-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','2012-01-01','SM','{"address":"http://10.222.9.208:8080/test3Producer_2","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');

COMMIT;