1/STIF-IVTR_TEST-473:

Preconditions

Avoir passé le test d'initialisation du référentiel STIF-IVTR_TEST-63

Avoir accès à la base de données postgre stif_ive via pgAdmin en utilisant les données ci-dessous :

Variables :

[serveurBDD] : adresse IP du serveur de base de données
[instance] : instance de la base de données
[port] : port de la base de données postgre par défaut 5432
[schéma] : stif_ive
[motDePasse] : mot de passe du schéma stif_ive

[fichierDonnees] : IVTR_UC_50/postgresql/2.4/TEST-UC_50_2.4_INS_Donnees.sql
[fichierDonnees2] : IVTR_UC_50/postgresql/2.4/TEST-473_INS_Donnees.sql
[projetSoap] :  fichier projet à charger sous SOAP UI simuler un producteur ="test-fonctionnel/IVTR_UC_50/soapui/TEST_IVTR_UC_50-soapui-project.xml"

5 	

Lancer SOAPUI, importer le projet [projetSoap] et démarrer le Mock injection en cliquant sur start minimized dans le menu du bouton droit.
	

Le mock injection est démarré l'icône du sablier associée est animée.
6 	

Lancer le script sql de [fichierDonnees] permettant de modifier les données en base du [serveurBDD]
	

Les données sont insérées en base.

Attendre 1 minute, le mock injection est requêté.
7 	

Lancer le script sql de [fichierDonnees2] permettant de modifier les données en base du [serveurBDD] et permettant de mettre le CheckStatus du producteur SNCF à FALSE.
	

Les données sont insérées en base.

Le mock injection cesse d'être requêté.
8 	

Double cliquer et lancer sur la requête TEST_IVTR_UC_50/SiriPorducerBinding/GetGeneralMessage/Request_TEST-473 en cliquant sur le bouton triangle vert.
	

Affichage de la réponse du RELAIS indiquant que le producteur SNCF-ACCES est indisponible.


2/ utiliser le mock diffuseur du testlink-415:
Preconditions

Avoir passé le test d'initialisation du référentiel STIF-IVTR_TEST-63

Avoir accès à la base de données postgre stif_ive via pgAdmin en utilisant les données ci-dessous :

Variables :

[serveurBDD] : adresse IP du serveur de base de données
[instance] : instance de la base de données
[port] : port de la base de données postgresql, par défaut XXX
[schema] :  stif_ive
[motDePasse] : mot de passe du schéma stif_ive

[fichierDonnees] : TEST-415_INS_Donnees.sql
[fichierDonnees2] : referentiels/communnication/init.sql
[projetSoap] :  fichier projet à charger sous SOAP UI simuler un producteur ="test-acceptation/IVTR_UC_53/soapui/TEST_IVTR_UC_53_abo.xml"

1 	

Ouvrir soapui, importer [projetSoap] et démarrer les mock test415Producer et mock test415Diffuseur en double cliquant sur chacun d'eux et en cliquant sur le triangle vert
	

Les mock sont démarrés
2 	

Accéder à l'IHM d'administration avec le profil administrateur, se rendre dans la page de gestion des comptes partenaires en cliquant sur les onglets "Gestion des comptes" puis "Partenaires"
	

Affichage de la page de gestion des comptes partenaires qui invitent à saisir un partenaire
3 	

Cliquer sur le bouton ajouter un partenaire
	

Apparition de la fenêtre d'ajout d'un partenaire
4 	

Saisir les informations suivantes :

- Nom : SNCF
- Type : Producteur/Diffuseur
- Version : 2.4
- Style de WSDL : RPC Literal
- Mode : Abonnement

Cliquer sur le bouton ajouter
	

Affichage de la page de paramétrage du partenaire
5 	

Cliquer sur le volet Association Transporteurs, rechercher le transporteur SNCF dans la barre de recherche et cliquer sur le bouton rechercher
	

Affichage du transporteur SNCF dans le volet transporteurs disponibles
6 	

Sélectionner le transporteur SNCF et cliquer sur le bouton ajouter
	

Le transporteur SNCF se déplace du volet "transporteur"
7 	

Cliquer sur le bouton "enregistrer"
	

Affichage d'un message de confirmation d'enregistrement
8 	

Cocher la case "Partenaire activé", dans le volet services producteurs saisir les informations suivantes :

- Référence du partenaire : SNCF-ACCES
- Cocher la case CheckStatus (services producteurs) et entrer l'url http://10.222.9.205:8080/test415Producer
- Cocher la case GeneralMessage (services producteurs) et entrer l'url http://10.222.9.205:8080/test415Producer

Cliquer sur le bouton enregistrer au bas de la page
	

Affichage d'un message de confirmation d'enregistrement
9 	

Cliquer sur le bouton "enregistrer"
	

Affichage d'un message de confirmation d'enregistrement
10 	

Ouvrir pgAdmin, consulter le contenu de la table "job_detail"
	

Affichage d'un job CheckStatus sur l'URL indiquée dans l'IHM
11 	

Ouvrir la fenêtre soapui et consulter le volet de log du mock test415Producer
	

Affichage d'un log indiquant que le mock a été requêté.
12 	

Saisir les informations suivantes :

- Nom : RATP
- Type : Diffuseur
- Version : 2.4
- Style de WSDL : RPC Literal
- Mode : Abonnement

Cliquer sur le bouton ajouter
	

Les données sont insérées en base, affichage de la page de paramétrage du partenaire
13 	

Cocher la case "Partenaire activé", dans le volet services producteurs saisir les informations suivantes :

- Référence du partenaire : 100WSIVSIRI
- Cocher la case GeneralMessage (services diffuseurs) et entrer l'url http://10.222.9.205:8080/test415Diffuseur

Cliquer sur le bouton enregistrer au bas de la page
	

Affichage d'un message de confirmation d'enregistrement
14 	

Cliquer sur le volet Association Transporteurs, rechercher le transporteur RATP dans la barre de recherche et cliquer sur le bouton rechercher
	

Affichage du transporteur RATP dans le volet transporteurs disponibles
15 	

Sélectionner le transporteur RATP et cliquer sur le bouton ajouter
	

Le transporteur RATP se déplace du volet "transporteur"
16 	

Lancer la requête "test415-Subscribe Request" située sous l'arborescence SiriProducerRpcBinding/Subscribe.
	

Dans le volet de droite, affichage de la réponse à la demande d'abonnement de la part du Relais.

La réponse a le status TRUE.
18 		
19 	

Dans pgAdmin, consulter le contenu de la table subsciption_diffuseur_gm
	

Affichage de l'abonnement GeneralMessage du diffuseur RATP
20 	

Ouvrir soapui et importer le [projetSoap]. Lancer la notification "Notification-test415" située sous l'arborescence SiriConsumerRpcBinding/NotifyGeneralMessage
	

Dans le mock test415Diffuseur, affichage d'un log indiquant que le mock a reçu une notification GeneralMessage
21 	

Double cliquer sur le log apparu dans le mock test415Diffuseur
	

Affichage de la notification GeneralMessage reçue par le mock 415Diffuseur.

3/resultat attendu : message d erreur dans le volet gauche du mock diffuseur


