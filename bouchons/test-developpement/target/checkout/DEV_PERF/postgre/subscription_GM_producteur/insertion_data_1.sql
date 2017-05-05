-- DROP TABLE
BEGIN;
DELETE FROM subscription_producteur;
COMMIT;

BEGIN;

INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','',TRUE,1,'GM');


COMMIT;
