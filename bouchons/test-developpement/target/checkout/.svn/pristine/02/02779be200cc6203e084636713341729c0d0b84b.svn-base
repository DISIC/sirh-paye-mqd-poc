BEGIN;

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF, SIRIREQUESTTYPE, REQUETE) 
VALUES (nextval('SEQ_job_detail'),'*/15 * * * * ?','ACME-PROD','GM','{"address":"http://localhost:8081/GeneralMessage","version":"2.4","participantRef":"ACME-PROD"}');

COMMIT;