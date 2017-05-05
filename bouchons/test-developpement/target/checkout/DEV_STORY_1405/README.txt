
STIFSCRUM-1405

En tant que RELAIS, je collecte les informations me permettant de générer les alertes en fonction de critères de recherche

        
        
- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)

- 2) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	
- 3) Copier le fichier logstash-logback.conf dans l'arborescence da l'installation de Logstach dans le répertoire "conf/" qui se trouve sous "/opt/logstash/1.3.2/conf/".

- 5) Aller dans le répertoire suivant "/opt/logstash/1.3.2/"

- 6) Lancer la commande suivant :
	1- $ java -jar logstash-1.3.2-flatjar.jar agent -f conf/logstash-logback.conf 

- 7) Attendre quelques minutes puis vérifier au niveau redis si la clé existe en effectuant la commande suivante :
	1- keys logstash
	2- lindex logstash 0
	3- llen logstash

- 8) Attendre quelques minutes puis vérifier au niveau de la base PostgreSQL si les mêmes enregistrements existes aussi 
(que la synchronisation est faite entre redis et la base PostgreSQL)

