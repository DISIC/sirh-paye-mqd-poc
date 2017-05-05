STIFSCRUM-1939
En tant que RELAIS, je veux supprimer les abonnements et les jobs liés à un lineRef supprimé lors d'une mise à jour du référentiel CODIFLIGNE.

Contexte : Récupération de référentiel Codifligne
Objectif : A partir d'un service Rest distant, on récupère le référentiel CODIFLIGNE et supprime les abonnements associés au lineRef supprimé

Phase IHM :
1-Aller dans Gestion de Sites, Codifligne.
2-Modifier Endpoint Service Rest :/rest/lc/getlist/0/0.xml
3- Modifier Adresse du service REST : http://localhost:8080 (localhost l'adresse du mock)
4- Aller dans la gestion des comptes partenaires et créer un partenaire producteur/diffuseur avec un service ET et GM activé en mode abonnement 2.4.
 

Phase service :
1-Importer le soapui présent dans le répertoire soapui.
2-Double cliquer sur Rest Project 1/MockService 1 et cliquer sur l'icône de paramètre (le tournevis et la clef)
3-Modifier le docroot pour le faire pointer vers le répertoire xml
4-Lancer le mock
(La version SoapUI 4.6.2 n'est pas totalement opérationnel sur les mock REST, les requêtes reuçut ne seront par exemple pas affiché dans les logs).



Phase BDD :
1- Attendre que la table codifligne soit remplie


Phase service :
1- Eteindre le mock

Phase BDD
1- Insérer un lineRef suplémentaire dans la base codifligne.

Phase service :
1-Utiliser le service Subscribe pour créer les abonnements GM et ET en relation avec le lineRef suplémntaire et le partenaire créé en IHM
2-Lancer le mock
3-Le mock reçoit le notifSubscriptionTerminated correspondant.
