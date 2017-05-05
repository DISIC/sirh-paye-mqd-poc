BEGIN;

UPDATE participant SET status = false WHERE name = 'SNCF';

UPDATE synchro_referentiels SET dernieremaj = NOW() AT TIME ZONE 'GMT' WHERE id = 'PARTICIPANT';

COMMIT;
