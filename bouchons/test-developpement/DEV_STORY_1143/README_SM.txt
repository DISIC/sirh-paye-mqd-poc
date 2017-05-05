
STIFSCRUM-1143

En tant que RELAIS, j'envoie une notification d'erreur StopMonitoring aux diffuseurs abonnés sur le périmètre d'un producteur KO configuré en ET.


- 1) Lancer les scripts SQL suivants qui se trouve sous le répertoire bouchons/test-fonctionnel/referentiels :
	-> 1) codifligne.sql (sous le répertoire codifligne)
	-> 2) partenaires.sql (sous le répertoire partenaires)
	-> 3) 01_reflex_groupe_de_lieu.sql (sous le répertoire reflex)
	-> 4) 02_reflex_lieu_d_arret.sql (sous le répertoire reflex)
	-> 5) 03_reflex_zone_de_lieu.sql (sous le répertoire reflex)
	-> 6) 04_reflex_zone_d_embarquement.sql (sous le répertoire reflex)
	-> 7) 05_arretpartenaire.sql (sous le répertoire reflex)
	
- 2) Au niveau de l'IHM le portail de l'application STIF aller dans l'onglet "Gestion des Comptes"
	-> 1) Créer le transporteur ACME-PROD dont le code est OpRef_ACME (Attention il faut d'abord augmenter la valeur de la sequance seq_operator)
	-> 2) Créer le partenaire ACME-PROD dont le type est Producteur/Diffuseur en mode requête pour la version 2.4 et RPC Literal (Attention il faut d'abord augmenter la valeur de la sequance seq_participant)
	-> 3) Renseigner la partie Service Producteur pour le stopMonitoring, CheckStatus et GeneralMessage la valeur de URL est http://localhost:8081/acme
	-> 4) Au niveau de l'association Transporteur ajouter le transporteur ACME-PROD
	-> 5) Au niveau du partenaire SNCF renseigner la partie Services Diffuseurs pour le StopMonitoring dont la valeur est http://localhost:8081/SNCF-test166
	-> 6) Cocher tous les champs au niveau de l'onglet Communication pour les blocs "Communication en réception" puis valider et "Communication en emission" puis valider
	
- 3) Créer un abonnement avec le partenaire SNCF 
	-> 1) Important le projet "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/TEST-IVTR-UC-51-abo-soapui-project.xml" 
	-> 2) lancer le script "test142-Subscribe Request"
	-> 3) Vérifier au niveau de la table "subscription_diffuseur_sm" que l'enregistrement existe et vérifier que la date "initialterminationtime" est suppérieur à la date du jour sinon il faut la modifiée.
	
- 4) Ajouter dans la base de données PostgreSQL en lançant le script "job_detail.sql"

- 5) Ajouter dans la base de données PostgreSQL en lançant le script "reflex_codifligne.sql" 
	-> 1) Augmenter la valeur de la sequance seq_reflex_zone_d_embarquement 
	-> 2) Augmenter la valeur de la sequance seq_codifligne
	
- 6) Lancer redis et effectuer les commandes suivantes :
	1- flunshdb
	2- Vérifier que la synchronisation et faite en tapant la commande suivante : keys *
	3- Vérifier que l'association suivante "SUBSCRIPTION_SM|STIF:StopPoint:Q:60000001:" existe dans le tampon.
	
- 7) Importer les fichier "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/Relais-IVTR-SIRI2-0k-IDF2-4-soapui-project.xml"  
	-> 1) Renseigner au niveau du mock "Prod_Srv_Req_ACME" le port: 8081, le host: localhost et la path: /acme 
	-> 2) Lancer le mock "Prod_Srv_Req_ACME"

- 8) Importer le projet "/test-developpement/DEV_STORY_1611/soapui/stopmonitoring/TEST-516-abo-soapui-project.xml"  
	-> 1) Renseigner au niveau du mock "SNCF-test166" le port: 8082, le host: localhost et le path: /SNCF-test166
	-> 2) Lancer le mock "SNCF-test166"

- 9) Attendre 1 à 2 minutes et les requêtes CheckStatus, GetGeneralMessage et GetStopMonitoring apparaissent dans "Prod_Srv_Req_ACME"

- 10) La notification apparaitre dans "SNCF-test166". Donc, double cliqué sur la notification et dans l'onglet "Request Message" faire la commande "Alt" F pour voir le résultat

- 11) Si la valeur de "Status" est à "true" en reçoit les bonnes valeur. Par contre, si la valeur de "Status" est à "false" alors on a une message contenant
"OtherError" et la description du message est : Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible.

Le résultat est le suivant pour le CheckStatus à false :

<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns1:NotifyStopMonitoring xmlns:ns1="http://wsdl.siri.org.uk">
         <ServiceDeliveryInfo xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri">
            <ns5:ResponseTimestamp>2015-01-20T11:15:30.695Z</ns5:ResponseTimestamp>
            <ns5:ProducerRef>RELAIS</ns5:ProducerRef>
            <ns5:ResponseMessageIdentifier>dghhdhhdhg</ns5:ResponseMessageIdentifier>
            <ns5:RequestMessageRef>dghhdhhdhg</ns5:RequestMessageRef>
         </ServiceDeliveryInfo>
         <Notification xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri">
            <ns5:StopMonitoringDelivery version="2.0:FR-IDF-2.4">
               <ns5:ResponseTimestamp>2015-01-20T11:15:30.695Z</ns5:ResponseTimestamp>
               <ns5:RequestMessageRef>RELAIS:Message::9c0a581d-29cb-4e9b-8e57-0c27d832016f:LOC</ns5:RequestMessageRef>
               <ns5:Status>false</ns5:Status>
               <ns5:ErrorCondition>
                  <ns5:OtherError>
                     <ns5:ErrorText>Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible</ns5:ErrorText>
                  </ns5:OtherError>
                  <ns5:Description>Erreur [PRODUCER_UNAVAILABLE] : SNCF-ACCES indisponible</ns5:Description>
               </ns5:ErrorCondition>
               <ns5:MonitoringRef>STIF:StopPoint:Q:60000001:</ns5:MonitoringRef>
            </ns5:StopMonitoringDelivery>
         </Notification>
         <NotifyExtension xmlns:ns2="http://www.ifopt.org.uk/acsb" xmlns:ns3="http://www.ifopt.org.uk/ifopt" xmlns:ns4="http://datex2.eu/schema/2_0RC1/2_0" xmlns:ns5="http://www.siri.org.uk/siri" xmlns:ns6="http://wsdl.siri.org.uk/siri"/>
      </ns1:NotifyStopMonitoring>
   </soap:Body>
</soap:Envelope> 

