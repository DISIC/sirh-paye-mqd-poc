-- Jeu de données du TEST-01

BEGIN;



-- insertion d'un job SM sur le STIF:StopPoint:Q:108940:
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE, PARTICIPANTREF, REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','SM', 'THALES-PROD', '{"address":"http://10.222.9.205:8080/Producteur","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');


COMMIT;