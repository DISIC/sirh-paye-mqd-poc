-- Jeu de données du TEST-01

BEGIN;


-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';
-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2012-01-01','SM', 'SNCF-ACCES', '{"address":"http://10.222.9.205:8080/test1Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');


COMMIT;