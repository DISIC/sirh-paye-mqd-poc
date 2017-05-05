STIFSCRUM-1145
En tant que RELAIS, je veux acquérir des informations de passage via le service ET 2.4 en mode requête


Contexte : Création d'un job requête à partir d'un ET et récupération des informations.
Objectif : MAJ/Ajout des relations ligne/producteur et création d'un abonnement.

Phase BDD :
1- Lancer les scripts test-fonctionnel/referentiels/reflex (sauf arretpartenaire)
2- Lancer le script SQL fournis avec ce test.


Phase IHM
Création d'un abonnement requête sur le StopPointDiscovery 
1- Aller dans Gestion des comptes->Partenaires
2- Saisir SNCF
3- Vérifier/ajouter le nom du partenaire (partenaireRef) est bien SNCF-ACCES
4- Vérifier/ajouter la case Partenaire activé est coché.
5- Vérifier/ajouter la version est 2.4
6- Vérifier/ajouter RPC literal est selectionné
7- Vérifier/Selectionner Producteur/diffuseur
8- Vérifier/Selectionner Requête
9- Vérifier/Cocher la case services producteurs stoppointdiscovery et ajouter l'adresse http://localhost:8080/TEST1145
10- Vérifier/Cocher la case services producteurs CheckStatus et ajouter l'adresse http://localhost:8080/TEST1145
11- Vérifier/Cocher la case services producteurs EstimatedTimeTable et ajouter l'adresse http://localhost:8080/TEST1145
12- Enregistrer
13- Descendre à Association transporteur
14- Saisir RATP
15- Cliquer sur Rechercher
16- Selectionner RATP 
17- Cliquer sur Ajouter
18- Cliquer sur enregistrer

Phase service :
1- Lancer le projet soapui
2- Ouvrir >Test1145
3- Lancer le mock en cliquant sur la flèche verte

Phase service Résultat attendu:
1- Le mock doit recevoir la requête stoppointdiscovery 
1- Le mock doit recevoir la requête ET 
