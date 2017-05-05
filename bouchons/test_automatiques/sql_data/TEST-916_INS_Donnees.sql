BEGIN;

-- acme
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '4644', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29066', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '12817', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '19785', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5604', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5603', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5362', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5197', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5601', 2);
-- stivo
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29692', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29685', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29687', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '5315', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29562', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29569', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29599', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '29583', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '8816', 2);
-- ratp
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40927', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40930', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411321', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '412802', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411352', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411368', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40933', 2);
-- sncf
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '412815', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '41071', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411410', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411412', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40968', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40978', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '40980', 2);
INSERT INTO arret_diffuseur (id, monitoringref, participant_id) VALUES (nextval('SEQ_arret_diffuseur'), '411411', 2);


-- acme
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:4644:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29066:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:12817:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:19785:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5604:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5603:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5362:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5197:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5601:';
--stivo
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29692:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29685:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29687:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:5315:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29562:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29569:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29599:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:29583:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:8816:';

-- ratp
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40927:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40930:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:411321:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:412802:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:411368:';
UPDATE arret_producteur SET souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40933:';
-- sncf
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:412815:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:41071:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:411410:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:411412:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40968:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40978:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:40980:';
UPDATE arret_producteur SET datedernieredemande=null, granularite= null, initialmonitoringref=null, souhaite='FALSE' where monitoringref like 'STIF:StopPoint:Q:411411:';


COMMIT;
