-- Jeu de données du TEST-29
-- Vidage des tables
BEGIN;

DELETE FROM communication;


INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');


-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','2012-01-01','GS','{"address":"http://10.222.9.208:8080/test29Producer","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:108940:","STIF:StopPoint:Q:108941:"]}');

COMMIT;