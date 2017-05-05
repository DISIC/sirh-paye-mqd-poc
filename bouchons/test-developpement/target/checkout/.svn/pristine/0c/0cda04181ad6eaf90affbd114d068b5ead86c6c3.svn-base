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

    
DELETE FROM job_detail;
INSERT INTO job_detail(
            id,  cron,  participantref, suppressiondemandee, version, sirirequesttype, requete)
    VALUES (19, '0/15 * * * * ?',  'SNCF-ACCES', 'FALSE', '2014-07-23 17:08:00.272', 
            'GM','{"address":"http://localhost:8080/testGeneralMessage"}');
INSERT INTO job_detail(
            id,  cron,  participantref, suppressiondemandee, version, sirirequesttype, requete)
    VALUES (20, '0/15 * * * * ?',  'SNCF-ACCES', 'FALSE', '2014-07-23 17:08:00.272', 
            'SM','{"address":"http://localhost:8080/GMProducer","version":"2.4","monitoringRef":"STIF:StopPoint:Q:108940:"}');

DELETE FROM operator;
DELETE FROM arret_diffuseur;
DELETE FROM arret_producteur;
DELETE FROM participant;
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style)
    VALUES (1, 'SNCF', 1, 10, 'SNCF-ACCES','DIFFUSEUR','2.4','RPC');
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style)
    VALUES (2, 'RATP', 1, 10, 'S100WSIVSIRI','DIFFUSEUR','2.4','RPC');
INSERT INTO participant(
            id, name, nombrearretparrequete, nombrerequeteparminute, participantref, 
            typepartenaire, siriversion, style)
    VALUES (3, 'KEOLIS', 1, 10, 'KEOLIS_SIVIK','DIFFUSEUR','2.4','RPC');
INSERT INTO arret_diffuseur(
            id, monitoringref, participant_id)
    VALUES (1, 'STIF:StopPoint:Q:108925:', 2);
INSERT INTO arret_diffuseur(
            id, monitoringref, participant_id)
    VALUES (2, 'STIF:StopPoint:Q:108926:', 1);
INSERT INTO operator(
            id, name, participant_id, active,code)
    VALUES (1, 'SNCF', 1, TRUE,'SNCF');

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
    VALUES (2, TRUE, 'S100WSIVSIRI');
INSERT INTO checkstatus_response(
            id, status, responder_ref)
    VALUES (3, TRUE, 'KEOLIS_SIVIK');
