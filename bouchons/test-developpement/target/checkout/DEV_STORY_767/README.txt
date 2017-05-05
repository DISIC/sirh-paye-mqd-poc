
STIFSCRUM-767

En tant que Relais, je filtre les passages sortants afin de n'envoyer que les passages pertinents.
1 - Un Diffuseur émet une requête StopMonitoring auprès du RELAIS pour récupérer les informations des passages pour des arrêts donnés.
2 - Un diffuseur émet une demande d'abonnement StopMonitoring auprès du RELAIS pour être notifié sur des arrêts données.

Objectifs :
1 - Si le filtre OperatorRef est renseigné et qu'il n'est pas associé au partenaire émettant la requête, on continue la recherche.
     Sinon suivre les consignes des SFD (voir Requête Diffuseur - Recherche StopMonitoring Tampon.bmp)

2 - Si le filtre OperatorRef est renseigné et qu'il est associé au partenaire émettant la requête, on annule l'abonnement pour l'arrêt souhaité ?
     Suivre les consignes des SFD pour savoir quoi répondre au Diffuseur.
     Sinon suivre aussi les consignes des SFD (voir Requête Diffuseur - Demande Abonnement StopMonitoring.bmp) 

3 - Dans le mode requête :
        Si le filtre le diffuseur requête ses propres informations, le Relais doit répondre un message au status "false" contenant une errorCondition du type "AccessNotAllowedError".
        La description de l'erreur doit être : "Le diffuseur RequestorRef n'est pas authorisé à requêter ses propres informations".
        
        
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

- 4) Lancer le fichier "soapui/siri-wsProducer-soapui-project-2.2.xml" au niveau de SOAP pour emettre une requête StopMonitoring auprès du RELAIS.

- 5) Vérifier que le réponse est correcte si tous les champs obligatoires sont renseignés sinon on resoit une réponse avec une "Erreur Condition"


- 6) Remodifier la table participant l'enregistrement correspondant à "SNCF-ACCES" en modifiant le champ "siriversion" 
égale à la valeur "2.4".

- 7) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "PARTICIPANT|SNCF-ACCES" existe dans le tampon.

- 8) Lancer le fichier "soapui/siri-wsProducer-soapui-project-2.4.xml" au niveau de SOAP pour emettre une requête StopMonitoring auprès du RELAIS.

- 9) Vérifier que le réponse est correcte si tous les champs obligatoires sont renseignés sinon on resoit une réponse avec une "Erreur Condition"
