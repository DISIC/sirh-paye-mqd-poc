#!/bin/sh

######################################################################################
# Program: installation-serveur-sftp.sh
#               Copyright OCTO Technology 2017
#
# Description:
#       Ce script permet d'automatiser la configuration d'un serveur SFTP.
# 
# Pré-requis:
#       - S'assurer que l'utilisateur qui exécute ce script ait les droits pour modifier des fichiers du système.
#       - Si nécessaire rendre le script executable: chmod +x installation-serveur-sftp.sh
#
#  Lancer les étapes une à une:
#       1 - ./installation-serveur-sftp.sh	(exécution de la procédure)
# 
#  Tester l'installation:
#       1 - sftp ${filter.sftp.user.name}@localhost
#
######################################################################################
# Auteur:   Alexandre TINGAUD (atingaud@octo.com)
#
# Date de creation: 31/05/2017
#
# Dernieres Modifications:
#
# 31/05/2017	Version: 1.0
#       Original Code
######################################################################################

######################################################################################
# Création de l'utilisateur et du groupe d'utilisateurs du serveur SFTP
######################################################################################
create_user_and_group() {
	echo -e "Création du groupe d'utilisateurs '${filter.sftp.group.name}'."
	sudo groupadd ${filter.sftp.group.name}
	
	echo -e "Création de l'utilisateur '${filter.sftp.user.name}' avec le mot de passe '${filter.sftp.user.password}'."
	sudo useradd ${filter.sftp.user.name} -m -g ${filter.sftp.group.name} -d /mqdsirh -s /sbin/nologin -c "POC MQD SIRH - PAY SFTP User"
	echo "${filter.sftp.user.password}" | sudo passwd ${filter.sftp.user.name} --stdin
}

######################################################################################
# Création de l'arborescence du répertoire SFTP et affectation des droits
######################################################################################
create_sftp_project_directories() {
	echo -e "Création de l'arborescence du répertoire SFTP '${filter.sftp.directory.path}' et affectation des droits."
	sudo mkdir -p ${filter.sftp.directory.path}/{pay,mso,dgac,user}
	sudo chown -R ${filter.sftp.user.name}:${filter.sftp.group.name} ${filter.sftp.directory.path}
}

######################################################################################
# Configuration du fichier /etc/ssh/sshd_config
######################################################################################
configure_sshd_conf_file() {
	if [ -f "/etc/ssh/sshd_config" ]
	then
		echo -e "Configuration du fichier '/etc/ssh/sshd_config'."
		linenbsubsystem=$(grep --line-number "^Subsystem" /etc/ssh/sshd_config | cut -d ":" -f1)
		echo "Subsystem Line Number : $linenbsubsystem"
		if [ -z "$linenbsubsystem" ]
		then
			echo -e -n "\nSubsystem       sftp    internal-sftp" >> /etc/ssh/sshd_config
		else
			sed -i "${linenbsubsystem}d" /etc/ssh/sshd_config
			sed -i "${linenbsubsystem}i\\Subsystem       sftp    internal-sftp" /etc/ssh/sshd_config
		fi
	
		echo "Définition du répertoire racine '/sftp' pour le groupe d'utilisateurs '${filter.sftp.group.name}' et du type de connexion"
		echo -e -n "\nMatch Group ${filter.sftp.group.name}" >> /etc/ssh/sshd_config
		echo -e -n "\n        ChrootDirectory /sftp/%u" >> /etc/ssh/sshd_config
		echo -e -n "\n        ForceCommand internal-sftp" >> /etc/ssh/sshd_config
		
		linenbpwdauth=$(grep --line-number -v "^#" /etc/ssh/sshd_config | grep "PasswordAuthentication " | cut -d ":" -f1)
		echo "PasswordAuthentication Line Number : $linenbpwdauth"
		if [ -z "$linenbpwdauth" ]
		then
			echo -e -n "\nPasswordAuthentication yes" >> /etc/ssh/sshd_config
		else
			sed -i "${linenbpwdauth}d" /etc/ssh/sshd_config
			sed -i "${linenbpwdauth}i\\PasswordAuthentication yes" /etc/ssh/sshd_config
		fi
	else
		echo -e "ERREUR : Le fichier '/etc/ssh/sshd_config' est introuvable sur cet environnement.\nEchec de l'installation."
		exit 0;
	fi
}

######################################################################################
# Désactivation de SELinux sur l'environnement
######################################################################################
disable_selinux() {
	if [ -f "/etc/selinux/config" ]
	then
		echo -e "Désactivation de SELinux sur l'environnement."
		linenbselinux=$(grep --line-number "^SELINUX=" /etc/selinux/config | cut -d ":" -f1)
		echo "SELinux Line Number : $linenbselinux"
		if [ -z "$linenbselinux" ]
		then
			echo -e -n "\nSELINUX=disabled" >> /etc/selinux/config
		else
			sed -i "${linenbselinux}d" /etc/selinux/config
			sed -i "${linenbselinux}i\\SELINUX=disabled" /etc/selinux/config
		fi
	else
		echo -e "WARNING : Aucun fichier de configuration SELinux (/etc/selinux/config) trouvée sur cet environnement.\nAucune action n'est réalisée sur SELinux."
	fi
}

######################################################################################
# Redémarrage du service SSHD
######################################################################################
restart_sshd_service() {
	echo -e "Redémarrage du service SSHD."
	sudo service sshd restart
}

create_user_and_group
create_sftp_project_directories
configure_sshd_conf_file
disable_selinux
restart_sshd_service
