# sirh-paye-mqd-poc

Prérequis
=====================

* Java 8
* Maven 3
* Tomcat 8

Environnement de Démo
=====================

Configurations
--------------------

* Chemin JAVA_HOME : /usr/java/jdk1.8.0_131
* Chemin TOMCAT_HOME : /opt/tomcat
* Utilisateur du process Tomcat : tomcat
* Groupe du process Tomcat : tomcat
* Récupérer le script **livraison-projet.sh** situé à la racine du projet

Installation
--------------------

**Command** : ./livraison-projet.sh <RELEASE-GIT AU FORMAT : X.Y.Z>

N.B. : La liste des RELEASES GIT est disponible à l'adresse suivante :
https://github.com/DISIC/sirh-paye-mqd-poc/releases/

Accès aux Services
--------------------

**IHM Reporting** : http://10.200.54.183:8080/reporting/

**Web Services Supplier** : http://10.200.54.183:8080/supplier/...
