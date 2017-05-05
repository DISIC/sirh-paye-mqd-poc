
STIFSCRUM-516 

En tant que RELAIS, je filtre les messages GM des producteurs KO afin de respecter l'intégrité de l'information GM envoyée aux diffuseurs en mode requête

- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Modifier au niveau de la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "producteurcheckstatusurl" 
égale à la valeur "http://localhost:8081/checkstatus" et la valeur du champ "producteurcheckstatusused" à TRUE.

- 3) Modifier au niveau de la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "diffuseurgeneralmessageurl" 
égale à la valeur "http://localhost:8081/SNCF-test166" et la valeur du champ "diffuseurgeneralmessageused" à TRUE.

- 3) Importer le projet au niveau du SoapUi "DEV_STORY_516/soapui/TEST-516-soapui-project.xml" 

- 4) Modifier la valeur de statut afin de tester avec une valeur "true" puis avec une valeur "false" <siri:Status>true</siri:Status> dans la "Response 1"

- 5) Lancer "SiriProducerRpcBinding MockService" pour le CheckStatus.

- 6) Rafraichir au niveau de l'IHM le Partenaire SNCF

- 7) Attendre quelques minutes et vérifier au niveau du tampon que le status de participant a pour valeur de CheckStatus
	Exemple : 127.0.0.1:6379> get PARTICIPANT|SNCF-ACCES
				"{\"name\":\"SNCF\",\"participantRef\":\"SNCF-ACCES\",\"siriVersion\":\"2.4\",\"status\":false}"

- 8) Importer le projet au niveau du SoapUi "DEV_STORY_516/soapui/TEST-516-abo-soapui-project.xml" 

- 9) Lancer "Subscribe"

- 10) Vérifier au niveau du tampon que l'abonnement existe : "SUBSCRIPTION_GM|108925"

- 11) Importer le projet au niveau du SoapUi "DEV_STORY_516/soapui/TEST-516-notif-soapui-project.xml" 

- 12) Lancer "NotifyGeneralMessage"

- 13) Vérifier au niveau du tampon que la notification existe : "ASSOCIATIONAMSG|STIF:StopPoint:Q:108925:"

- 14) Importer le projet au niveau du SoapUi "DEV_STORY_516/soapui/TEST-516-siri-wsProducer-soapui-project.xml" 

- 15) Lancer "GetGeneralMessage"

- 16) Vérifier la réponse par rapport au statut si le statut est à true alors l'objet GeneralMessageDelivery contient "GeneralMessage" 
	sinon si le statut est à false l'objet GeneralMessageDelivery contient "ErrorCondition" avec une description de type Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible
