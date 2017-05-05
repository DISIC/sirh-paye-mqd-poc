delete from participant;
delete from job_detail;
delete from arret_souhaite;

INSERT INTO participant (id, name, participantref, siriversion, address, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, partenaireactif) 
VALUES (nextval('SEQ_partenaires'),'SNCF','SNCF','2.4','http://localhost:8080/producteurSNCF',10,1,'RPC',false,true,true);
INSERT INTO participant (id, name, participantref, siriversion, address, nombreArretParRequete, nombreRequeteParMinute, style, activationmodeabonnement, activationmoderequete, partenaireactif)
VALUES (nextval('SEQ_partenaires'),'RATP','RATP','2.4','http://localhost:8080/producteurRATP',10,1,'RPC',false,true,true);

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/10 * * * * ?','SNCF','SM','{"address":"http://localhost:8888/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:108925:"}');
INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('SEQ_job_detail'),'5/10 * * * * ?','SNCF','SM','{"address":"http://localhost:8080/producteurRATP","monitoringRef":"STIF:StopPoint:Q:108925:"}');
