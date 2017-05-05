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
	 * @param usePool
	 *            Turns on or off the use of connection pooling.
	 * @param hostName
	 *            HostName.
	 * @param port
	 *            Port used to connect to the instance.
	 * @param clusterName
	 *            Cluster name
	 * @param poolConfig
	 *            pool configuration. Defaulted to new instance if
	 *            {@literal null}.
	 */
	public MongodbConnectionFactory(final boolean usePool, final String hostName, final int port,
			final String clusterName) {
		super(creerRedisSentinelConfiguration(clusterName, sentinelHosts), creerRedisPoolConfiguration(poolConfig));
		super.setUsePool(usePool);
		super.setHost(hostName);
		super.setPort(port);
	}
}
