
STIFSCRUM-768

En tant que RELAIS, je filtre les informations voyageurs produites par le système qui a émis la requête.

Contexte :
Un Diffuseur émet une requête StopMonitoring auprès du RELAIS pour récupérer les informations des passages pour des arrêts donnés.

Objectif :
Lorsque les passages issues des critères de recherche de la requête sont remontés, on retire ceux associés au même partenaire (voir Requête Diffuseur - Recherche StopMonitoring Tampon.bmp).
Cette information est présente dans le champ partenaireRef du format pivot des passages (Passage). 
        
        
- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
			
- 2) Modifier au niveau de la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "siriversion" 
égale à la valeur "2.2".

- 3) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "PARTICIPANT|SNCF-ACCES" existe dans le tampon.
	
- 4) Lancer le fichier "DEV_STORY_768/soapui/TEST-IVTR-UC-11-notif-soapui-project.xml" au niveau de SOAP pour créer une notification StopMonitoring.

- 5) Lancer le fichier "DEV_STORY_768/soapui/siri-wsProducer-soapui-project-2.2.xml" au niveau de SOAP pour emettre une requête StopMonitoring auprès du RELAIS.

- 6) Vérifier que le réponse est correcte si tous les champs obligatoires sont renseignés sinon on resoit une réponse avec une "Erreur Condition"


- 7) Remodifier la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "siriversion" 
égale à la valeur "2.4".

- 8) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "PARTICIPANT|SNCF-ACCES" existe dans le tampon.

- 9) Lancer le fichier "DEV_STORY_768/soapui/TEST-IVTR-UC-11-notif-soapui-project.xml" au niveau de SOAP pour créer une notification StopMonitoring.

- 10) Lancer le fichier "DEV_STORY_768/soapui/siri-wsProducer-soapui-project-2.4.xml" au niveau de SOAP pour emettre une requête StopMonitoring auprès du RELAIS.

- 11) Vérifier que le réponse est correcte si tous les champs obligatoires sont renseignés sinon on resoit une réponse avec une "Erreur Condition"
