

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';
INSERT INTO job_detail (ID,CRON,VERSION,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','2012-01-01','SNCF-ACCES','GM','{"address":"http://10.222.9.205:8080/test179ProducerGM2","version":"2.4","participantRef":"SNCF-ACCES"}');
