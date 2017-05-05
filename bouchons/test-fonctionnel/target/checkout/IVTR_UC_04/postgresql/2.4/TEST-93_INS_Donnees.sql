-- Jeu de données du TEST-93
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';

-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test93Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:109802:","STIF:StopPoint:Q:108918:","STIF:StopPoint:Q:109310:","STIF:StopPoint:Q:109311:"]}');
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test93Producer_RATP","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:109802:","STIF:StopPoint:Q:108918:","STIF:StopPoint:Q:109310:","STIF:StopPoint:Q:109311:"]}');

COMMIT;