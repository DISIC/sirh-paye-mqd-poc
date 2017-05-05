
STIFSCRUM-972

En tant que Relais, je traite la requête contenue dans une demande d'abonnement SM afin de communiquer les données présentes dans le tampon

Contexte : Arrêt du tampon
Objectif : Renvois des demandes d'abonnement.

Phase BDD
1-Lancer les scripts referentiel Reflex sur pgadmin
2-Lancer les script referentiel Codifligne sur pgadmin
3-Lancer le script SQL du Test sur pgadmin

Phase service :
1- Lancer le projet soapui
2- Ouvrir SiriProducerRpcBinding->Subscribe->Test972
3- Lancer la requête en cliquant sur la flèche verte



Phase REDIS
1-Lancer Flushdb sur redis

Phase service :
1- Le mock doit recevoir 2 demandes d'abonnement
