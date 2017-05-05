BEGIN;

INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','SM', 'ACME-PROD', '{"address":"http://localhost:8081/acme","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108926:"}');

COMMIT;