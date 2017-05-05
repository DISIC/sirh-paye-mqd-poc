
STIFSCRUM-900

En tant que RELAIS, on stocke un GeneralMessage et on notifie Emission suite à la réception d'une notification GeneralMessage 2.4 
de la part d'un producteur.

- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Modifier au niveau de la table participant l'enregistrement correspondant à "KEOLIS" en modifiant le champ "diffuseurgeneralmessageurl" 
égale à la valeur "http://localhost:8081/SNCF-test166" et la valeur du champ "diffuseurgeneralmessageused" à TRUE.

- 3) Cocher tous les champs au niveau de l'IHM Communication qui sont "Communication en réception" puis valider et "Communication en emission" puis valider

- 4) Lancer le fichier "soapui/abonnementGeneralMessage.xml" au niveau de SOAP afin de créer les abonnements Général Message.

- 5) Vérifier au niveau de la table "subscription_diffuseur_gm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur 
à la date du jour sinon il faut la modifiée.

- 6) Vérifier que le champ "address" est bien renseigné.

- 7) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "SUBSCRIPTION_GM|108925" existe dans le tampon.
	
- 8) Lancer "SNCF-test166" qui se trouve sous le projet "TEST_IVTR_UC_11_notif" mais en séléctionnant "NotifyGeneralMessage" correspondant au champ "Operation"

- 9) Vérifier avant de lancer le fichier "soapui/notificationGeneralMessage.xml" au niveau de SOAP que la valeur de la date "ValidUntilTime" du bloc "GeneralMessage"
est supérieur à la date du jour

- 10) Lancer le fichier "soapui/notificationGeneralMessage.xml" au niveau de SOAP afin de stocker le GeneralMessage puis créer le message 
"GeneralMessageDelivery" et envoyer le message "GeneralMessageDelivery"

- 11) Vérififier au niveau "SNCF-test166" que la notification est reçu.