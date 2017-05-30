#!/bin/sh

######################################################################################
# Program: livraison-projet.sh
#               Copyright OCTO Technology 2017
#
# Description:
#       Ce script permet d'automatiser la livraison des livrables sur l'environnement de démo fourni par les Ministères Sociaux.
# 
# Pré-requis:
#       - S'assurer que l'utilisateur qui exécute ce script ait des droits d'écriture dans le répertoire courant.
#       - Si nécessaire rendre le script executable: chmod +x livraison-projet.sh
#       - Aria2c, ZIP, Maven, Java et Tomcat doivent être installés sur la VM cible
#       - Ce script doit être déployé sur la VM
#
#  Lancer les étapes une à une (où $VERSION est la version du projet à installer):
#       1 - ./livraison-projet.sh $VERSION	(exécution de la procédure)
#
######################################################################################
# Auteur:   Alexandre TINGAUD (atingaud@octo.com)
#
# Date de creation: 30/05/2017
#
# Dernieres Modifications:
#
# 30/05/2017	Version: 1.0
#       Original Code
######################################################################################

LOCATION=$(pwd)
VERSION=$1

######################################################################################
# Informations sur l'emplacement actuel de l'utilisateur
######################################################################################
check_current_location() {
	echo -e "Emplacement actuel : $LOCATION."
}

######################################################################################
# Suppression de l'ancienne version du projet
######################################################################################
cleanup_project() {
	echo -e "Suppression de l'ancienne version du projet."
	sudo rm -Rf ./sirh-paye-mqd-poc-tag-*
}

######################################################################################
# Téléchargement de la version du code source du projet
######################################################################################
scripts_git_tag() {
	echo -e "Téléchargement de la version $VERSION du code source du projet"
	aria2c -x10 "https://github.com/DISIC/sirh-paye-mqd-poc/archive/tag-$VERSION.zip"
	unzip "sirh-paye-mqd-poc-tag-$VERSION.zip" -d "$LOCATION"
}

######################################################################################
# Génération des configurations du projet propres à l'environnement de Démo
######################################################################################
scripts_mvn_install() {
	echo -e "Génération des configurations du projet propres à l'environnement de Démo"
	cd "$LOCATION/sirh-paye-mqd-poc-tag-$VERSION/mqd-root-project/parent"
	mvn clean install -P demo
}

######################################################################################
# Installation du projet reporting
######################################################################################
scripts_reporting_delivery() {
	echo -e "Installation du projet reporting"
	cd "$LOCATION/sirh-paye-mqd-poc-tag-$VERSION/mqd-root-project/reporting/target"
	sudo chmod +x "./livraison-projet-reporting.sh"
	sh ./livraison-projet-reporting.sh $VERSION
}

######################################################################################
# Installation du projet supplier
######################################################################################
scripts_supplier_delivery() {
	echo -e "Installation du projet supplier"
	cd "$LOCATION/sirh-paye-mqd-poc-tag-$VERSION/mqd-root-project/supplier/target"
	sudo chmod +x "./livraison-projet-supplier.sh"
	sh ./livraison-projet-supplier.sh $VERSION
}

check_current_location
cleanup_project
scripts_git_tag
scripts_mvn_install
scripts_reporting_delivery
