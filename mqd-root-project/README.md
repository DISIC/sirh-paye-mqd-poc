# sirh-paye-mqd-poc

Prérequis
=====================

* Java 8
* Maven 3

Montée de version
=====================

**Création d'une nouvelle version du projet :**<br/>
* mvn release:clean release:prepare -Dtag=tag-[CURRENT-RELEASE-VERSION] -DreleaseVersion=[CURRENT-RELEASE-VERSION] -DdevelopmentVersion=[NEXT-RELEASE-VERSION]-SNAPSHOT
* mvn release:clean

**Retour arrière si une opération s'est mal déroulée :**<br/>
* mvn release:rollback
