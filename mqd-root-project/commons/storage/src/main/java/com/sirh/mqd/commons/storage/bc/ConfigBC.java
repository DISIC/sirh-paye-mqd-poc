package com.sirh.mqd.commons.storage.bc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.ConfigDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IConfigDAO;
import com.sirh.mqd.commons.storage.entity.ConfigEntity;
import com.sirh.mqd.commons.storage.factory.ConfigEntityFactory;

@Service(PersistenceConstantes.CONFIG_BC)
public class ConfigBC {

	@Autowired
	@Qualifier(PersistenceConstantes.CONFIG_DAO)
	private IConfigDAO configDAO;

	/**
	 * Méthode permettant de récuperer les seuils de changement de couleurs des
	 * anomalies et des alertes
	 */
	public Optional<ConfigDTO> rechercherConfig(final String id) {
		final ConfigEntity entity = this.configDAO.selectConfig(id);
		return Optional.ofNullable(ConfigEntityFactory.createConfigDTO(entity));
	}

}
