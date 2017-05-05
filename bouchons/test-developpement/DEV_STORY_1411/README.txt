
STIFSCRUM-1411

En tant que RELAIS, j'envoie une notification GeneralMessageCancellation vers les diffuseurs abonnés à un message supprimé via l'IHM administration.

- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
	

- 2) Au niveau de l'IHM avec le profif "Administrateur" aller dans l'onglet "Gestion des Comptes" puis l'onglet "Utilisateur"

- 3) Saisir la valeur "r " puis sélectionner la valeur "r r" de la liste proposées.

- 4) Dans la partie "Association de Partenaires" rechercher "SNCF" puis l'ajouter dans la liste des "Partenaires associés" puis cliquer sur le bouton "Enregistrer"

- 5) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- 2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *

- 6) Lancer le script suivant "postgre/TEST-1411_INS_Donnees.sql"

- 7) Importer le fichier "soapui/TEST-IVTR-UC-1411-abo-soapui-project.xml" au niveau de SOAP.

- 8) Lancer le test "test1411-Subscribe Request" qui se trouve dans "soapui/TEST-IVTR-UC-1411-abo-soapui-project.xml/SiriProducerRpcBinding/Subcribe" 
afin de créer des GeneralMessage.

- 9) Lancer le test "test1411Producer" qui se trouve dans "soapui/TEST-IVTR-UC-1411-abo-soapui-project.xml".

- 10) Attendre quelques minutes afin qu'au niveau de message log apparaît un message puis arrêter le test "test1411Producer"

- 11) Effectuer les commandes suivantes :
	1- Vérifier que l'association suivante "SUBSCRIPTION_GM|108940" existe dans le tampon.
	2- Vérifier que l'association suivante "ASSOCIATIONPMSG|SNCF-ACCES" existe dans le tampon.

- 12) Au niveau de l'IHM avec le profil "Transporteur" sélectionner l'onglet "Information Circonstancielle"

- 13) Cliquer sur le bouton "Rechercher" afin que la liste des messages de type GeneralMessage apparaît.

- 14) Sélectionner un message puis cliquer sur le bouton "Supprimer le message" afin de supprimer un message, puis confirmer la suppression.

- 15) Vérifier que la notification est bien envoyée au diffuseurs;

