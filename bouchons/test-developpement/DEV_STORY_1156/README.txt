STIFSCRUM-1156

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
- 2) Lancer l'IHM administration
- 3) Aller dans Configuration de la plateforme 
- 4) Modifier le cron avec la valeur : 0 0/2 * * * ?
- 5) Mettre à 'null' la valeur des rapports 1,2,34,5,6,7,8,9,10 
- 6) Modifier le cron avec la valeur : 0 0/1 * * * ?
- 7) Spécifier test2@test.com;test@test.com pour la valeur du rapport 2
- 8) Spécifier test3@test.com pour la valeur du rapport 3
- 9) Vérifier sur le composant reporting la création des rapports 2 et 3
- 10) Vérifier la mise à jour du rapport à la minute suivante puis toute les minutes (exemple première modif 12:39:30, puis 12:40:00, puis 12:41:00 etc...) 