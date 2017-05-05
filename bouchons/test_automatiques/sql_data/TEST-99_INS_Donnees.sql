BEGIN;

-- Suppression des partenaires
DELETE FROM checkstatus_response;
DELETE FROM operator;
DELETE FROM arret_producteur;
DELETE FROM arret_diffuseur;
DELETE FROM ligne_producteur;
DELETE FROM arret_ligne;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_diffuseur_gm;
DELETE FROM subscription_producteur;
DELETE FROM participant; -- NB: A supprimer en dernier sinon constraint violation
DELETE FROM site;


INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('1','SNCF','SNCF-ACCES', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://10.222.9.205:8080/SNCF-test116', true, true,'http://10.222.9.205:8080/SNCF-test249',true,'http://10.222.9.205:8080/bouchon-diffuseur',10,1,'RPC',false,true, 'HTTP');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, diffuseurgeneralmessageused, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, diffuseurgeneralmessageurl, protocole) 
VALUES ('2','RATP','100WSIVSIRI','DIFFUSEUR', '2.4', true,true,'http://192.54.144.229:8080/bouchon-producteur',true,'http://10.222.9.205:8080/RATP-test249',true ,10,1,'RPC',true,false,'http://10.222.9.205:8080/Ano209', 'HTTP');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, protocole) 
VALUES ('3','KEOLIS','KEOLIS','DIFFUSEUR', '2.4', true,true,'http://10.222.9.205:8080/bouchon-producteur',true,'http://10.222.9.205:8080/KEOLIS-test245',10,1,'RPC',true,false, 'HTTP');


INSERT INTO operator (id, name, participant_id, active, code) VALUES ('1','RATP','2',true ,'RATP');
INSERT INTO operator (id, name, participant_id, active, code) VALUES ('2','SNCF','1',true ,'SNCF');


INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'100WSIVSIRI');
INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'KEOLIS');
INSERT INTO checkstatus_response (id, status, responder_Ref)  
VALUES (nextval('SEQ_checkstatus_response'),true,'SNCF-ACCES');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:109801:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:109801:',false,2);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108941:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108941:',false,2);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108941',3);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108941',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'109801',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108941',1);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'109801',1);

DELETE FROM synchro_referentiels;
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('PARTICIPANT',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('COMMUNICATION',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('OPERATOR_CODE',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('ARRET_DIFFUSEUR',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('ARRET_PRODUCTEUR',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('SUBSCRIPTION',now());
INSERT INTO synchro_referentiels(
            id,dernieremaj)
    VALUES ('REFLEX',now());


-- Suppression des jobs existants
delete from job_detail;


-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','GS','{"address":"http://${ip_producer}:${port_producer}/test99Producer_SNCF","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:108941:"]}', 'SNCF-ACCES');
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/15 * * * * ?','GS','{"address":"http://${ip_producer}:${port_producer}/test99Producer_RATP","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:109801:","STIF:StopPoint:Q:108941:","STIF:StopPoint:Q:109943:","STIF:StopPoint:Q:110048:"]}', 'SNCF-ACCES');

COMMIT;
