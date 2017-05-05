STIFSCRUM-1660

En tant qu'ADMINISTRATEUR, je veux visualiser les abonnement ET sur l'IHM d'administration.

Contexte : Création d'un abonnement à partir d'un ET et visualisation sur l'IHM
Objectif : MAJ/Ajout des relations ligne/producteur et création d'un abonnement.

Phase BDD :
1- Lancer les scripts test-fonctionnel/referentiels/reflex (sauf arretpartenaire)
2- Lancer le script SQL fournis avec ce test.

Phase service :
1- Lancer le projet soapui
2- Ouvrir >Test1660
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
4- Vérifier/ajouter la case Partenaire activé est coché.
5- Vérifier/ajouter la version est 2.4
6- Vérifier/ajouter RPC literal est selectionné
7- Vérifier/Selectionner Producteur/diffuseur
8- Vérifier/Selectionner Abonnement
9- Vérifier/Cocher la case services producteurs stoppointdiscovery et ajouter l'adresse http://localhost:8080/TEST1660
10- Enregistrer


Phase service Résultat attendu:
1- Le mock doit recevoir la requête stoppointdiscovery à chaque mise à jour du partenaire

Phase IHM Résultat attendu :
1- Après un checkStatus sur le mock
2- Vérifier dans Abonnements>EstimatedTimetable Partenaire : SNVF-ACCES ligne C00040 retourne un abonnement désactivé.

