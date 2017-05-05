-- Jeu de données du TEST-31
-- Vidage des tables
BEGIN;
DELETE FROM communication;


INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');

-- Suppression des jobs existants
delete from job_detail;

-- insertion d'un job checkStatus vers un producteur connu 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','CS','{"address":"http://10.222.9.208:8080/test31ProducerConnu"}');

-- insertion d'un job checkStatus vers un producteur inconnu
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','CS','{"address":"http://10.222.9.208:8080/test31ProducerInconnu"}');


COMMIT;


