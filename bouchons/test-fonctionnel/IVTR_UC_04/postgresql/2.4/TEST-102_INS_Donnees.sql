-- Jeu de données du TEST-102
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test100Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:110048:LOC","STIF:StopPoint:Q:109925:"]}');

COMMIT;