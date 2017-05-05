BEGIN;

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';

-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,PARTICIPANTREF, CRON,VERSION, SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'SNCF-ACCES','*/15 * * * * ?','2012-01-01','SM','{"address":"http://10.222.9.205:8080/SNCF-test249","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108925:"}');


COMMIT;