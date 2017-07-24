package com.sirh.mqd.reporting.core.api;

import java.util.Optional;

/**
 * Service de gestion des paramètres de l'appli gérer de façon dynamique
 *
 * @author khalil  
 */
public interface IConfigService {

	/**
	 * Méthode permettant de récuperer les paramètres saisis dynamiquement
	 *
	 * @param id
	 *            nom du paramètre à lire
	 * @return Object le paramètre trouvé
	 */
	Optional<Object> rechercherConfig(String id);
}
