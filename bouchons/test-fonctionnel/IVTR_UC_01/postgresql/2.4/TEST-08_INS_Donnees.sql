-- Jeu de données du TEST-08
BEGIN;


-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now();


-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','SM','{"address":"http://10.222.9.208:8080/test8Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');


COMMIT;