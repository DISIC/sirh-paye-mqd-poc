# sirh-paye-mqd-poc

Prérequis
=====================

* Java 8
* Maven 3
* Tomcat 8
* CentOS 7

Environnement Local
=====================

Configurations
--------------------

* Chemin JAVA_HOME : /opt/jdk/current
* Chemin TOMCAT_HOME : /opt/tomcat/current
* Utilisateur du process Tomcat : [YOUR DESKTOP USERNAME]
* Groupe du process Tomcat : [YOUR DESKTOP GROUPNAME]
* Installer un serveur SFTP sur une VM CentOS 
1. Télécharger une image minmaliste de CentOS 7 :

	**Lien** : http://isoredirect.centos.org/centos/7/isos/x86_64/

2. Installer Oracle VM VirtualBox
3. Créer une machine virtuelle avec VirtualBox :
	* de type : Linux / Red Hat (64 bits)
	* de type de fichier pour le disque dur : VDI (VirtualBox Disk Image)
4. Démarrer la VM et suivre toutes les étapes d'installation de CentOS (activer la connexion réseau) jusqu'au redémarrage automatique
5. Mettre en place une redirection du port 22 vers le port 2222 :
	* Cliquer successivement sur : Configuration > Réseau > Carte 1 (Mode d'accès réseau : NAT) > Redirection de ports
	* Saisir les paramètres suivants sur une ligne: 
		* Nom : SSH
		* Protocole : TCP
		* Port hôte : 2222
		* Port invité : 22
	* Cliquer sur : OK
6. Se connecter à la VM en super-utilisateur et exécuter la commande suivante :


	**Command** : yum install net-tools openssh* openssl-libs vim


7. Depuis le répertoire **mqd-root-project** dans le code source du projet, compiler le avec la commande suivante :


	**Command** : mvn clean install -Dmaven.test.skip=true


8. Copier sur la machine virtuelle le script **installation-serveur-sftp.sh** situé dans le répertoire généré **supplier/target/livraison/local/sftp** du projet :


	**Command** : scp -P 2222 installation-serveur-sftp.sh root@localhost:/tmp


9. Se connecter à la VM en super-utilisateur et exécuter la commande suivante :


	**Command** : chmod +x installation-serveur-sftp.sh
	**Command** : ./installation-serveur-sftp.s


10. Tester l'accès au serveur SFTP sur la machine virtuelle avec la commande suivante :


	**Command** : sftp -P 2222 sftpuser-mqdsirh@localhost



Environnement de Démo
=====================

Configurations
--------------------

* Chemin JAVA_HOME : /usr/java/jdk1.8.0_131
* Chemin TOMCAT_HOME : /opt/tomcat
* Utilisateur du process Tomcat : tomcat
* Groupe du process Tomcat : tomcat
* Copier sur le serveur le script **livraison-projet.sh** situé à la racine du code source du projet :


	**Command** : scp -P 22 livraison-projet.sh root@:10.200.54.183/tmp
	N.B. : Demander le mot de passe à un membre de l'équipe si nécessaire



Installation
--------------------

	**Command** : chmod +x livraison-projet.sh
	**Command** : ./livraison-projet.sh [RELEASE-GIT AU FORMAT : X.Y.Z]

N.B. : La liste des RELEASES GIT est disponible à l'adresse suivante :<br/>
https://github.com/DISIC/sirh-paye-mqd-poc/releases/

Accès aux Services
--------------------

**IHM Reporting** : http://10.200.54.183:8080/reporting/

**Web Services Supplier** : http://10.200.54.183:8080/supplier/...
