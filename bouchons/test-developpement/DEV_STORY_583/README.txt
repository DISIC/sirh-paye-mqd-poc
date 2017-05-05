STIFSCRUM-583 - Acquisition des passages via le service GetMultipleStopMonitoring

Pour acquerir des informations de passage via le service GetMultipleStopMonitoring, il faut qu'un diffuseur fasse une requete SM sur un monitoringRef

Workflow :
1 - Un diffuseur fait une requete GetStopMonitoring ou GetMultpleStopMonitoring ou GetSiri sur un perimetre d'un producteur en version 2.2
2 - Le composant emission notifie le composant orchestration sur l'arret souhaité
3 - Un job GetMultipleStopMonitoring est crée vers le producteur ayant comme perimetre les arrets souhaité

Déroulement du test :

Pré-requis : 
			- Créer un producteur Diffuseur en 2.2
			- (Voir dossier DEV_STORY_583/soapui)Créer un mock producteur à l'aide de SOAPUI :
				- Nouveau projet SOAP avec le WSDL  workspace/stif/commons/siri/src/main/wsdl/wsdl2-2/xsd/siri_wsProducer.wsdl
				- Clique droit sur GetMultipleStopMonitoring -> Add to MockService
				- Modifier la reponse en remplacant les données "?" obligatoire avec les données voulu (Mettre autant que StopMonitoringDelivery que necessaire)
					- PS : StopMonitoringDelivery du GetMultipleStopMonitoring est identique à celui du GetStopMonitoring
				
			- Activer le service SM et renseigner l'url du mock producteur précédement crée
			- Setter dans la base le checkStatus du producteur à TRUE
			- Ajouter dans la table arret_producteur les monitoringRef (ZDE) et les associer au producteur. Mettre la variable arret_souhaite à FALSE
			- Ajouter dans la table arret_diffuseur les monitoringRef (ZDE) qui seront requêté par le diffuseur 



1 - Faire une requete GetMultipleStopMonitoring (STIFSCRUM-584) sur le composant emission
2 - Suite à la requete :
						- la table arret_producteur est mise à jour en passant à TRUE le champ SOUHAITE pour les monitoringRef du périmetre
						- un job de type MSM est créer dans la table jobDetail avec dans le JSON la liste des monitoringRef du périmetre
3 - Les données reçu suite au requete vers le producteur mock sont insérer dans le tampon exactement comme un GetStopMonitoring