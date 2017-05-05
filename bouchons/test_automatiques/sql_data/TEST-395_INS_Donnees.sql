BEGIN;


-- ne pas supprimer les jbos existants 


INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','SM', 'SNCF-ACCES', '{"address":"http://${ip_producer}:${port_producer}/test395ProducerSM","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108926:"}');


COMMIT;
