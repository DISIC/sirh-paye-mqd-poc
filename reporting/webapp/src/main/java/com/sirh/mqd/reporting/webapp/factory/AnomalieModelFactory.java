package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.constante.Constantes;
import com.sirh.mqd.reporting.webapp.model.AnomalieModel;

/**
 * Factory de création des anomalies d'un dossier à manipuler côté IHM.
 *
 * @author alexandre
 */
public final class AnomalieModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AnomalieModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieModelFactory.class.getName());
	}

	public static AnomalieModel createAnomalie(final int numero, final AnomalieDTO anomalieDTO) {
		final AnomalieModel anomalieModel = new AnomalieModel();
		anomalieModel.setNumAnomalie("Ano" + Constantes.SPACE + numero);
		anomalieModel.setDateCloture(DateUtils.clonerDate(anomalieDTO.getDateCloture()));
		anomalieModel.setDateEcheance(DateUtils.clonerDate(anomalieDTO.getDateEcheance()));
		final DifferenceDTO donnes = anomalieDTO.getDonnees();
		anomalieModel.setDonneesGA(donnes.getDonneeGA());
		anomalieModel.setDonneesPAY(donnes.getDonneePAY());
		anomalieModel.setEtatCorrection(anomalieDTO.getEtatCorrection().getLibelle());
		anomalieModel.setModeOperatoire(anomalieDTO.getModeOperatoire());
		anomalieModel.setPerimetre(anomalieDTO.getPerimetre().getLibelle());
		anomalieModel.setType(anomalieDTO.getType().getLibelle());
		return anomalieModel;
	}

}
