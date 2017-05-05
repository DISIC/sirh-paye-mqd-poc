BEGIN;

-- suppression des jobs existants
delete from job_detail;

-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,PARTICIPANTREF, CRON, SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'SNCF-ACCES','*/5 * * * * ?','SM','{"address":"http://${ip_producer}:${port_producer}/SNCF-test249","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108925:"}');


COMMIT;
