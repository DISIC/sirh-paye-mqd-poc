    STIFSCRUM-1612

En tant que RELAIS, je veux supprimer les alertes résolues de la BDD et dont la date est trop ancienne.
1/remplir la base table Alerte_logstash avec une alerte avec actif true et une alerte avec actif  false les deux ayant une requestTimestamp a 1jour dans le passé et l autre a 1 an dans le passé(now-1jour,now-1an)
2/rafraichir la table
3/il ne doit plus y avoir la ligne 1 an dans le passé avec actif = false et
la ligne avec actif =true et 1 jours avant doit etre a actif =false