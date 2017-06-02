package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
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

	public static AnomalieModel createAnomalieModel(final int numero, final ComparaisonDTO anomalieDTO) {
		final AnomalieModel anomalieModel = new AnomalieModel();
		anomalieModel.setNumAnomalie("Ano" + Constantes.SPACE + numero);
		anomalieModel.setDateCloture(DateUtils.clonerDate(anomalieDTO.getDateCloture()));
		anomalieModel.setDateEcheance(DateUtils.clonerDate(anomalieDTO.getDateEcheance()));
		final DifferenceDTO donnes = anomalieDTO.getDonnees();
		anomalieModel.setDonneesGA(donnes.getDonneeGA());
		anomalieModel.setDonneesPAY(donnes.getDonneePAY());

		if (anomalieDTO.getEtatCorrection() != null) {
			anomalieModel.setEtatCorrection(anomalieDTO.getEtatCorrection().getLibelle());
		} else {
			anomalieModel.setEtatCorrection(AnomalieEtatEnum.A_TRAITER.getLibelle());
		}

		anomalieModel.setModeOperatoire(anomalieDTO.getModeOperatoire());
		anomalieModel.setPerimetre(anomalieDTO.getType().getPerimetre().getLibelle());
		anomalieModel.setType(anomalieDTO.getType().getLibelle());
		return anomalieModel;
	}

	public static ComparaisonDTO createAnomalieDTO(final AnomalieModel anomalieModel) {
		final ComparaisonDTO anomalieDTO = new ComparaisonDTO();
		anomalieDTO.setDateCloture(DateUtils.clonerDate(anomalieModel.getDateCloture()));
		anomalieDTO.setDateEcheance(DateUtils.clonerDate(anomalieModel.getDateEcheance()));
		final DifferenceDTO donnesDTO = new DifferenceDTO();
		donnesDTO.setDonneeGA(anomalieModel.getDonneesGA());
		donnesDTO.setDonneePAY(anomalieModel.getDonneesPAY());
		anomalieDTO.setDonnees(donnesDTO);
		anomalieDTO.setEtatCorrection(AnomalieEtatEnum.enumOf(anomalieModel.getEtatCorrection()));
		anomalieDTO.setModeOperatoire(anomalieModel.getModeOperatoire());
		anomalieDTO.setType(AnomalieTypeEnum.enumOf(anomalieModel.getType()));
		return anomalieDTO;
	}
}
