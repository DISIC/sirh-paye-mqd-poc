
STIFSCRUM-1611

En tant que RELAIS, je désabonne les diffuseurs dont les envois ont échoués au bout de N tentatives 
(contient l'écriture d'une LOG spécifique pour l'envoie d'un email au diffuseur concerné).


- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	-> 1) codifligne.sql (sous le répertoire codifligne)
	-> 2) partenaires.sql (sous le répertoire partenaires)
	-> 3) 01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	-> 4) 02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	-> 5) 03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	-> 6) 04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	-> 7) 05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Au niveau de l'IHM le portail de l'application STIF aller dans l'onglet "Gestion des Comptes"
	-> 1) Créer le transporteur ACME-PROD dont le code est OpRef_ACME (Attention il faut d'abord augmenter la valeur de la sequance seq_operator)
	-> 2) Créer le partenaire ACME-PROD dont le type est Producteur/Diffuseur en mode requête pour la version 2.4 et RPC Literal (Attention il faut d'abord augmenter la valeur de la sequance seq_participant)
	-> 3) Renseigner la partie Service Producteur pour le stopMonitoring, CheckStatus et GeneralMessage la valeur de URL est http://localhost:8081/acme
	-> 4) Au niveau de l'association Transporteur ajouter le transporteur ACME-PROD
	-> 5) Au niveau du partenaire SNCF renseigner la partie Services Diffuseurs pour le StopMonitoring dont la valeur est http://localhost:8082/SNCF-test166
	-> 6) Cocher tous les champs au niveau de l'onglet Communication pour les blocs "Communication en réception" puis valider et "Communication en emission" puis valider
	
- 3) Créer un abonnement avec le partenaire SNCF 
	-> 1) Important le projet "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/TEST-IVTR-UC-51-abo-soapui-project.xml" 
	-> 2) lancer le script "test142-Subscribe Request"
	-> 3) Vérifier au niveau de la table "subscription_diffuseur_sm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur à la date du jour sinon il faut la modifiée.
	
- 4) Ajouter dans la base de données PostgreSQL en lançant le script "job_detail.sql"

- 5) Ajouter dans la base de données PostgreSQL en lançant le script "reflex_codifligne.sql" 
	-> 1) Augmenter la valeur de la sequance seq_reflex_zone_d_embarquement 
	-> 2) Augmenter la valeur de la sequance seq_codifligne
	
- 6) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "SUBSCRIPTION_SM|STIF:StopPoint:Q:108926:" existe dans le tampon.
	
- 7) Importer les fichier "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/Relais-IVTR-SIRI2-0k-IDF2-4-soapui-project.xml"  
	-> 1) Renseigner au niveau du mock "Prod_Srv_Req_ACME" le port: 8081, le host: localhost et la path: /acme 
	-> 2) Lancer le mock "Prod_Srv_Req_ACME"

- 8) Importer le projet "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/TEST-516-abo-soapui-project.xml"  
	-> 1) Renseigner au niveau du mock "SNCF-test166" le port: 8082, le host: localhost et le path: /SNCF-test166
	-> 2) Lancer le mock "SNCF-test166"

- 9) Attendre 1 à 2 minutes et les requêtes CheckStatus, GetGeneralMessage et GetStopMonitoring apparaissent dans "Prod_Srv_Req_ACME"

- 10) La notification ne doit pas apparaitre dans "SNCF-test166" car il faut que le mock soit éteint.

- 11) Vérifier au nivea de la table "subscription_diffuseur_sm" que l'enregistrement a été supprimer après près 2 minutes 

