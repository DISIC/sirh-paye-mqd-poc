-- Jeu de données du TEST-308
-- Vidage des tables
BEGIN;

-- suppression des synchro existantes
DELETE  FROM synchro_referentiels;

-- insertion d'une synchro transporteur 
INSERT INTO synchro_referentiels (id, dernieremaj, nomfichier)
VALUES ('OPERATOR','2014-03-26 14:56:00.404', null);
INSERT INTO synchro_referentiels (id, dernieremaj, nomfichier)
VALUES ('PARTICIPANT','2014-03-26 14:56:00.404', null);

-- suppression des partenaires
DELETE  FROM participant;

-- insertion d'une synchro partenaire 
INSERT INTO participant (id, name, participantref, siriversion, address, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, partenaireactif)
VALUES (1,'RATP','RATP','2.4','http://localhost:8080/ratpProducer',10,1,'RPC',false,true,true);
-- insertion d'une synchro partenaire 
INSERT INTO participant (id, name, participantref, siriversion, address, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, partenaireactif)
VALUES (2,'SNCF','SNCF','2.4','http://localhost:8080/ratpProducer',10,1,'RPC',false,true,true);

-- suppression des job
DELETE  FROM job_detail;
-- insertion d'un job GetStopMonitoring vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','SM','{"address":"http://10.222.9.170:8081/bouchon-producteur-ok","version":"2.4","monitoringRef":"STIF:StopPoint:Q:2"}');
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/30 * * * * ?','SM','{"address":"http://10.222.9.170:8082/bouchon-producteur-ko","version":"2.4","monitoringRef":"STIF:StopPoint:Q:2"}');

-- suppression des transporteurs
DELETE  FROM operator;

-- insertion des transporteurs
INSERT INTO operator VALUES (1,'RER','RER','RATP');

COMMIT;