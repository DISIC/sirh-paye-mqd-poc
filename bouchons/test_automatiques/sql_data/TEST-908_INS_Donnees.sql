BEGIN;

INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '4644', 2);

UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:4644:';

COMMIT;
