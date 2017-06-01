package com.sirh.mqd.commons.exchanges.factory.pivot;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;

/**
 * Factory de création des anomalies d'un dossier à partir des fichiers reçus en
 * entrée du système.
 *
 * @author alexandre
 */
public final class AnomalieDTOFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AnomalieDTOFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieDTOFactory.class.getName());
	}

	public static ComparaisonDTO createComparaisonDTO(final AnomalieTypeEnum typeDonnee, final String donneePay,
			final String donneeRenoiRH) {
		final DifferenceDTO donnees = new DifferenceDTO();
		donnees.setDonneePAY(donneePay);
		donnees.setDonneeGA(donneeRenoiRH);
		final ComparaisonDTO comparaison = new ComparaisonDTO();
		comparaison.setType(typeDonnee);
		comparaison.setDonnees(donnees);
		return comparaison;
	}
}
