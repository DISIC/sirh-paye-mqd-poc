-- Jeu de données du TEST-569
-- Vidange des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','GS','{"address":"http://10.222.9.205:8080/test36Producer","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:108925:","STIF:StopPoint:Q:108926:"]}', 'SNCF-ACCES');

COMMIT;