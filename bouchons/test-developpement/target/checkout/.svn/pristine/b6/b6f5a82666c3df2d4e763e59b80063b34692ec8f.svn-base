STIFSCRUM-983

En tant que Relais, j'ordonnance les jobs de maintenance quotidienne afin de préparer l'initialisation

Contexte : Maintenance quotidienne
Objectif : MAJ/Ajout des tables BDD et Tampon

Phase Fichier :
1- Mettre en place sur le serveur distant les fichiers Reflex et quodifligne

Phase BDD:
1- Lancer le script fournis.

Phase service :
1- Lancer le projet soapui
2- Ouvrir >Test979
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
4- Vérifier/ajouter la version est 2.2
5- Vérifier/ajouter RPC literal est selectionné
6- Vérifier/Selectionner Producteur/diffuseur
7- Vérifier/Cocher la case services producteurs CheckStatus et ajouter l'adresse http://localhost:8080/CheckStatus
8- Vérifier/Cocher la case services producteurs stoppointdiscovery et ajouter l'adresse http://localhost:8080/Test979
9- Enregistrer


Phase service :
1- Le mock doit recevoir la requête stoppointdiscovery à chaque mise à jour du partenaire
2- Le mock doit recevoir la requête stoppointdiscovery à chaque récupération d'un nouveau fichier reflex

Phase BDD :
1- 103 relations arrêt/producteur sont créé en base pour le producteur SNCF.
2- Supprimer les entrée en base du producteur SNCF.
