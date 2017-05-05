
STIFSCRUM-1143

En tant que RELAIS, j'envoie une notification d'erreur StopMonitoring aux diffuseurs abonnés sur le périmètre d'un producteur KO configuré en ET.


- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	-> 1)  codifligne.sql (sous le répertoire codifligne)
	-> 2)  partenaires.sql (sous le répertoire partenaires)
	-> 3)  01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	-> 4)  02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	-> 5)  03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	-> 6)  04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	-> 7)  05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Au niveau de l'IHM le portail de l'application STIF aller dans l'onglet "Gestion des Comptes"
	-> 1) Créer le transporteur ACME-PROD dont le code est OpRef_ACME (Attention il faut d'abord augmenter la valeur de la sequance seq_operator)
	-> 2) Créer le partenaire ACME-PROD dont le type est Producteur/Diffuseur en mode requête pour la version 2.4 et RPC Literal (Attention il faut d'abord augmenter la valeur de la sequance seq_participant)
	-> 3)  Au niveau du partenaire ACME-PROD renseigner la partie Services Producteurs en cochant "CheckStatus" et en renseignant la valeur de l'URL par "http://localhost:8081/checkstatus";
	-> 4)  Au niveau du partenaire ACME-PROD renseigner la partie Services Producteurs en cochant "GeneralMessage" et en  renseignant la valeur de l'URL par "http://localhost:8081/GeneralMessage";
	-> 5)  Au niveau du partenaire SNCF renseigner la partie Services Diffuseurs en cochant "GeneralMessage" et en renseignant la valeur de l'URL par  http://localhost:8081/SNCF-test166;
	-> 6)  le champ "Partenaire activé" est coché;
	-> 7)  Sélectionner "Producteur/Diffuseur" pour le champ "Type de partenaire";
	-> 8)  Sélectionner "Version 2.4" pour le champ "Version SIRI";
	-> 9)  Sélectionner "RPC Literal" pour le champ "Style de WSDL";
	-> 10) Sélectionner "Requête" pour le champ "Mode(s) d'acquisition";
	-> 11) Saisir la valeur 1 pour le champ "Nombre de requêtes par minute";
	-> 12) Saisir la valeur 10 pour le "Nombre d'arrêts par requête";
	-> 13) Cliquer sur le bouton "Valider" pour enregistrer les données en base PostgreSQL.
	-> 14) Cocher tous les champs au niveau de l'onglet Communication pour les blocs "Communication en réception" puis valider et "Communication en emission" puis valider

- 3) Créer un abonnement avec le partenaire SNCF 
	-> 1)  Important le projet "/test-developpement/DEV_STORY_1143/soapui/generalmessage/TEST-1143-soapui-project.xml" 
	-> 2)  lancer le script "Subscribe"
	-> 3)  Vérifier au niveau de la table "subscription_diffuseur_sm_gm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur à la date du jour sinon il faut la modifiée.

- 4) Lancer redis et effectuer les commandes suivantes :
	-> 1)  flunshdb
	-> 2)  Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	-> 3)  Vérifier que l'association suivante "SUBSCRIPTION_GM|108925" existe dans le tampon.
	
- 5) Importer le projet "/test-developpement/DEV_STORY_1143/soapui/generalmessage/TEST-1143-soapui-project.xml" au niveau de SoapUI.
	-> 1) Lancer le "SiriProducerRpcBinding MockService" du "CheckStatus" en renseignant le port: 8081, le host: localhost et la path: /checkstatus
	-> 2) Vérifier avant de lancer le fichier "SiriProducerRpcBinding MockService" du "GetGeneralMessage" que la valeur de la date "ValidUntilTime" du bloc "GeneralMessage" est supérieur à la date du jour
	-> 3) Lancer le fichier "SiriProducerRpcBinding MockService" du "GetGeneralMessage" en renseignant le port: 8081, le host: localhost et la path: /GeneralMessage

- 6) Importer le projet "/test-developpement/DEV_STORY_1143/soapui/stopmonitoring/TEST-516-abo-soapui-project.xml"  
	-> 1) Renseigner au niveau du mock "SNCF-test166" le port: 8081, le host: localhost et le path: /SNCF-test166
	-> 2) Lancer le mock "SNCF-test166"

- 7) Attendre 1 à 2 minutes et les requêtes CheckStatus et GetGeneralMessage apparaissent dans "SiriProducerRpcBinding MockService"

- 8) La notification apparaitre dans "SNCF-test166". Donc, double cliqué sur la notification et dans l'onglet "Request Message" faire la commande "Alt" F pour voir le résultat

- 9) Vérifier au nivea de la table "subscription_diffuseur_sm" que l'enregistrement a été supprimer après près 2 minutes

- 10) Si la valeur de "Status" est à "true" en reçoit les bonnes valeur. Par contre, si la valeur de "Status" est à "false" alors on a une message contenant
"OtherError" et la description du message est : Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible.

Le résultat est le suivant pour le CheckStatus à false :

<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns1:NotifyGeneralMessage xmlns:ns1="http://wsdl.siri.org.uk">
         <ServiceDeliveryInfo xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri">
            <ns5:ResponseTimestamp>2015-01-20T12:44:15.072Z</ns5:ResponseTimestamp>
            <ns5:ProducerRef>RELAIS</ns5:ProducerRef>
            <ns5:ResponseMessageIdentifier>RELAIS:ResponseMessage::3ce5317e-af43-45d4-ac2c-d4cdecfcfd79:LOC</ns5:ResponseMessageIdentifier>
            <ns5:RequestMessageRef>idMessage</ns5:RequestMessageRef>
         </ServiceDeliveryInfo>
         <Notification xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri">
            <ns5:GeneralMessageDelivery version="2.0:FR-IDF-2.4">
               <ns5:ErrorCondition>
                  <ns5:CapabilityNotSupportedError>
                     <ns5:CapabilityRef>Erreur [PRODUCER_UNAVAILABLE] : ACME-PROD indisponible</ns5:CapabilityRef>
                  </ns5:CapabilityNotSupportedError>
                  <ns5:Description>Erreur [PRODUCER_UNAVAILABLE] : ACME-PROD indisponible</ns5:Description>
               </ns5:ErrorCondition>
            </ns5:GeneralMessageDelivery>
         </Notification>
         <NotifyExtension xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri"/>
      </ns1:NotifyGeneralMessage>
   </soap:Body>
</soap:Envelope>
