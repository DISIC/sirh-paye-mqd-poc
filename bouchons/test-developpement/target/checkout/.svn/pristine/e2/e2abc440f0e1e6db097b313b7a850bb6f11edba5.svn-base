STIFSCRUM-1939
En tant que RELAIS, je veux supprimer les abonnements et les jobs liés à un lineRef supprimé lors d'une mise à jour du référentiel CODIFLIGNE.

Contexte : Récupération de référentiel Codifligne
Objectif : A partir d'un service Rest distant, on récupère le référentiel CODIFLIGNE, suite à quoi si une ligne a disparue les abonnements et les jobs en relation sont supprimés.

Phase IHM :
1-Aller dans Gestion de Sites, Codifligne.
2-Modifier Endpoint Service Rest :/rest/lc/getlist/0/0.xml
3- Modifier Adresse du service REST : http://localhost:8080 (localhost l'adresse du mock)
 

Phase service :
1-Importer le soapui présent dans le répertoire soapui.
2-Double cliquer sur Rest Project 1/MockService 1 et cliquer sur l'icône de paramètre (le tournevis et la clef)
3-Modifier le docroot pour le faire pointer vers le répertoire xml
4-Lancer le mock
(La version SoapUI 4.6.2 n'est pas totalement opérationnel sur les mock REST, les requêtes reuçut ne seront par exemple pas affiché dans les logs).


Phase IHM :
1-Aller dans Gestion de Sites, Codifligne.
2-Modifier Endpoint Service Rest :/rest/lc/getlist/0/1.xml
3- Modifier Adresse du service REST : http://localhost:8080 (localhost l'adresse du mock)
