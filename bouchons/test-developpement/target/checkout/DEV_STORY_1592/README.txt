STIFSCRUM-1592

En tant que RELAIS, je veux émettre les informations sur les lignes via le service LinesDiscovery 2.2

1/suivre les étapes du testlink STIF-IVTR_TEST-1:Envoi d'une requête SM et reception d'une réponse valide avec nouveaux passages:
Summary

Ce test permet de vérifier que le relais envoie une requête StopMonitoring à un producteur. Le producteur envoie une réponse valide, le relais stocke le résultat dans son tampon.

Use Case : IVTR_UC_01 Acquisition SM en mode Requête
Preconditions

Connaître l'outil SOAPUI et savoir le configurer.

Avoir passé le test d'initialisation du référentiel STIF-IVTR_TEST-63

Avoir passé le test d'initialisation de la base de données redis STIF-IVTR_TEST-53

Avoir accès à la base de données postgre stif_ive via pgAdmin en utilisant les données ci-dessous :

Variables :

[serveurBDD] : adresse IP du serveur de base de données
[instance] : instance de la base de données
[port] : port de la base de données postgresql, par défaut XXX
[schema] :  stif_ive
[motDePasse] : mot de passe du schéma stif_ive


[fichierDonnees] : TEST-1_INS_Donnees.sql

[fichierRedis] : /bouchons/test-fonctionnel/IVTR_UC_01/redis/TEST-1_INS_Donnees.redis

[projetSoap] : fichier projet à charger sous SOAP UI simuler un producteur = "test-acceptation/IVTR_UC_01/soapui/TEST-IVTR-UC-01-soapui-project.xml"

Step actions:

Lancer SOAPUI, importer le projet [projetSoap] et démarrer le Mock test1Producer en cliquant sur start minimized dans le menu du bouton droit.
	

Le mock test1Producer est démarré l'icône du sablier associée est animée.


Dans SOAPUI lancer le mock test1Producer en cliquant sur "startMinimized" du menu du clique droit.
	

Le mock est lancé, l'icône du sablier est animée


Lancer le script sql de [fichierBDD] permettant de modifier les données en base du [serveurBDD]
	

Les données sont insérées en base


Afficher les logs de la machine d'administration2 et les logs de la machine reception2
	

Après une minute les logs indiquent que les passages sont insérés dans le tampon.


Dans SOAP UI double cliquer sur "Request-TEST-1" situé à l'arborescence TEST_IVTR_UC_01/SiriProducerRpcBinding/GetStopMonitoring
	

Dans le volet de droite affichage de la réponse du relais, elle contient les deux passages du MonitoringRef : STIF:StopPoint:Q:108940:

2/importer le xml du projet joint dans soapUI et lancer la requete.

Resultat attendu: obtenir des informations sur les lignes: identifiant et nom
