
STIFSCRUM-765

En tant que RELAIS, je veux acquérir des informations GeneralMessage 2.2 en mode requête.

- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Modifier au niveau de la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "diffuseurgeneralmessageurl" 
égale à la valeur "http://localhost:8081/SNCF-test166" et la valeur du champ "diffuseurgeneralmessageused" à TRUE.

- 3) Cocher tous les champs au niveau de l'IHM Communication qui sont "Communication en réception" puis valider et "Communication en emission" puis valider

- 4) Lancer le fichier "soapui/abonnementGeneralMessage.xml" au niveau de SOAP afin de créer les abonnements Général Message.

- 5) Vérifier au niveau de la table "subscription_diffuseur_gm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur 
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
	8- Saiisr la valeur "Pas de rzquête en attente" pour le champ "Durée d'un cycle d'interrogation"
	9- Partie "Services Diffuseurs" :
		1- Cocher "StopMonitoring" et renseigner la valeur "http://localhost:8080/bouchon-diffuseur";
		2- Cocher "GeneralMessage" et renseigner la valeur "http://localhost:8081/SNCF-test166";
	10- Partie "Services Producteurs" :
		1- Cocher "CheckStatus" et renseigner la valeur "http://localhost:8088/mockSiriSOAPBinding";
		2- Cocher "GeneralMessage" et renseigner la valeur "http://localhost:8088/mockSiriSOAPBinding";
	11- Cliquer sur le bouton "Valider".

- 8) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "SUBSCRIPTION_GM|108925" existe dans le tampon.

- 9) Importer le projet "DEV_STORY_765/soapui/siri-wsProducer-soapui-project.xml" au niveau de SoapUI.

- 10) Lancer le "SiriSOAPBinding MockService" pour "CheckStatus" et "GetGeneralMessage"

- 11) Vérifier au niveau de Redis que l'association suivante existe : "ASSOCIATIONAMSG|STIF:StopPoint:Q:108925:"

- 12) Lancer le "GetGeneralMessage" qui se trouve sous "SiriSOAPBinding"

- 13) Vérifier que le réponse est correcte si tous les champs obligatoires sont renseignés sinon on resoit une réponse avec une "Erreur Condition"
