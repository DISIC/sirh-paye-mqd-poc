STIFSCRUM-970

En tant que Relais j'analyse le champ serviceStartedTime du CheckStatus des systèmes producteurs afin de déclencher le réabonnement sur le périmètre de ce producteur

Contexte : Récupération d'un checkstatus
Objectif :Lancer un réabonnement sur un producteur qui a redémarré (Changement de servicestartedtime)


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
8- Vérifier/Selectionner Abonnement
9- Vérifier/Cocher la case services producteurs CheckStatus et ajouter l'adresse http://localhost:8080/TEST970
9- Vérifier/Cocher la case services producteurs Stopmonitoring et ajouter l'adresse http://localhost:8080/TEST970
9- Vérifier/Cocher la case services Diffuseurs Stopmonitoring et ajouter l'adresse http://localhost:8080/TEST970
10- Enregistrer


Phase service :
1- Lancer le projet soapui
2- Double cliquer sur Service
3- Lancer le mock en cliquant sur la flèche verte
4- Double cliquer sur SMProducer
5- Lancer le mock en cliquant sur la flèche verte
6- Ouvrir SiriProducerRpcBinding->Subscribe->Test970
7- Lancer la requête en cliquant sur la flèche verte

Phase service, Résultat attendu :
1- La réponse doit être au status True et contenir la référence du message.
2- Le mock doit recevoir un subscribe à chaque checkstatus car le ServiceStartedTime change à chaque checkstatus.

Phase service :
remplacer       <siri:ServiceStartedTime>${=javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.getInstance())}</siri:ServiceStartedTime>
par la date du jour au format : AAAA-MM-JJTHH:MM:SS.052+01:00 exemple : 2015-01-13T14:03:35.052+01:00


Phase service, Résultat attendu :
1- Les subscribe s'arrête car le ServiceStartedTime ne change plus.