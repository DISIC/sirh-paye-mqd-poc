-- Jeu de données du TEST-13
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.205:8080/Test","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:50090527:","STIF:StopPoint:Q:50090639:","STIF:StopPoint:Q:50090634:","STIF:StopPoint:Q:50090637:"]}');

COMMIT;