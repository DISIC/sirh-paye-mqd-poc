-- Jeu de données du TEST-91
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
delete from job_detail;

-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test91Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:109802:","STIF:StopPoint:Q:108918:","STIF:StopPoint:Q:109310:","STIF:StopPoint:Q:109311:"]}');
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','GS','{"address":"http://10.222.9.208:8080/test91Producer_RATP","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:109802:","STIF:StopPoint:Q:108918:","STIF:StopPoint:Q:109310:","STIF:StopPoint:Q:109311:"]}');

COMMIT;