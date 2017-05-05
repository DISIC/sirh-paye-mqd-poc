
STIFSCRUM-1413

En tant que RELAIS, à la modification des paramètres d'un diffuseur via l'IHM d'administration, j'envoie la notification de suppression d'abonnement au diffuseur modifié
       
        
- 1) Lancer le script d'initialisation SQL  présent dans le répertoire : /test-fonctionnel/referentiels

- 2) Executer récupéré le projet soapui fournis et executer siriProducerRPCBinding>subscribe>TEST-1413

- 3) Lancer le mock Test-1413

- 4) Aller dans l'IHM : Gestion des comptes -> Partenaires -> RATP & Sauvegarder.

- 5) Vérifier qu'une notif de fin d'abonnement apparait dans le mock Test-1413
	 Le subscriptionRef doit être identique a celui envoyé par la requête d'abonnement siriProducerRPCBinding>subscribe>TEST-1413

- 6) Executer récupéré le projet soapui fournis et executer siriProducerRPCBinding>subscribe>TEST-1413
	 
- 7) Aller dans l'IHM : Gestion des comptes -> Partenaires -> RATP remplacer l'addresse stopMonitoring par http://[adresse du mock]/Test-1413/ & Supprimer.

- 8) Vérifier qu'une notif de fin d'abonnement apparait dans le mock Test-1413
	 Le subscriptionRef doit être identique a celui envoyé par la requête d'abonnement siriProducerRPCBinding>subscribe>TEST-1413