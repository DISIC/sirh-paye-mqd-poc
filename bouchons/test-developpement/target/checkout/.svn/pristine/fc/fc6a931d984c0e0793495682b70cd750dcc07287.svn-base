
STIFSCRUM-919

Problème de synchronisation dans redis

Contexte :
Il y a un problème de suppression des entrées Redis lors de la mise à jour de la table postgre.

Lorsqu'un opérateur est supprimé dans postgre, son association transporteur opérateur n'est pas supprimée dans le tampon.
Ce problème de suppression dans le tampon existe potentiellement pour d'autres données, 
un comportement similaire a été remarqué pour les abonnement SM diffuseurs.  
        
        
- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	1-  codifligne.sql (sous le répertoire codifligne)
	2- 	partenaires.sql (sous le répertoire partenaires)
	3- 	01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	4- 	02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	5- 	03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	6- 	04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	7-  05_arretpartenaire.sql (sous le répertoire reflex)
			
- 2) Modifier au niveau de la table operator l'enregistrement correspondant à "SNCF" en modifiant le champ "code" 
égale à la valeur "SNCF".

- 3) Modifier la table participant le champ "diffuseurgeneralmessageurl" à la valeur "http://localhost:8081/SNCF-test166" et le champ "diffuseurgeneralmessageused" à la valeur "TRUE".

- 4) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que les associations suivantes existe dans le tampon :
		- 1) "PARTICIPANT|SNCF-ACCES"
		- 2) "ASSOCIATIONTP|SNCF-ACCES|SNCF"
	
- 5) Lancer le fichier "DEV_STORY_919/soapui/TEST-IVTR-UC-11-abo-soapui-project.xml" au niveau de SOAP pour emettre une requête Subscribe.

- 6) Vérifier dans la table subscription_diffuseur_gm que l'enregistrement exite et que la date initialterminationtime est suppérieure à la date du jour

- 7) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que les associations suivantes existe dans le tampon :
		- 1) "SUBSCRIPTION_GM|108925"

- 8) Lancer le fichier "DEV_STORY_919/soapui/TEST-IVTR-UC-11-notif-soapui-project.xml" au niveau de SOAP pour créer une notification 
StopMonitoring en sélectionnant le test "Notification-test127".

- 9) Lancer redis et effectuer les commandes suivantes :
	2- Vérifier que la synchronisation et faite en tapant les commandes suivantes : 
		- 1) keys *108925*
			- résultat : 
			127.0.0.1:6379> keys *108925*
			 1) "ALVD|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|STIF:StopPoint:Q:108925:"
			 2) "ALD|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:"
			 3) "ALVO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			 4) "AO|108925|SNCF:Operator::SNCF:"
			 5) "ALDO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			 6) "SUBSCRIPTION_GM|108925"
			 7) "REFLEX|108925"
			 8) "ALV|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:"
			 9) "A|108925"
			10) "ADO|108925|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			11) "AD|108925|STIF:StopPoint:Q:108925:"
			12) "ALO|108925|STIF:Line::004004009:|SNCF:Operator::SNCF:"
			13) "ASSOCIATIONAL|108925"
			14) "AP|108925|SNCF-ACCES"
			15) "ASSOCIATIONDA|STIF:StopPoint:Q:108925:"
			16) "AL|108925|STIF:Line::004004009:"
			17) "ALVDO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			
			
		- 2) keys *PAR*
			- résultat :
			127.0.0.1:6379> keys *PAR*
			1) "PARTICIPANT|KEOLIS"
			2) "SYNC_BDDPARTICIPANT"
			3) "PARTICIPANT|SNCF-ACCES"
			4) "PARTICIPANT|100WSIVSIRI"
		
		- 3) keys *ASS*
			- résultat :
			127.0.0.1:6379> keys *ASS*
			1) "ASSOCIATIONTP|SNCF-ACCES|SNCF"
			2) "ASSOCIATIONMA|SNCF:JourneyPattern::2:LOC"
			3) "ASSOCIATIONIA|SNCF:Route::1:LOC"
			4) "ASSOCIATIONLA|STIF:Line::004004009:"
			5) "ASSOCIATIONLV|STIF:Line::004004009:"
			6) "ASSOCIATIONAL|108925"
			7) "ASSOCIATIONDA|STIF:StopPoint:Q:108925:"
			8) "ASSOCIATIONTP|100WSIVSIRI|2"


- 10) Au niveau de IHM Gestion des Comptes -> Partenaires : sélectionner SNCF, 
dès que le formulaire est rafraichit cliquer sur le bouton "Supprimer" adin de ce participant

- 11) Il faut attendre que la synchronisation soit fait (paramétrage à configurer dans les fichiers pour attendre juste quelque dizaines de minutes exemple : 15)

- 12) Lancer redis et effectuer les commandes suivantes :
	2- Vérifier que la synchronisation et faite en tapant les commandes suivantes : 
		- 1) keys *108925*
			- résultat : 
			127.0.0.1:6379> keys *108925*
			 1) "ALVD|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|STIF:StopPoint:Q:108925:"
			 2) "ALD|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:"
			 3) "ALVO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			 4) "AO|108925|SNCF:Operator::SNCF:"
			 5) "ALDO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			 6) "SUBSCRIPTION_GM|108925"
			 7) "REFLEX|108925"
			 8) "ALV|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:"
			 9) "A|108925"
			10) "ADO|108925|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			11) "AD|108925|STIF:StopPoint:Q:108925:"
			12) "ALO|108925|STIF:Line::004004009:|SNCF:Operator::SNCF:"
			13) "ASSOCIATIONAL|108925"
			14) "AP|108925|SNCF-ACCES"
			15) "ASSOCIATIONDA|STIF:StopPoint:Q:108925:"
			16) "AL|108925|STIF:Line::004004009:"
			17) "ALVDO|108925|STIF:Line::004004009:|STIF:StopPoint:Q:108925:|STIF:StopPoint:Q:108925:|SNCF:Operator::SNCF:"
			
			
		- 2) keys *PAR*
			- résultat :
			127.0.0.1:6379> keys *PAR*
			1) "PARTICIPANT|KEOLIS"
			2) "SYNC_BDDPARTICIPANT"
			3) "PARTICIPANT|100WSIVSIRI"
		
		- 3) keys *ASS*
			- résultat :
			127.0.0.1:6379> keys *ASS*
			1) "ASSOCIATIONMA|SNCF:JourneyPattern::2:LOC"
			2) "ASSOCIATIONIA|SNCF:Route::1:LOC"
			3) "ASSOCIATIONLA|STIF:Line::004004009:"
			4) "ASSOCIATIONLV|STIF:Line::004004009:"
			5) "ASSOCIATIONAL|108925"
			6) "ASSOCIATIONDA|STIF:StopPoint:Q:108925:"
			7) "ASSOCIATIONTP|100WSIVSIRI|2"
			
- 13) Les données suivantes ont été bien supprimés du Tampon :
	- 1) "ASSOCIATIONTP|SNCF-ACCES|SNCF"
	- 2) "PARTICIPANT|SNCF-ACCES"
	
- 14) Les autres données n'ont pas été supprimés du Tampon : 
	- 1) "SUBSCRIPTION_GM|108925" (lors de la synchronisation du participant on ne vérifie pas les subscriptionDiffuseur, subscriptionDiffuseurGM et subscriptionProducteur)
	- 2) les données insérées lors de la notification StopMonitoring voir ci-dessus.
			
