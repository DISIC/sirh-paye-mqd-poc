-- Jeu de données du TEST-01

BEGIN;


-- suppression des jobs existants
delete from job_detail;


-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','GM','{"address":"http://localhost:8080/test377Producer","version":"2.4"}');


COMMIT;