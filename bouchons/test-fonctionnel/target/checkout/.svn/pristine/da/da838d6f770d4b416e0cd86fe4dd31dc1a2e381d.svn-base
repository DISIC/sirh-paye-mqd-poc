-- Jeu de données du TEST-100
-- Vidage des tables
BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test99Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:110048:","STIF:StopPoint:Q:109925:","STIF:StopPoint:Q:109926:"]}');
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test99Producer_RATP","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109956:","STIF:StopPoint:Q:109974:","STIF:StopPoint:Q:109943:","STIF:StopPoint:Q:110048:"]}');

COMMIT;