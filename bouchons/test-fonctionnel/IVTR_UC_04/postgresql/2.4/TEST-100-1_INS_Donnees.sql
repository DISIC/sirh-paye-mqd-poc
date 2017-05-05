-- Jeu de données du TEST-100
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test100-1Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:110048:","STIF:StopPoint:Q:109925:","STIF:StopPoint:Q:109926:"]}');
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test100-1Producer_RATP","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109956:","STIF:StopPoint:Q:109974:","STIF:StopPoint:Q:109943:","STIF:StopPoint:Q:110048:"]}');

COMMIT;