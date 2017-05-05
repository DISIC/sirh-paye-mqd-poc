STIFSCRUM-1599 En tant que RELAIS, je définie une in...
creation du WS rest
0/installer soapui5 a partir du partage
1/importer dans soapui le projet du repertoire soapui 
2/changer l 'IP localhost avec celle de la plateforme et le port le reste reste pareil
3/lancer le mock dans soapui bouton vert
3bis/inserer des passages comme specifier dans le testlink-1:
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
1 	

Lancer SOAPUI, importer le projet [projetSoap] et démarrer le Mock test1Producer en cliquant sur start minimized dans le menu du bouton droit.
	

Le mock test1Producer est démarré l'icône du sablier associée est animée.
2 	

Dans SOAPUI lancer le mock test1Producer en cliquant sur "startMinimized" du menu du clique droit.
	

Le mock est lancé, l'icône du sablier est animée
3 	

Lancer le script sql de [fichierBDD] permettant de modifier les données en base du [serveurBDD]
	

Les données sont insérées en base
4 	

Afficher les logs de la machine d'administration2 et les logs de la machine reception2
	

Après une minute les logs indiquent que les passages sont insérés dans le tampon.
5 	

Dans SOAP UI double cliquer sur "Request-TEST-1" situé à l'arborescence TEST_IVTR_UC_01/SiriProducerRpcBinding/GetStopMonitoring
	

Dans le volet de droite affichage de la réponse du relais, elle contient les deux passages du MonitoringRef : STIF:StopPoint:Q:108940:


4/se connecter a l emission en ssh 10.222.37.16
5/lancer la commande sudo tcpdump -i lo -A -l 'dst host localhost and port 8080' | tee dump.log
6/verifier qu il contient des passages en lisant ce fichier dump.log un peu sous le mot json: texte entre crochets:
il doit y avoir dedans STIF:StopPoint:Q:108940