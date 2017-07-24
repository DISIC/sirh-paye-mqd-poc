package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.storage.entity.ConfigEntity;

public class ConfigEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private ConfigEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + ConfigEntityFactory.class.getName());
	}

	public static Object createConfigValue(final ConfigEntity entity) {
		Object valeur = null;
		if (entity != null) {
			valeur = entity.getValeur();
		}
		return valeur;
	}
}
