

-- suppression des jobs existants
delete from job_detail;
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','SNCF-ACCES','GM','{"address":"http://10.222.9.205:8080/test179ProducerGM2","version":"2.4","participantRef":"SNCF-ACCES"}');
