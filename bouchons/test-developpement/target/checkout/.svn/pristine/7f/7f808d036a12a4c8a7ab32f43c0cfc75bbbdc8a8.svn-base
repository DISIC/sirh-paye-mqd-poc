
STIFSCRUM-969

En tant que Relais, je traite la requête contenue dans une demande d'abonnement SM afin de communiquer les données présentes dans le tampon

Contexte : Envois d'une demande d'abonnement avec une requête SM 
Objectif : Récupération d'une notification GM contenant la réponse à la requête

Phase BDD :
1-Lancer les scripts referentiel Reflex sur pgadmin
2-Lancer les script referentiel Codifligne sur pgadmin
3-Lancer le script SQL du Test sur pgadmin
4-Mettre le champ "activationmoderequete" à true ainsi que le champ "status" à true au niveau de la table "participant" 


Phase IHM :

Création d'un producteur de SM
1- Aller dans Gestion des comptes->Partenaires
2- Saisir RATP
3- Vérifier que le nom du partenaire (partenaireRef) est bien 100WSIVSIRI

Création d'un abonnement sur le SM produit
1- Aller dans Gestion des comptes->Partenaires
2- Saisir SNCF
3- Vérifier que le nom du partenaire (partenaireRef) est bien SNCF-ACCES
4- Vérifier que la case Partenaire activé est coché.
5- Vérifier que la version est 2.4
6- Vérifier que RPC literal est selectionné
7- Vérifier que le mode abonnement est selectionné
8- Cocher la case services Diffuseur StopMonitoring et ajouter l'adresse http://localhost:8081/Test969
9- Enregistrer

Phase Redis :
1-Lancer Flushdb sur redis


Phase service :
1- Lancer le projet soapui
2- Double cliquer sur Test969
3- Lancer le mock en cliquant sur la flèche verte
4- Double cliquer sur SMProducer
5- Lancer le mock en cliquant sur la flèche verte
6- Ouvrir SiriProducerRpcBinding->Subscribe->Test1079
7- Lancer la requête en cliquant sur la flèche verte
8- La réponse doit être au status True et contenir la référence du message.
9- Le mock doit recevoir une notification contenant la réponse à la requête.