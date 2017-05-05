package com.thalesgroup.stif.test.performance

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._
import java.util.Properties
import java.io.FileInputStream


class SoapSimulationDiffuseur extends Simulation {
	
	def gatling_home = System.getenv("GATLING_HOME")
	
	// chargement du fichier de config
	val prop = new Properties()
	prop.load(new FileInputStream(gatling_home+"/user-files/data/config.properties"))
	

	
	// On charge le fichier de notification StopMonitoring
	val sourceStopMonitoring = scala.io.Source.fromFile(gatling_home+prop.getProperty("stopMonitoring.notificationXml"))(io.Codec.ISO8859)
	val getStopMonitoringSoap = sourceStopMonitoring.mkString
	sourceStopMonitoring.close()
	
	// On charge le fichier de notification GeneralMessage
	val sourceGeneralMessage = scala.io.Source.fromFile(gatling_home+prop.getProperty("generalMessage.notificationXml"))(io.Codec.ISO8859)
	val getGeneralMessageSoap = sourceGeneralMessage.mkString
	sourceGeneralMessage.close()
	
	
	
	val myCustomFeeder = new Feeder[String] {
		import org.joda.time.DateTime
		import scala.util.Random
		import java.util.Calendar
		import java.util.Date;
		import java.text.SimpleDateFormat
		import javax.xml.datatype.DatatypeConfigurationException;
		import javax.xml.datatype.DatatypeFactory;
		import javax.xml.datatype.XMLGregorianCalendar;
		import java.util.GregorianCalendar;

		  private val RNG = new Random

		  // nombre aleatoire compris entre [a...b]
		  private def randInt(a:Int, b:Int) = RNG.nextInt(b-a) + a		  

		  // always return true as this feeder can be polled infinitively
		  override def hasNext = true

		  override def next: Map[String, String] = {
		    // Creation de la date au format xml
			val gc = new GregorianCalendar()
			gc.setTime(new Date());
			val xmlgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)

			// Création du messageIdentifier
			val messageIdentifier = scala.math.abs(java.util.UUID.randomUUID.getMostSignificantBits)
			
						
			//Création de l'infoMessageIdentifier
			val infoMessageIdentifier = scala.math.abs(java.util.UUID.randomUUID.getMostSignificantBits)
			
			//Création de l'infoMessageIdentifier
			val itemIdentifier = scala.math.abs(java.util.UUID.randomUUID.getMostSignificantBits)

		    Map("requestTimestamp" -> String.valueOf(xmlgc), 
			"messageIdentifier" -> String.valueOf("SNCF:Message::"+messageIdentifier+":"),
			"infoMessageIdentifier" -> String.valueOf("SNCF:InfoMessage::"+infoMessageIdentifier+":"),
			"itemIdentifier" -> String.valueOf("SNCF:Item::"+itemIdentifier+":"),
			"startTime" -> String.valueOf(xmlgc),
			"previewInterval" -> String.valueOf(randInt(1,360))
			)
		    }
		}


	// on défini le header de la requete
	val httpConf = httpConfig
	        .acceptHeader("text/html,text/xml,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
	        .acceptEncodingHeader("gzip, deflate")
	        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
	        .connection("keep-alive")
	        .userAgentHeader("Apache-HttpClient/4.1.1 (java 1.5)")

	
	
	val smUserCount = Integer.valueOf(prop.getProperty("GetStopMonitoring.usersCount"))
	val smSimuDuration = Integer.valueOf(prop.getProperty("GetStopMonitoring.duration"))
	
	// Scenario de test pour StopMonitoring
	val scnStopMonitoring = scenario(prop.getProperty("stopMonitoring.scenarioName"))
		.during(smSimuDuration.intValue,"seconds"){
		// on set le jeux de données statique
		feed(csv(prop.getProperty("stopMonitoring.csvDataFile")).circular)
		// on set le jeux de données aleatoire
		.feed(myCustomFeeder)
		.exec(
			http(prop.getProperty("stopMonitoring.actionName"))
			// on set l'endpoint du service à attaquer
			.post(prop.getProperty("ws.url.emission"))
			// on set le contenu de la requete
			.body(getStopMonitoringSoap)
			// on verifie le code retour
			//.check(status.is(200))
			)
	}
		
		
	val gmUserCount = Integer.valueOf(prop.getProperty("GetGeneralMessage.usersCount"))
	val gmSimuDuration = Integer.valueOf(prop.getProperty("GetGeneralMessage.duration"))
	
	// Scenario de test pour GeneralMessage
	val scnGeneralMessage = scenario(prop.getProperty("generalMessage.scenarioName"))
		.during(gmSimuDuration.intValue,"seconds"){
		// on set le jeux de données statique
		feed(csv(prop.getProperty("stopMonitoring.csvDataFile")).circular)
		// on set le jeux de données aleatoire
		.feed(myCustomFeeder)
		.exec(
			http(prop.getProperty("generalMessage.actionName"))
			// on set l'endpoint du service à attaquer
			.post(prop.getProperty("ws.url.emission"))
			// on set le contenu de la requete
			.body(getGeneralMessageSoap)
			// on verifie le code retour
			//.check(status.is(200))
		)
	}
	
	
		
	setUp(scnStopMonitoring.users(smUserCount).protocolConfig(httpConf))
	
	setUp(scnGeneralMessage.users(gmUserCount).protocolConfig(httpConf))
}
