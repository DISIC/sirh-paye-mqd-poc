-- Jeu de données du TEST-13
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','GS','{"address":"http://10.222.9.205:8080/Test","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:50090527:","STIF:StopPoint:Q:50090639:","STIF:StopPoint:Q:50090634:","STIF:StopPoint:Q:50090637:"]}');

COMMIT;