-- Jeu de données du TEST-12
-- Vidage des tables


-- suppression des jobs existants
delete from job_detail;

-- insertion d'un job GS vers le Producer 
INSERT INTO job_detail (ID,CRON,SIRIREQUESTTYPE,REQUETE, PARTICIPANTREF) 
VALUES (nextval('seq_job_detail'),'*/10 * * * * ?','GS','{"address":"http://${ip_producer}:${port_producer}/test12Producer_3","version":"2.4","listIdentifiantArrets":["STIF:StopPoint:Q:108940:","STIF:StopPoint:Q:108941:"]}', 'SNCF-ACCES');

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),null,'STIF:StopPoint:Q:108941:',false,1);
INSERT INTO arret_diffuseur(id, monitoringref, participant_id)   
 VALUES (nextval('seq_arret_diffuseur'), '108941',2);

