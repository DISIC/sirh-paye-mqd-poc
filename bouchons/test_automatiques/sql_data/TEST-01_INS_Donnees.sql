-- Jeu de données du TEST-01

BEGIN;


-- suppression des jobs existants
delete from job_detail;

-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','SM', 'SNCF-ACCES', '{"address":"http://${ip_producer}:${port_producer}/test1Producer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');



COMMIT;
