BEGIN;


INSERT INTO participant (id, name, participantref, typepartenaire, siriversion, producteurcheckstatusused, producteurcheckstatusurl, partenaireActif, producteurstopmonitoringused, producteurstopmonitoringurl, diffuseurstopmonitoringused, diffuseurstopmonitoringurl, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, plagedebut, plagefin) 
VALUES (nextval('SEQ_participant'),'THALES-Test','THALES-REF', 'PRODUCTEUR_DIFFUSEUR', '2.4', true, 'http://10.222.9.4:8080/SNCF-test116', true, true,'http://10.222.9.4:8080/SNCF-test249',true,'http://10.222.9.4:8080/bouchon-diffuseur',10,1,'RPC',false,true, '10:00', '15:00');

INSERT INTO operator (id, name, participant_id, active, code) VALUES (nextval('SEQ_operator'),'THALES',current_value('SEQ_participant'),true ,'Op-THALES');

INSERT INTO checkstatus_response (id, status, responder_Ref) 
VALUES (nextval('SEQ_checkstatus_response'),true,'THALES-REF');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,current_value('SEQ_participant'));
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108940:',false,current_value('SEQ_participant'));

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108925',current_value('SEQ_participant'));
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108926',current_value('SEQ_participant'));
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'108940',current_value('SEQ_participant'));


UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';
UPDATE synchro_referentiels SET dernieremaj = now() at time zone 'GMT' WHERE id= 'OPERATOR_CODE';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_DIFFUSEUR';
UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'ARRET_PRODUCTEUR';


