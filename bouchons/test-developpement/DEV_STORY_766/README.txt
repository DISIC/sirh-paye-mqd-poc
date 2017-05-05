STIFSCRUM-766 En tant que Producteur, je veux accéder aux messages du service GeneralMessage à travers l'IHM administration

Pré-requis :
 1	- Ajouter un ou plusieurs partnaire(s)
 2	- Associer des partenaires au compte utilisateur
 

 1	- Lancer le mock producteur (voir fichier SOAP TEST-378) via SOAPUI afin de recevoir des messages GM - TestGeneralMessage
 2	- Ajouter l'adresse du mock dans l'ihm du participant (ajouter l'url du service GM)
 3	- Forcer le checkstatus_response dans la base
 4	- Verifier dans le tampon la presence d'une clé "keys ASSOCIATIONPMSG|*"
 	- Verifier la présence de message GM dans le tampon "keys MESSAGE|*" 
 	Ps : ASSOCIATIONPMSG permet de faire le lien entre un partenaire et un message GM
 5	- Aller dans l'ihm en tant que rôle PRODUCTEUR_ET_DIFFUSEUR puis faire une recherche sans filtre afin d'avoir tous les messages associées aux partenaires du compte
 6  - Selectionner un message afin de visualiser ses valeurs dans toutes les langues.
 
 Ps : 	En fonction du cron du job GM, le message risque de revenir assez rapidement une fois supprimé. 
 		Mettre un cron toutes les 30 sec. 	
 