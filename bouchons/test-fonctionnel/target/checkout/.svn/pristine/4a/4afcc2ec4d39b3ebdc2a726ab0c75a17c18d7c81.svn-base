-- Jeu de données du TEST-102
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test100Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:110048:LOC","STIF:StopPoint:Q:109925:"]}');

COMMIT;