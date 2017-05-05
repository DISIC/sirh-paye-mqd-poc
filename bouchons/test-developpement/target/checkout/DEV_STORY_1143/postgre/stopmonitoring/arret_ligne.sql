-- la valeur de participant_id = 4 correspondant à nouvel enregistrement du participant ACME-PROD crée

INSERT INTO arret_producteur (id, dateDerniereDemande, monitoringRef, souhaite, participant_id) 
VALUES (nextval('SEQ_arret_producteur'),now(),'STIF:StopPoint:Q:60000001:',TRUE,4);


INSERT INTO arret_diffuseur (id, monitoringRef, participant_id) 
VALUES (nextval('SEQ_arret_diffuseur'),'STIF:StopPoint:Q:60000001:',1);



INSERT INTO ligne_producteur (id, lineref, participant_id) 
VALUES (nextval('seq_ligne_producteur'),'STIF:Line::C00369:',4);