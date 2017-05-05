
STIFSCRUM-768

En tant que Relais, je traite la requête contenue dans une demande d'abonnement GM afin de communiquer les données présentes dans le tampon

Contexte : Envois d'une demande d'abonnement avec une requête GM 
Objectif : Récupération d'une notification GM contenant la réponse à la requête

Phase BDD
1-Lancer les scripts referentiel Reflex sur pgadmin
2-Lancer les script referentiel Codifligne sur pgadmin
1-Lancer le script SQL du Test sur pgadmin
2-Lancer Flushdb sur redis


Phase IHM :

Création d'un producteur de GM
1- Aller dans Gestion des comptes->Partenaires
2- Saisir RATP
3- Vérifier que le nom du partenaire (partenaireRef) est bien 100WSIVSIRI

Création d'un abonnement sur le GM produit
1- Aller dans Gestion des comptes->Partenaires
2- Saisir SNCF
3- Vérifier que le nom du partenaire (partenaireRef) est bien SNCF-ACCES
3- Vérifier que la case Partenaire activé est coché.
4- Vérifier que la version est 2.4
5- Vérifier que RPC literal est selectionné
6- Vérifier que le mode abonnement est selectionné
7- Cocher la case services Diffuseur GeneralMessage et ajouter l'adresse http://localhost:8088/Test1079 
8- Enregistrer

Phase service :
1- Lancer le projet soapui
2- Double cliquer sur Test1079
3- Lancer le mock en cliquant sur la flèche verte
4- Double cliquer sur GMProducer
5- Lancer le mock en cliquant sur la flèche verte
6- Double cliquer sur GeneralMessage
7- Lancer le mock en cliquant sur la flèche verte
8- Ouvrir SiriProducerRpcBinding->Subscribe->Test1079
9- Lancer la requête en cliquant sur la flèche verte
10- La réponse doit être au status True et contenir la référence du message.
11- Le mock doit recevoir une notification contenant la réponse à la requête.