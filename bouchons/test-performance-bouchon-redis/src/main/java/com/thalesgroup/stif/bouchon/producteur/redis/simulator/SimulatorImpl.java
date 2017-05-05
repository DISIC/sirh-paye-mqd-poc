package com.thalesgroup.stif.bouchon.producteur.redis.simulator;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.bouchon.producteur.redis.tests.ITestCase;

/**
 * Lance une simulation
 * 
 * @author adile
 * 
 */
@Component(ServicesConstantes.SIMULATOR)
public class SimulatorImpl implements ISimulator {

	/**
	 * Nombre de notification à envoyer par seconde
	 */
	private int nbNotificationSeconde;

	/**
	 * Durée de la simulation
	 */
	private int dureeSimulation;

	/**
	 * Heure en ms à laquelle la simulation est fini
	 */
	private double finSimulation;

	/**
	 * Status de la simulation
	 */
	private boolean simulationStatus = true;

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application}")
	private Properties properties;

	@Autowired
	@Qualifier(ServicesConstantes.TEST_SM)
	private ITestCase smTestCase;

	@Autowired
	@Qualifier(ServicesConstantes.TEST_GM)
	private ITestCase gmTestCase;

	public int launch() {
		int totalNotif = 0;

		totalNotif = launch(smTestCase, gmTestCase);

		return totalNotif;

	}

	private int launch(final ITestCase testSM, final ITestCase testGM) {

		// On récupére la durée de la simulation, par default on met 8h
		dureeSimulation = Integer.valueOf(properties.getProperty("dureeSimulation", "28800"));
		nbNotificationSeconde = Integer.valueOf(properties.getProperty("nbNotificationSeconde", "300"));

		boolean stopMonitoringStatus = Boolean.valueOf(properties.getProperty("stopMonitoringStatus"));
		boolean generalMessageStatus = Boolean.valueOf(properties.getProperty("generalMessageStatus"));

		RateLimiter limiter = RateLimiter.create(nbNotificationSeconde);

		finSimulation = System.currentTimeMillis() + this.sec2ms(dureeSimulation);

		while (simulationStatus) {

			// On arrete la simulation si le temps est fini
			if (finSimulation <= System.currentTimeMillis()) {
				simulationStatus = false;
			}

			limiter.acquire();

			if (stopMonitoringStatus) {
				testSM.sendNotification();
			}

			if (generalMessageStatus) {
				testGM.sendNotification();
			}

		}

		return testSM.getCountNotificationSend() + testGM.getCountNotificationSend();

	}

	/**
	 * Convertit des secondes en millisecondes
	 * 
	 * @param arg
	 * @return
	 */
	private double sec2ms(final int arg) {
		return arg * 1000.0;
	}

}
