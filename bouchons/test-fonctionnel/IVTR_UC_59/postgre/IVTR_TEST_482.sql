--Veilez à initialiser les référentiels avec les jeux des tests fonctionnels.

DELETE FROM communication;
INSERT INTO communication(
            nomgroup, nomchamp, active)
    VALUES ('emission', 'generalMessage', 'TRUE');
INSERT INTO communication(
            nomgroup, nomchamp, active)
    VALUES ('emission', 'stopMonitoring', 'TRUE');
INSERT INTO communication(
            nomgroup, nomchamp, active)
    VALUES ('reception', 'generalMessage', 'TRUE');
INSERT INTO communication(
            nomgroup, nomchamp, active)
    VALUES ('reception', 'stopMonitoring', 'TRUE');
    
DELETE FROM operator;
DELETE FROM arret_diffuseur;
DELETE FROM arret_producteur;
DELETE FROM subscription_diffuseur_gm;
DELETE FROM ligne_producteur;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_producteur;
DELETE FROM participant;
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style,producteurstopmonitoringused,producteurstopmonitoringurl)
    VALUES (1, 'SNCF', 1, 10, 'SNCF-ACCES','DIFFUSEUR','2.4','RPC',true,'http://10.222.9.205:8080/TEST973');
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style,diffuseurstopmonitoringused,diffuseurstopmonitoringurl)
    VALUES (2, 'RATP', 1, 10, '100WSIVSIRI','DIFFUSEUR','2.4','RPC',true,'http://10.222.9.205:8080/TEST973');
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style,diffuseurstopmonitoringused,diffuseurstopmonitoringurl)
    VALUES (3, 'KEOLIS', 1, 10, 'KEOLIS_SIVIK','DIFFUSEUR','2.4','RPC',true,'http://10.222.9.205:8080/TEST973');
INSERT INTO arret_diffuseur(
            id, monitoringref, participant_id)
    VALUES (1, 'STIF:StopPoint:Q:108925:', 2);
INSERT INTO arret_diffuseur(
            id, monitoringref, participant_id)
    VALUES (2, 'STIF:StopPoint:Q:108926:', 1);
INSERT INTO operator(
            id, name, participant_id, active,code)
    VALUES (1, 'RATP', 2, TRUE,'RATP');

INSERT INTO arret_producteur(
            id,  monitoringref, souhaite, participant_id)
    VALUES (1, 'STIF:StopPoint:Q:108925:', TRUE, 1);
INSERT INTO arret_producteur(
            id,  monitoringref, souhaite, participant_id)
    VALUES (2, 'STIF:StopPoint:Q:108926:', TRUE, 1);
DELETE FROM synchro_referentiels;
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('CODIFLIGNE',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('COMMUNICATION',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('PARTICIPANT',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('REFLEX',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('SUBSCRIPTION',now());


DELETE FROM checkstatus_response;
INSERT INTO checkstatus_response(
            id, status, responder_ref)
    VALUES (1, TRUE, 'SNCF-ACCES');
INSERT INTO checkstatus_response(
            id, status, responder_ref)
    VALUES (2, TRUE, '100WSIVSIRI');
INSERT INTO checkstatus_response(
            id, status, responder_ref)
    VALUES (3, TRUE, 'KEOLIS_SIVIK');



-- suppression des jobs existants
delete from job_detail;
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:108925:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:21100:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:30:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:109806:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:2847:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:23777:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:109802:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:8349:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:31656:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:108918:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:31656:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:941:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','2014-07-01','SNCF-ACCES','SM','{"address":"http://10.222.9.205:8080/TEST973","monitoringRef":"STIF:StopPoint:Q:1147:","participantRef":"SNCF-ACCES","siriVersion":"2.4"}');