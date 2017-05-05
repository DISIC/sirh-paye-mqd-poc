-- Jeu de données du TEST-30
-- Vidage des tables
BEGIN;
DELETE FROM PARTICIPANT;
DELETE FROM CHECKSTATUSRESPONSE;
DELETE FROM communication;


INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'checkStatus', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication (nomgroup, nomchamp, active) VALUES ('emission', 'generalMessage', 'TRUE');

update job_detail set suppressiondemandee = false;

-- insertion d'un partenaire
INSERT INTO Participant (id, participantId, name, participantRef,siriVersion) 
VALUES (1,'SNCF','SNCF','SNCF','2.4');

COMMIT;
