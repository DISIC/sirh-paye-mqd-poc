package com.sirh.mqd.commons.exchanges.factory.pivot;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;

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

	public static ComparaisonDTO createComparaisonDTO(final String payLot, final String matricule,
			final AnomalieTypeEnum typeDonnee, final InteractionSirhEnum referentiel, final String donnee) {
		final DifferenceDTO donnees = new DifferenceDTO();
		switch (referentiel) {
		case PAY:
			donnees.setDonneePAY(donnee);
			break;
		default:
			donnees.setDonneeGA(donnee);
			break;
		}
		final ComparaisonDTO comparaison = new ComparaisonDTO();
		comparaison.setType(typeDonnee);
		comparaison.setPayLot((payLot != null) ? payLot.trim() : null);
		comparaison.setRenoiRHMatricule((matricule != null) ? matricule.trim() : null);
		comparaison.setDonnees(donnees);
		return comparaison;
	}
}
