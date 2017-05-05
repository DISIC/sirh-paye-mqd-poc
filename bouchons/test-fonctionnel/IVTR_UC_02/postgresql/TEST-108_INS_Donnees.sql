-- Jeu de données du TEST-12
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','GS','{"address":"http://10.222.9.205:8080/test12Producer","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:108940:","STIF:StopPoint:Q:108941:"]}', 'SNCF-ACCES');

COMMIT;