-- Jeu de données du TEST-36
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test36Producer","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:2","STIF:StopPoint:Q:1"]}');

COMMIT;