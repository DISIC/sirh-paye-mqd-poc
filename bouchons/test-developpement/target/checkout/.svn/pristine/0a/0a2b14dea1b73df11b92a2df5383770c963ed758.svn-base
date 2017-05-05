DELETE FROM job_detail;
DELETE FROM subscription_diffuseur_sm;
DELETE FROM subscription_producteur;
DELETE FROM operator;
DELETE FROM arret_diffuseur;
DELETE FROM arret_producteur;
DELETE FROM participant;

-- DATA TEST DE PERF


INSERT INTO participant 
  (
	  id,
	  name,
	  nombrearretparrequete, 
	  nombrerequeteparminute,
	  participantref,
	  typepartenaire, 
	  cycleinterrogation, 
	  siriversion,
	  style,
	  activationmodeabonnement, 
	  activationmoderequete,
	  partenaireactif, 
	  activationdiscovery,
	  diffuseurstopmonitoringused,
	  diffuseurstopmonitoringurl,
	  diffuseurcheckstatusused,
	  diffuseurcheckstatusurl,
	  diffuseurestimatetimetableused,
	  diffuseurestimatetimetableurl,
	  diffuseurgeneralmessageused ,
	  diffuseurgeneralmessageurl,
	  diffuseurstoppointdiscoveryused,
	  diffuseurstoppointdiscoveryurl ,
	  diffuseurlinesdiscoveryused,
	  diffuseurlinesdiscoveryurl,
	  producteurstopmonitoringused,
	  producteurstopmonitoringurl ,
	  producteurcheckstatusused ,
	  producteurcheckstatusurl,
	  producteurestimatetimetableused, 
	  producteurestimatetimetableurl,
	  producteurgeneralmessageused,
	  producteurgeneralmessageurl,
	  producteurstoppointdiscoveryused,
	  producteurstoppointdiscoveryurl,
	  producteurlinesdiscoveryused,
	  producteurlinesdiscoveryurl
  )
  VALUES (
  
  1,'SNCF','0','0','SNCF','PRODUCTEUR_DIFFUSEUR','Pas de requête en attente.','2.4','RPC',TRUE,FALSE,TRUE,FALSE,TRUE,'http://10.222.37.56:8080/bouchon-diffuseur/GeneralMessageSOAPPortImpl',FALSE,'',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,''''
  
  );
  
  
  
  
  
  INSERT INTO participant 
  (
	  id,
	  name,
	  nombrearretparrequete, 
	  nombrerequeteparminute,
	  participantref,
	  typepartenaire, 
	  cycleinterrogation, 
	  siriversion,
	  style,
	  activationmodeabonnement, 
	  activationmoderequete,
	  partenaireactif, 
	  activationdiscovery,
	  diffuseurstopmonitoringused,
	  diffuseurstopmonitoringurl,
	  diffuseurcheckstatusused,
	  diffuseurcheckstatusurl,
	  diffuseurestimatetimetableused,
	  diffuseurestimatetimetableurl,
	  diffuseurgeneralmessageused ,
	  diffuseurgeneralmessageurl,
	  diffuseurstoppointdiscoveryused,
	  diffuseurstoppointdiscoveryurl ,
	  diffuseurlinesdiscoveryused,
	  diffuseurlinesdiscoveryurl,
	  producteurstopmonitoringused,
	  producteurstopmonitoringurl ,
	  producteurcheckstatusused ,
	  producteurcheckstatusurl,
	  producteurestimatetimetableused, 
	  producteurestimatetimetableurl,
	  producteurgeneralmessageused,
	  producteurgeneralmessageurl,
	  producteurstoppointdiscoveryused,
	  producteurstoppointdiscoveryurl,
	  producteurlinesdiscoveryused,
	  producteurlinesdiscoveryurl
  )
  VALUES (
  
 	2,'RATP','1','1','RATP','DIFFUSEUR','Pas de requête en attente.','2.4','RPC',FALSE,TRUE,TRUE,FALSE,FALSE,'''',FALSE,'',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,'''',FALSE,''''
  
  );
  
  
INSERT INTO checkstatus_response (id, status, responder_ref, error_condition)  VALUES (1, 'true', 'SNCF', '{"serviceNotAvailableError":{"errorText":"Could not send Message.","number":null,"expectedRestartTime":null},"otherError":null,"description":{"value":"Could not send Message."}}');


-- Abonnement diffuseur pour recevoir les notifications




INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (1,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109311:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (2,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:108926:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (3,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:108925:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (4,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109310:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (5,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:108918:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (6,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109801:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (7,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109802:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (8,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109805:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (9,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109806:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);

INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (10,'http://localhost:8888/bouchon-diffuseur/GeneralMessageSOAPPortImpl','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109988:','','2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',1);



INSERT INTO operator (ID,CODE,NAME,PARTICIPANT_ID,ACTIVE) 
VALUES (1,'KEOLIS','KEOLIS',1,TRUE);


-- arret diffuseur essentiel pour que le mode requete diffuseur fonctionne
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (1,'STIF:StopPoint:Q:109311:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (2,'STIF:StopPoint:Q:108926:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (3,'STIF:StopPoint:Q:108925:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (4,'STIF:StopPoint:Q:109310:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (5,'STIF:StopPoint:Q:108918:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (6,'STIF:StopPoint:Q:109801:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (7,'STIF:StopPoint:Q:109802:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (8,'STIF:StopPoint:Q:109805:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (9,'STIF:StopPoint:Q:109806:',2);
INSERT INTO arret_diffuseur (ID,MONITORINGREF,PARTICIPANT_ID) VALUES (10,'STIF:StopPoint:Q:109988:',2);

INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (1,'STIF:StopPoint:Q:109311:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (2,'STIF:StopPoint:Q:108926:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (3,'STIF:StopPoint:Q:108925:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (4,'STIF:StopPoint:Q:109310:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (5,'STIF:StopPoint:Q:108918:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (6,'STIF:StopPoint:Q:109801:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (7,'STIF:StopPoint:Q:109802:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (8,'STIF:StopPoint:Q:109805:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (9,'STIF:StopPoint:Q:109806:',TRUE,1);
INSERT INTO arret_producteur(ID,MONITORINGREF,SOUHAITE,PARTICIPANT_ID) VALUES (10,'STIF:StopPoint:Q:109988:',TRUE,1);



-- abonnement producteur essentiel pour que les notification producteur soient prise en compte
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109311:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:108926:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:108925:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109310:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:108918:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109801:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109802:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109805:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109806:',TRUE,1,'SM');
INSERT INTO subscription_producteur (id,datedebut,incrementalupdates,initialterminationtime,siriref,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),FALSE,'2018-11-03 11:53:33.878632','STIF:StopPoint:Q:109988:',TRUE,1,'SM');


INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');
INSERT INTO subscription_producteur (id,datedebut,changebeforeupdates,incrementalupdates,initialterminationtime,statut,participant_id,requesttype) VALUES (nextval('SEQ_subscription_producteur'),now(),'PT1M',FALSE,'2018-11-03 11:53:33.878632',TRUE,1,'GM');






-- JOBS SM

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:108925:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:21100:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:30:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:109806:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:2847:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:23777:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:109802:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:8349:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:31656:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:108918:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:31656:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:941:","participantRef":"SNCF","siriVersion":"2.4"}');

INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","monitoringRef":"STIF:StopPoint:Q:1147:","participantRef":"SNCF","siriVersion":"2.4"}');





-- JOBS GM


INSERT INTO job_detail (ID,CRON,PARTICIPANTREF,SIRIREQUESTTYPE,REQUETE) 
VALUES (nextval('seq_job_detail'),'0/1 * * * * ?','SNCF','SM','{"address":"http://10.222.37.56:8080/bouchon-producteur/services/siri","participantRef":"SNCF","siriVersion":"2.4"}');


