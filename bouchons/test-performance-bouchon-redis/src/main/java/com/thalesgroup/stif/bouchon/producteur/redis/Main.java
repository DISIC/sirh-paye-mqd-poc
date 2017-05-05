package com.thalesgroup.stif.bouchon.producteur.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.bouchon.producteur.redis.simulator.ISimulator;

/**
 * @author adile Bouchon permettant d'insérer des données SM/GM dans le tampon Start application command : java
 *         -DapplicationProperties.simulation
 *         ="/home/adile/Workspace/stif/bouchons/bouchon-producteur-redis/src/main/resources/simulation.properties"
 *         -DapplicationProperties.override
 *         ="/home/adile/Workspace/stif/bouchons/bouchon-producteur-redis/src/main/resources/override.properties" -jar
 *         ./bouchon-producteur-redis.jar
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		context.setValidating(false);
		ISimulator simulateur = context.getBean(ServicesConstantes.SIMULATOR, ISimulator.class);
		simulateur.launch();
		context.close();
	}
}
