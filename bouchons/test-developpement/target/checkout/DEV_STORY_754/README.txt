STIFSCRUM 754 : Synchronisation des abonnements General Message (diffuseur) avec le tampon

Etapes :
	PS : Vérifier que les communications sont autorisées ! (via IHM ou table communication)
	1 - Alimenter le tampon avec des associations (voir plus bas : utiliser le projet bouchon-producteur)
	2 - Faire une demande d'abonnement General Message (diffuseur) -> voir STIFSCRUM-749
	3 - Mettre à jour le champ SUBSCRIPTION_DIFFUSEUR dans la table synchro_referentiels
	4 - Attendre un peu le temps de synchro puis verifier dans le tampon la présence des clés "SUBSCRIPTION_GM|{idTechnique}"
	

Info étape 1 :
	
	Pour alimenter le tampon, il faut utiliser le bouchon producteur ou SOAPUI afin de simuler des reponses StopMonitoring de la part d'un producteur.
	Ces réponses permettent de créer des associations entre les lineRef et StopPointRef par exemple.
	Dans notre cas, on va prendre pour exemple un abonnement GM avec comme filtre un lineRef "STIF:Line:Q:C01727:"
	
	1 - Mettre à jour le fichier src/main/ressources/config.properties du projet bouchon-producteur en ajoutant le lineRef voulu
		ex : stopMonitoringDelivery.lineRef=STIF:Line:Q:C01727:
	2 - Démarrer le serveur bouchon-producteur
	3 - Créer un job afin de requeter le producteur en SM
	
	Dans le tampon se trouvera une association avec les données contenu dans stopMonitoringDelivery.stopPointRef
	
 