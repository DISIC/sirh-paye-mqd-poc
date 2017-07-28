# sirh-paye-mqd-poc

Prérequis
=====================

* Java 8
* Maven 3
* Apache Tomcat 8.0
* MongoDB 3.4
* Elasticsearch 5.5
* Serveur SFTP (voir procédure d'installation ci-dessous)
* CentOS 7
* Compte utilisateur (voir procédure d'importation des comptes utilisateurs dans le [WIKI](https://github.com/DISIC/sirh-paye-mqd-poc/wiki))


Environnement Local
=====================

Configurations
--------------------

* Chemin JAVA_HOME : /opt/jdk/current
* Chemin TOMCAT_HOME : /opt/tomcat/current
* Utilisateur du process Tomcat : [YOUR DESKTOP USERNAME]
* Groupe du process Tomcat : [YOUR DESKTOP GROUPNAME]
* Installer un serveur SFTP sur une VM CentOS

Installation du serveur SFTP
--------------------

1. Télécharger une image minimaliste de CentOS 7 :

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
<pre><code>Commande : yum install net-tools openssh* openssl-libs vim</code></pre>
7. Depuis le répertoire **mqd-root-project** dans le code source du projet, compiler le avec la commande suivante :
<pre><code>Commande : mvn clean install -Dmaven.test.skip=true</code></pre>
8. Copier sur la machine virtuelle le script **installation-serveur-sftp.sh** situé dans le répertoire généré **supplier/target/livraison/local/sftp** du projet :
<pre><code>Commande : scp -P 2222 installation-serveur-sftp.sh root@localhost:/tmp</code></pre>
9. Se connecter à la VM en super-utilisateur et exécuter la commande suivante :
<pre><code>Commande : ssh -p 2222 root@localhost
Commande : cd /tmp
Commande : chmod +x installation-serveur-sftp.sh
Commande : ./installation-serveur-sftp.sh</code></pre>
10. Tester l'accès au serveur SFTP sur la machine virtuelle avec la commande suivante :
<pre><code>Commande : sftp -P 2222 sftpuser-mqdsirh@localhost</code></pre>

Environnement de Démo
=====================

Configurations
--------------------

* Chemin JAVA_HOME : /usr/java/jdk1.8.0_131
* Chemin TOMCAT_HOME : /opt/tomcat
* Utilisateur du process Tomcat : tomcat
* Groupe du process Tomcat : tomcat
* Copier sur le serveur le script **livraison-projet.sh** situé à la racine du code source du projet :
<pre><code>Commande : scp -P 22 livraison-projet.sh root@10.200.54.183:/tmp</code></pre>

N.B. : Demander le mot de passe à un membre de l'équipe si nécessaire

Installation
--------------------

<pre><code>Commande : ssh -p 22 root@10.200.54.183
Commande : cd /tmp
Commande : chmod +x livraison-projet.sh
Commande : ./livraison-projet.sh [RELEASE-GIT AU FORMAT : X.Y.Z]</code></pre>

N.B. : La liste des RELEASES GIT est disponible à l'adresse suivante :<br/>
https://github.com/DISIC/sirh-paye-mqd-poc/releases/

Accès aux Services
--------------------

**IHM Reporting** : http://10.200.54.183:8080/reporting/

**Web Services Supplier** : http://10.200.54.183:8080/supplier/...
