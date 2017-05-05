#!/bin/sh
### ====================================================================== ###
##                                                                          ##
##  SoapUI Bootstrap Script     		                            ##
##          								    ##	
## written by : ilakkis 		                                    ##          
### ====================================================================== ###

## 
## 
## path de l'outil soapui par défaut
SOAPUI_PATH="/opt/soapui/5.0.0"

## enregistrement du pwd d'origine
PROJECT_PATH=$PWD
##nom du fichier soapui contenant les tests etc...
FICHIER="test_auto_increment_1_2_3.xml"

sudo chmod -R 777 *

echo ""
echo "////////////////////////////////////////////"
echo "// Rappel de la configuration d'origine :"
echo "////////////////////////////////////////////"
echo ""

while read ligne
do
	fichier=$(cut -d '=' -f1 <<< $ligne)
	prop=$(cut -d '=' -f2 <<< $ligne)
	line=$fichier"="$prop	
	if [ $fichier == "url" ]; then
		URL=$prop
	fi
	PROPERTIES=$PROPERTIES$line"\n"
done < properties/configuration.properties

URL_CONTAINER=`echo "$URL" | sed "s@\/referentiel@@"`
URL_CONTAINER=`echo "$URL_CONTAINER" | sed "s@jdbc:postgresql:\//@@"`

echo -e $PROPERTIES

echo ""
echo "////////////////////////////////////////////"
echo "// Set configuration :"
echo "////////////////////////////////////////////"
echo ""


## test to start
read -p "Souhaitez-vous configurer les paramètres du projet ? (y/n) :" answer
case ${answer:0:1} in
    y|Y )
	echo ""	
	echo "Le chemin d'accès au repertoire Soap UI est-il correct ?"
	read -p "$SOAPUI_PATH (y/n) :" answer
	case ${answer:0:1} in
	    y|Y )
		## copie des jars du projet vers la librairie de soapUi          
		sudo cp -R lib/* $SOAPUI_PATH/lib/
	    ;;
	    * )
		echo "Nouveau chemin ?"
		read soapui_newpath
		SOAPUI_PATH=$soapui_newpath
		## copie des jars du projet vers la librairie de soapUi          
		sudo cp -R lib/* $SOAPUI_PATH/lib/
	    ;;
	esac
       	echo "Adresse ip de la machine ? (défaut : localhost)"
	read connection
	echo "Votre alias base de donnée ?"
	read username_bdd
	echo "Votre password base de donnée ?"
	read password_bdd
	
	echo "L'url de connexion base de donnée est-elle correcte ?"
	read -p "$URL (y/n) :" answer
	case ${answer:0:1} in
	    y|Y )
		URL="jdbc:postgresql:\/\/"$URL_CONTAINER"\/referentiel"
	    ;;
	    * )
		read -p "Saisissez la nouvelle url (exemple : 10.222.37.0:5432) : " newurl
		URL="jdbc:postgresql:\/\/"$newurl"\/referentiel"
		echo "Saisissez la valeur maintenance database ? (exemple : referentiel) "
		read maintenance_bdd
		URL_CONTAINER=`echo "$URL" | sed "s@\/referentiel@@"`
		URL=$URL_CONTAINER"/"$maintenance_bdd		
	    ;;
	esac
	echo "Adresse ip du serveur emission ? (exemple : localhost)"
	read host_emission
	echo "Port emission ?"
	read port_emission
	echo "Adresse ip du serveur reception ? (exemple : localhost)"
	read host_reception
	echo "Port reception ?"
	read port_reception
	echo "Adresse ip des producteurs ? (exemple : localhost)"
	read ip_producer
	echo "Port producteur (8880 par défaut)?"
	read port_producer
	echo "Adresse ip de la base redis ? (exemple : localhost)"
	read host_redis
	echo "Port redis ? (exemple : 6379)"
	read port_redis
	echo "Adresse ip du serveur administration ? (exemple : localhost)"
	read ip_administration
	echo "Port du serveur administration ? (exemple : 8080)"
	read port_administration
	
	sed -i.bak 's/^\user=.*/user='$username_bdd'/' properties/configuration.properties
	sed -i.bak 's/^\password=.*/password='$password_bdd'/' properties/configuration.properties
	sed -i.bak 's/^\url=.*/url='$URL'/' properties/configuration.properties	
	sed -i.bak 's/^\ip_emission=.*/ip_emission='$host_emission'/' properties/configuration.properties
	sed -i.bak 's/^\ip_reception=.*/ip_reception='$host_reception'/' properties/configuration.properties
	sed -i.bak 's/^\port_emission=.*/port_emission='$port_emission'/' properties/configuration.properties
	sed -i.bak 's/^\port_reception=.*/port_reception='$port_reception'/' properties/configuration.properties
	sed -i.bak 's/^\ipRedis=.*/ipRedis='$host_redis'/' properties/configuration.properties
	sed -i.bak 's/^\portRedis=.*/portRedis='$port_redis'/' properties/configuration.properties
	sed -i.bak 's/^\connection=.*/connection='$connection'/' properties/configuration.properties
	sed -i.bak 's/^\connection=.*/connection='$connection'/' properties/configuration.properties
	sed -i.bak 's/^\ip_producer=.*/ip_producer='$ip_producer'/' properties/configuration.properties
	sed -i.bak 's/^\port_producer=.*/port_producer='$port_producer'/' properties/configuration.properties
	sed -i.bak 's/^\ip_administration=.*/ip_administration='$ip_administration'/' properties/configuration.properties
	sed -i.bak 's/^\port_administration=.*/port_administration='$port_administration'/' properties/configuration.properties

	
	if [ -d "sql_data_$username_bdd" ]; then
		echo ""
	else 
		sudo mkdir sql_data_$username_bdd
	fi
	##création d'un repertoire temporaire
	sudo cp -R sql_data/* sql_data_$username_bdd/	

	##remplacer les adresses ip et ports des producteurs dans les fichiers sql du repertoire temporaire client
	sudo find sql_data_$username_bdd/ -name "*.sql" -exec sed -i 's/${ip_producer}:${port_producer}/'$ip_producer':'$port_producer'/g' {} \;

	echo ""
	echo "////////////////////////////////////////////"
	echo "// Recapitulation de la configuration :     "
	echo "////////////////////////////////////////////"
	echo ""

	while read ligne
	do
		fichier=$(cut -d '=' -f1 <<< $ligne)
		prop=$(cut -d '=' -f2 <<< $ligne)
		echo $fichier"="$prop
	done < properties/configuration.properties	


    ;;
    * )
       echo ""
    ;;
esac




echo ""
echo "////////////////////////////////////////////"
echo "// Test automatisé :"
echo "////////////////////////////////////////////"
echo ""


read -p "Numéro version : " version
echo ""

## test to start
read -p "Démarrer le test ? (y/n) :" answer
case ${answer:0:1} in
    y|Y )
	## Création du repertoire où figurent les resultats de tous les tests
	sudo mkdir RESULTS_TA_INCR_$version
	
	## droits d'écriture et de lecture sur le repertoire global
	sudo chmod -R 777 *

        ## placement sur le repertoire
	cd $SOAPUI_PATH/bin

	## lancement des tests          
	sh ./testrunner.sh -S -r -I $PROJECT_PATH"/$FICHIER" -f $PROJECT_PATH"/RESULTS_TA_INCR_"$version/ -1

    ;;
    * )
        echo "Lancement annulé.."
    ;;
esac



