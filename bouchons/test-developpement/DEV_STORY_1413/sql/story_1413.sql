Delete from subscription_diffuseur_sm;
Delete from subscription_diffuseur_gm;
INSERT INTO subscription_diffuseur_sm (id,address,changebeforeupdates,datedernieredemande,incrementalupdates,initialsubscribedmonitoringref,initialterminationtime,monitoringref,previewinterval,starttime,messageidentifier,subscriptionref,
subscriptionfilteridentifier,participant_id)
VALUES (1,'http://localhost:8080/Test-1413','',now(),TRUE,'STIF:StopPoint:Q:108926:','2034-11-06 17:58:24.658504','STIF:StopPoint:Q:109311:','',
'2014-11-06 17:58:24.658504','gjddnb','rthjkur','kut',2);


INSERT INTO subscription_diffuseur_gm (id,address,messageidentifier,subscriptionref,subscriptionfilteridentifier,participant_id)
VALUES (1,'http://localhost:8080/Test-1413','gjddnb','rthjkur','kut',2);
