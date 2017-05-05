Story 760 : En tant que RELAIS, je m'abonne à un producteur concentrateur en GeneralMessage 2.4

1 - Lancer les 2 mocks du projet SOPAUI fournis.
2 - Se connecter à l'ihm d'administration (en tant qu'administrateur)
3 - Se rendre dans l'onglet "Gestion de compte" puis "Partenaires"
4 - Créer ou modifier un partenaire en activant le service checkstatus en définissant l'url correspondant au mock http://localhost:8088/checkstatus
5 - Modifier le partenaire en activant le service generalMessage en définissant l'url correspondant au mock http://localhost:8088/subscribe
6 - Vérifier que les 2 mocks sont appelés.
7 - Vérifier que le mock Subscribe est appellé en boucle tant que validUntil est à une date précédent la date du jour.(Par défaut : 2012-05-14T19:04:22.480+01:00)
8 - Vérifier qu'une fois la date validUntil changé à une date posterieur à celle du jour l'appelle ne se fait qu'une seul et dernière fois.