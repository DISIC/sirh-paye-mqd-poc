package com.sirh.mqd.reporting.core.api;

import java.util.Optional;

import com.sirh.mqd.commons.exchanges.dto.pivot.ConfigDTO;

/**
 *  * Service de gestion des paramètres de l'appli gérer de façon dynamique  *
 *  * @author khalil  
 */

public interface IConfigService {

	/**
	 * Méthode permettant de récuperer les paramètres saisis dynamiquement
	 *
	 * @param id
	 *                        nom du paramètre à lire
	 * @return {@link ConfigDTO} le paramètre trouvé
	 */
	Optional<ConfigDTO> rechercherConfig(String id);
}
