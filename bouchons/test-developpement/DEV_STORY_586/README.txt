Story : En tant que RELAIS, j'active ou désactive une typologie d'échange sur la plateforme RELAIS
Test : 
	- Aller sur l'IHM Communication puis authoriser ou non les communications
	- Verifier dans REDIS la présence des champs autoriser "COMMUNICATION|(emission ou reception)|stopMonitoring
		PS : - Dans REDIS se trouvera uniquement le nom des services authoriser via l'IHM
			 - Après chaque modification IHM, attendre 1min le temps que le CRON se lance
	
	- Executer les fichiers de test present dans le dossier soapui
	- Resultats attendu :
		- Mode requete : reponse indiquant l'erreur si le service est fermé
		- Mode abonnement (notify) : pas de reponse
	
	- Dans tous les modes, un log est crée afin d'indiquer le bloquage du service
	
 Rappel URL de test en local pour SoapUI:
 	- Notify Acquisition-Reception RPC : http://localhost:8090/reception/SiriRelais/SiriConsumerRpcPort
 	- Notify Acquisition-Reception DOC : http://localhost:8090/reception/SiriRelais/SiriConsumerDocPort
 	
Test du bloquage Acquisition-Recetpion en mode requete : 

	Pour tester le fonctionnement en mode requete, il faut 
	- Demarrer un mock de producteur
	- Ajouter des jobs dans la table job_details (SM et CS)
	- Verifier dans les logs du composant "acquisition" le blocage des requetes ou non en fonction de la config
	- Verifier les logs du mock producteur


Test du bloquage Emission en mode requete : 
	- Demarrer le composant emission
	- Executer via SoapUI les requetes
 	- Verifier le message de retour (erreur si le service est fermé)
 	 	