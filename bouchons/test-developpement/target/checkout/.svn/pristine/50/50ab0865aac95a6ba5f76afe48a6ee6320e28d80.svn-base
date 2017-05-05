
STIFSCRUM-1415

En tant que RELAIS, à la modification des paramètres d'un producteur via l'IHM d'administration, 
j'envoie la nouvelle demande d'abonnement en utilisant les nouveaux paramètres de ce producteur
       
        
- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)


- 2) Modifier au niveau de la table participant le champ "activationmodeabonnement" à la valeur "TRUE" et le champ "activationmoderequete" à la valeur "TRUE".

- 3) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	
- 4) Importer le fichier "DEV_STORY_1415/soapui/TEST-IVTR-UC-1415-soapui-project.xml" au niveau de SOAP.
	
- 5) Lancer le fichier "DEV_STORY_1415/soapui/TEST-IVTR-UC-1415-soapui-project.xml/SiriProducerRpcBinding/Subscribe" pour emettre une requête Subscribe de type StopMonitoring.

- 6) Vérifier dans la table subscription_diffuseu que l'enregistrement exite 

- 7) Vérifier dans la table subscription_producteur que l'enregistrement exite en vérifiant la valeur de la date "initialterminationtime" ainsi que la date "datedernierereponse"

- 8) Lancer le test "test1415-1Producer_SNCF" du fichier "DEV_STORY_1415/soapui/TEST-IVTR-UC-1415-soapui-project.xml"

- 9) Au niveau de IHM Gestion des Comptes -> Partenaires 

- 10) Saisir la valeur de SNCF puis sélectionner SNCF

- 11) Modifier un paramètre dans l'IHM par exemple la partie "Service Diffuseurs" en modifiant l'url de "StopMonitoring" par cette valeur : "http://localhost:8081/test1415-1Producer_SNCF".
afin de vérifier que la notification est déclencher auprès de diffuseur.

- 12) Vérifier dans la table subscription_producteur que la valeur de la date "initialterminationtime" ainsi que la date "datedernierereponse" ont été modifiés

