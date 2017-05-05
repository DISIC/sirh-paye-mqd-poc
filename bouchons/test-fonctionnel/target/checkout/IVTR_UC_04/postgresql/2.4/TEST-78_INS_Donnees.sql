-- Jeu de données du TEST-78

BEGIN;

--Insertion
INSERT INTO ARRETPARTICIPANT (ID,dateDerniereDemande, monitoringRef, requete,FK_PARTICIPANT) 
VALUES (nextval('seq_arretparticipant'),null,'STIF:StopPoint:Q:108940:',null,1);

COMMIT;