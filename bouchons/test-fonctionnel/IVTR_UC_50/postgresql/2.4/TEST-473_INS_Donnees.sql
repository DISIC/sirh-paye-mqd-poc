BEGIN;

UPDATE checkstatus_response SET status = false WHERE responder_ref = 'SNCF-ACCES';

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';

COMMIT;
