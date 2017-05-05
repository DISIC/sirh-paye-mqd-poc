
-- suppression des jobs existants
delete from job_detail;

INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','SM', 'SNCF-ACCES', '{"address":"http://10.222.9.205:8080/test173ProducerSM","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF, SIRIREQUESTTYPE, REQUETE) VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','SNCF-ACCES','GM','{"address":"http://10.222.9.205:8080/test173ProducerGM2","version":"2.4","participantRef":"SNCF-ACCES"}');
