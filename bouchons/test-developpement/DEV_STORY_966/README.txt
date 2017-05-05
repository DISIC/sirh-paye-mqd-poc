
STIFSCRUM-966 

En tant que Relais, je filtre les passages SM des producteurs KO afin de respecter l'intégrité de l'information envoyée aux diffuseurs en mode requête.


- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Modifier au niveau de la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "diffuseurstopmonitoringurl" 
égale à la valeur "http://localhost:8081/SNCF-test166" et la valeur du champ "diffuseurstopmonitoringused" à TRUE.

- 3) Cocher tous les champs au niveau de l'IHM Communication qui sont "Communication en réception" puis valider et "Communication en emission" puis valider

- 4) Lancer le fichier "soapui/Story-966-Subscribe-soapui-rpc-project.xml" au niveau de SOAP afin de créer les abonnements Stop Monitoring.

- 5) Vérifier au niveau de la table "subscription_diffuseur_sm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur 
à la date du jour sinon il faut la modifiée.

- 6) Vérifier que le champ "address" est bien renseigné.

- 7) Au niveau de l'IHM sélectionnez l'onglet "Gestion des Comptes" puis l'onglet "Partenaires" et saisissez "SNCF" au niveau du champ "Choisir un partenaire".
Dés que le formulaire s'affiche des "Paramètres" vérifiez que les champs suivants sont bien renseignés :
	1- le champ "Partenaire activé" est coché;
	2- Sélectionner "Producteur/Diffuseur" pour le champ "Type de partenaire";
	3- Sélectionner "Version 2.4" pour le champ "Version SIRI";
	4- Sélectionner "RPC Literal" pour le champ "Style de WSDL";
	5- Sélectionner "Requête" pour le champ "Mode(s) d'acquisition";
	6- Saisir la valeur 1 pour le champ "Nombre de requêtes par minute";
	7- Saisir la valeur 10 pour le "Nombre d'arrêts par requête";
	8- Saiisr la valeur "Pas de requête en attente" pour le champ "Durée d'un cycle d'interrogation"
	9- Partie "Services Diffuseurs" :
		1- Cocher "StopMonitoring" et renseigner la valeur "http://localhost:8081/SNCF-test166";
		2- Cocher "GeneralMessage" et renseigner la valeur "http://localhost:8081/SNCF-test166";
	10- Partie "Services Producteurs" :
		1- Cocher "CheckStatus" et renseigner la valeur "http://localhost:8088/checkstatus";
		2- Cocher "GeneralMessage" et renseigner la valeur "http://localhost:8088/GeneralMessage";
		3- Cocher "StopMonitoring" et renseigner la valeur "http://localhost:8088/GMProducer";
	11- Cliquer sur le bouton "Valider".

- 8) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "SUBSCRIPTION|STIF:StopPoint:Q:108926:" existe dans le tampon.

- 9) Importer le projet "DEV_STORY_966/soapui/TEST-966-CheckStatus-soapui-project.xml" au niveau de SoapUI.

- 10) Lancer le "SiriProducerRpcBinding MockService" du "CheckStatus" en mettant la valeur de "Status" à "true" puis en testant avec la valeur "false"

- 11) Importer le projet "DEV_STORY_966/soapui/Test-966-GetStopMonitoring-soapui-project.xml" au niveau de SoapUI.

- 12) Lancer le "GMProducer" du "GetStopMonitoring"

- 13) Importer le projet "DEV_STORY_966/soapui/TEST-966-notif-soapui-project.xml" au niveau de SoapUI.
	
- 14) Lancer "SNCF-test166" qui se trouve sous le projet "TEST_IVTR_UC_11_notif" mais en séléctionnant "NotifyStopMonitoring" correspondant au champ "Operation"

- 15) Lancer "Notification-test127" qui se trouve dans l'arborescence "NotifyStopMonitoring" en incrémentant la valeur de "RecordedAtTime"

- 16) Vérififier au niveau "SNCF-test166" que la notification est reçu.

- 17) Double cliquer sur la valeur "NotifyStopMonitoring" une PopUp s'ouvre puis double cliquer sur "Response 1" une autre PopUp s'ouvre.

- 18) Sur le volet gauche il faut faire "Alt F" pour indenté le XML.

- 19) Si la valeur de "Status" est à "true" en reçoit les bonnes valeur. Par contre, si la valeur de "Status" est à "false" alors on a une message contenant
"OtherError" et la description du message est : Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible.