-- Jeu de données du TEST-36
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test36Producer","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:2","STIF:StopPoint:Q:1"]}');

COMMIT;