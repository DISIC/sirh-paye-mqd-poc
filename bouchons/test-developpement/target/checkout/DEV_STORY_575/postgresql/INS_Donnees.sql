-- Jeu de données du TEST-346 c'est un exemple ce n'est pas un jeu de donnée qui respecte tout les cas métier prévu

BEGIN;

-- Purge des tables
DELETE FROM arret_producteur;
DELETE FROM operator;
DELETE FROM participant;
DELETE FROM subscription_producteur;
DELETE FROM site;

--Insertion
INSERT INTO site (id, host, login, password, port, remote_directory, type_referentiel)
VALUES (1, 'localhost', 'msabatte', 'zda8dzhy', '22', '/home/mickael/stifSFTP/reflex', 'REFLEX');
INSERT INTO site (id, host, login, password, port, remote_directory, type_referentiel)
VALUES (2, 'localhost', 'msabatte', 'zda8dzhy', '22', '/home/mickael/stifSFTP/codifligne', 'CODIFLIGNE');

INSERT INTO participant (id, name, participantref, siriversion,  nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES (1,'SNCF','SNCF','2.4',10,1,'RPC',true,false);
INSERT INTO participant (id, name, participantref, siriversion,  nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete) 
VALUES (2,'RATP','RATP','2.4',10,1,'DOCUMENT',true,false);

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (1,null,'STIF:StopPoint:Q:108925:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (2,null,'STIF:StopPoint:Q:108926:',false,2);

COMMIT;
