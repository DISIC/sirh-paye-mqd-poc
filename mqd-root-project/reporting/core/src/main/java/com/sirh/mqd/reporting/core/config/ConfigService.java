package com.sirh.mqd.reporting.core.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.storage.bc.ConfigBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.IConfigService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.CONFIG_SERVICE)
public class ConfigService implements IConfigService {

	@Autowired
	@Qualifier(PersistenceConstantes.CONFIG_BC)
	private ConfigBC configBC;

	@Override
	public Optional<Object> rechercherConfig(final String id) {
		return configBC.rechercherConfig(id);
	}
}
