
STIFSCRUM-1096

IHM : La recherche par id dans l'écran de visualisation des abonnements génére une erreur de connexion serveur


Contexte :
Lorsque l'on clique sur rechercher avec un id d'abonnement SM, l'erreur "Problème de connexion serveur apparait" 


        
        
- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)


- 2) Modifier au niveau de la table participant le champ "activationmodeabonnement" à la valeur "TRUE" et le champ "activationmoderequete" à la valeur "TRUE".

- 3) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	
- 4) Lancer le fichier "DEV_STORY_1096/soapui/TEST-IVTR-UC-04-soapui-project.xml" au niveau de SOAP pour emettre une requête Subscribe.

- 5) Vérifier dans la table subscription_diffuseu que l'enregistrement exite 

- 6) Vérifier dans la table subscription_producteur que l'enregistrement exite 

- 7) Au niveau de IHM Abonnements -> StopMonitoring : faire une recherche d'un abonnement par Identifiant pour différents rôles (Administrateur, Diffusseur, Producteur)

- 8) Vérifier que les enregistrements au niveau de la base de données figure sur le formulaire.

