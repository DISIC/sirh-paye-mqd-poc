package com.thalesgroup.stif.bouchon.producteur.redis.tests;

public interface ITestCase {

	/**
	 * Envoi une notification
	 */
	public void sendNotification();

	/**
	 * Renvoi le nombre de notification envoyé la derniere seconde
	 * 
	 * @return
	 */
	public int getCountNotificationSend();

}
