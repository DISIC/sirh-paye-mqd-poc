
STIFSCRUM-1164 En tant qu'EQUIPE, je mets en place des règles spécifiques aux indépendants et celles spécifiques aux concentrateurs sur les services SIRI

1 - Creation d'un diffuseur concentrateur
2 - Faire une requete SM avec ce diffuseur sur le RELAIS
	- Les champs spécifiées comme non applicable dans le profil SIRI ne sont pas pris en compte. Un log trace les champs qui ne sont pas appliquées à la requete.
3 - Idem pour une demande d'abonnement

Champs non applicable pour un StopMonitoringRequest et abonnement SM :

- LineRef
- DestinationRef
- OperatorRef
- StopVisitTypeEnumeration par defaut à ALL
- MaximumStopVisits
- MinimumStopVisitsPerLine
- MinimumStopVisitsPerLineVia
- MaximumNumberOfCalls

- IncrementalUpdates par defaut à TRUE (abonnement uniquement)
- ChangeBeforeUpdates par defaut à 1 minutes (abonnement uniquement)
