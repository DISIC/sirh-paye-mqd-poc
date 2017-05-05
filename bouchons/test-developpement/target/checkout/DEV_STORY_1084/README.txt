
STIFSCRUM-1084

En tant que Relais, je synchronise l'état des producteurs dans le tampon

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

- 3) Lancer le fichier au nivrau SoapUi "SiriProducerRpcBinding MockService" pour le CheckStatus.

- 4) Attendre 1 minutes puis vérifier au niveau des tables "participant" que le status est à true et la table "checkstatus_response" que le status est à true
