BEGIN;

INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, plagedebut, plagefin) 
VALUES (nextval('SEQ_participant'),'THALES-Diff','THALES-DIFF', 'DIFFUSEUR', '2.4', true, 'http://10.222.9.4:8080/SNCF-test116', true, true,'http://10.222.9.4:8080/SNCF-test249',true,'http://[ip]:[port]/Diffuseur',10,1,'RPC',false,true, '10:00', '15:00');
INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, plagedebut, plagefin) 
VALUES (nextval('SEQ_participant'),'THALES-Test','THALES-PROD', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://[ip]:[port]/Producteur', true, true,'http://[ip]:[port]/Producteur',true,'http://[ip]:[port]/Producteur',10,1,'RPC',false,true, '10:00', '15:00');

INSERT INTO operator (id, name, participant_id, active, code) VALUES (nextval('SEQ_operator'),'THALES',currval('SEQ_participant'),true ,'Op-THALES');

INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'THALES-PROD');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,currval('SEQ_participant'));
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108940:',false,currval('SEQ_participant'));

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108925',(SELECT id FROM participant WHERE participantref = 'THALES-DIFF' ));
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108926',(SELECT id FROM participant WHERE participantref = 'THALES-DIFF' ));
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108940',(SELECT id FROM participant WHERE participantref = 'THALES-DIFF' ));
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108940',(SELECT id FROM participant WHERE participantref = 'THALES-PROD' ));


UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = now() at time zone 'GMT' WHERE id= 'OPERATOR_CODE';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_PRODUCTEUR';

COMMIT;
