STIFSCRUM-973

En tant que Relais après mon démarrage complet, j'envoie aux producteurs les requêtes en fonction de ma table des arrêts souhaités afin de récupérer l'information voyageurs.

Contexte : Arrêt du tampon
Objectif : Réactualisation des informations récupéré en mode requête

Phase BDD
1-Lancer les scripts referentiel Reflex sur pgadmin
2-Lancer les script referentiel Codifligne sur pgadmin
3-Lancer le script SQL du Test sur pgadmin
4-Lancer Flushdb sur redis

Phase service :
1- Lancer le projet soapui
2- Ouvrir >Test973
3- Lancer le mock en cliquant sur la flèche verte
4- le mock doit recevoir 13 demandes SM après insertion SQL.
5- le mock ne recevrat pas d'autre demande avant 15h
6- le mock doit recevoir 13 demandes SM après un flushdb
