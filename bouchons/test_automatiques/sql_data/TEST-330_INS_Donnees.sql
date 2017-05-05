
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108925:',false,1);
INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108926:',false,2);

INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108925:',2);
INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:108926:',1);

UPDATE participant
SET producteurstopmonitoringurl = 'http://${ip_producer}:${port_producer}/test330Producer'
WHERE participantref='SNCF-ACCES';

UPDATE participant
SET siriversion = '2.2'
WHERE participantref='100WSIVSIRI';



INSERT INTO operator (id, name, participant_id, active, code) VALUES ('3','SNCF-ACCES','1',true, 'SNCF-ACCES');
