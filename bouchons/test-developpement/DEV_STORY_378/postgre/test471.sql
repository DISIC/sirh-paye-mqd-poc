


-- suppression des jobs existants
delete from job_detail;
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) VALUES (1,'5/10 * * * * ?','SNCF-ACCES','GM','{"address":"http://localhost:8080/test","version":"2.4","participantRef":"SNCF-ACCES"}');
