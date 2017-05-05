BEGIN;


INSERT INTO ligne_producteur (id, lineref, participant_id) 
VALUES (nextval('SEQ_ligne_producteur'),'STIF:Line::C00040:',1);

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'LIGNE_PRODUCTEUR';


COMMIT;
