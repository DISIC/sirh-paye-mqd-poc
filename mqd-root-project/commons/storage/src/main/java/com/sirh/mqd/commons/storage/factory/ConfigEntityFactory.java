package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.ConfigDTO;
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

	public static ConfigDTO createConfigDTO(final ConfigEntity entity) {
		ConfigDTO config = null;
		if (entity != null) {
			config = new ConfigDTO();
			config.setId(entity.getId());
			config.setValeur(entity.getValeur());
		}
		return config;
	}
}
