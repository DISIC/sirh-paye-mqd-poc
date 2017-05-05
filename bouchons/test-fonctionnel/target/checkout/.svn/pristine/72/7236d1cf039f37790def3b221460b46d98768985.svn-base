

-- suppression des jobs existants
update job_detail set suppressiondemandee = true, datemaj=now() at time zone 'GMT';

INSERT INTO job_detail (ID,CRON,VERSION,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','2012-01-01','SM', 'SNCF-ACCES', '{"address":"http://10.222.9.205:8080/test178ProducerSM","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');
INSERT INTO job_detail (ID,CRON,VERSION,PARTICIPANTREF, SIRIREQUESTTYPE, REQUETE) VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','2012-01-01','SNCF-ACCES','GM','{"address":"http://10.222.9.205:8080/test178ProducerGM","version":"2.4","participantRef":"SNCF-ACCES"}');
