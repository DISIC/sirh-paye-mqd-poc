STIFSCRUM-1690 : (complement) En tant que RELAIS, j'associe un libellé, une sévérité et un type de requête à chaque alerte

Démarrer l'IHM Administration Relais.
Se connecter avec un utilisateur disposant des profils SUPERVISEUR, DIFFUSEUR, et TRANSPORTEUR.
Choisir le profil SUPERVISEUR.
Dans la barre de menu qui s'affiche cliquer sur "Alertes".
Choisir "Toutes les alertes" sans saisir de filtre puis cliquer sur "Rechercher".
Le tableau des alertes contient toutes les alertes prédéfinies dans le tableau  https://ecm.corp.thales/Livelink/livelink.exe/open/0001-0013724104
(dont le périmètre est "STIF" ou "Tous").
Une nouvelle colonne "Sévérité" apparait à droite de la colonne "Type de requête".
3 valeurs sont possibles : Haute, Basse, Haute/Basse.
Pour chaque alerte cette colonne contient la valeur indiquée dans le fichier https://ecm.corp.thales/Livelink/livelink.exe/open/0001-0013724104/
Vérifier que le tableau d'alertes peut être trié par sévérité.
Vérifier que le filtre "Sévérité" affiche un popup contenant les 3 valeurs Haute, Basse, Haute/Basse.
Vérifier que les alertes peuvent être filtrées en fonction de leur sévérité.

Effectuer les mêmes tests avec le profil DIFFUSEUR puis le profil TRANSPORTEUR.
Vérifier que seules les alertes de périmètre "Tous" sont listées.

Avec chacun des 3 profils s'abonner à toutes les alertes.
Générer dynamiquement des alertes.
Vérifier que seules les alertes à Etat non expirées apparaissent dans la table des "Alertes en cours".
Vérifier que les alertes de type Info (expirées ou non) apparaissent dans la table "Historique".
