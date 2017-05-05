-- Jeu de données du TEST-01

BEGIN;


-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';

INSERT INTO checkstatus_response (ID,STATUS , RESPONDER_REF) VALUES (nextval('seq_checkstatus_response'), true, 'SNCF-ACCESS');

-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','2012-01-01','SM','{"address":"http://localhost:8080/test244","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108925:"}');


COMMIT;