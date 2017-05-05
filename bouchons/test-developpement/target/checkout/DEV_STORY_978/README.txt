STIFSCRUM-978

En tant que Relais je veux récupérer auprès des producteurs les informations sur les arrêts via le service StopPointsDiscovery 2.4

Contexte : Lancement du job stoppointdiscovery sur un producteur
Objectif : MAJ/Ajout des relations arrêt/producteur

Phase BDD :
1- Lancer les scripts test-fonctionnel/referentiels/reflex (sauf arretpartenaire)
2- Lancer le script SQL fournis avec ce test.

Phase service :
1- Lancer le projet soapui
2- Ouvrir >Test978
3- Lancer le mock en cliquant sur la flèche verte
4- le mock doit recevoir régulièrement la requête stoppointdiscovery
5- Ouvrir >CheckStatus
6- Lancer le mock en cliquant sur la flèche verte
7- le mock doit recevoir régulièrement la requête stoppointdiscovery

Phase IHM
Création d'un abonnement requête sur le StopPointDiscovery 
1- Aller dans Gestion des comptes->Partenaires
2- Saisir SNCF
3- Vérifier/ajouter le nom du partenaire (partenaireRef) est bien SNCF-ACCES
3- Vérifier/ajouter la case Partenaire activé est coché.
4- Vérifier/ajouter la version est 2.4
5- Vérifier/ajouter RPC literal est selectionné
6- Vérifier/Selectionner Producteur/diffuseur
6- Vérifier/Cocher la case services producteurs CheckStatus et ajouter l'adresse http://localhost:8080/CheckStatus
6- Vérifier/Cocher la case services producteurs stoppointdiscovery et ajouter l'adresse http://localhost:8080/Test978
7- Enregistrer


Phase service :
1- Le mock doit recevoir la requête stoppointdiscovery à chaque mise à jour du partenaire
2- Le mock doit recevoir la requête stoppointdiscovery à chaque récupération d'un nouveau fichier reflex

Phase BDD :
1- 103 relations arrêt/producteur sont créé en base pour le producteur SNCF.
