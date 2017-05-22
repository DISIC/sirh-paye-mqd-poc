package com.sirh.mqd.commons.storage;

import org.springframework.data.mongodb.core.MongoClientFactoryBean;

/**
 * Classe permettant de construire une connexion à la base de données MongoDB.
 *
 * @see MongoClientFactoryBean
 * @author alexandre
 */
public class MongodbConnectionFactory extends MongoClientFactoryBean {

	/**
	 * Constructeur d'une instance de connexion à la base de données MongoDB.
	 * {@link MongoClientFactoryBean} instance using the given
	 *
	 * @param host
	 *            Host Address.
	 * @param port
	 *            Port used to connect to the instance.
	 */
	public MongodbConnectionFactory(final String host, final int port) {
		super();
		super.setHost(host);
		super.setPort(port);
	}
}
