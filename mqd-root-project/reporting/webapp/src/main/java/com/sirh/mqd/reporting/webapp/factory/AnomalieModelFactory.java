package com.sirh.mqd.reporting.webapp.factory;

import org.apache.commons.lang3.StringUtils;

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

	public static AnomalieModel createAnomalieModel(final ComparaisonDTO anomalieDTO) {
		final AnomalieModel anomalieModel = new AnomalieModel();
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

		if (anomalieDTO.getResponsableLogin() != null) {
			anomalieModel.setResponsableLogin(anomalieDTO.getResponsableLogin());
		} else {
			anomalieModel.setResponsableLogin(StringUtils.EMPTY);
		}
		if (anomalieDTO.getResponsableNom() != null) {
			anomalieModel.setResponsableNom(anomalieDTO.getResponsableNom());
		} else {
			anomalieModel.setResponsableNom(StringUtils.EMPTY);
		}
		if (anomalieDTO.getResponsablePrenom() != null) {
			anomalieModel.setResponsablePrenom(anomalieDTO.getResponsablePrenom());
		} else {
			anomalieModel.setResponsablePrenom(StringUtils.EMPTY);
		}
		anomalieModel.setDateModification(anomalieDTO.getDateModification());
		anomalieModel.setAnomalieReouverte(anomalieDTO.isAnomalieReouverte());

		return anomalieModel;
	}

	public static ComparaisonDTO createAnomalieDTO(final String dossierPayLot, final String dossierMatricule,
			final AnomalieModel anomalieModel, final String userLogin, final String userPrenom, final String userNom) {
		final ComparaisonDTO anomalieDTO = new ComparaisonDTO();

		anomalieDTO.setPayLot(dossierPayLot);
		anomalieDTO.setRenoiRHMatricule(dossierMatricule);

		anomalieDTO.setDateEcheance(DateUtils.clonerDate(anomalieModel.getDateEcheance()));

		final DifferenceDTO donnesDTO = new DifferenceDTO();
		donnesDTO.setDonneeGA(anomalieModel.getDonneesGA());
		donnesDTO.setDonneePAY(anomalieModel.getDonneesPAY());
		anomalieDTO.setDonnees(donnesDTO);

		anomalieDTO.setEtatCorrection(AnomalieEtatEnum.enumOf(anomalieModel.getEtatCorrection()));
		anomalieDTO.setModeOperatoire(anomalieModel.getModeOperatoire());
		anomalieDTO.setType(AnomalieTypeEnum.enumOf(anomalieModel.getType()));
		anomalieDTO.setAnomalieDonnees(true);
		anomalieDTO.setAnomalieReouverte(anomalieModel.isAnomalieReouverte());

		anomalieDTO.setResponsableLogin(userLogin);
		anomalieDTO.setResponsableNom(userNom);
		anomalieDTO.setResponsablePrenom(userPrenom);
		anomalieDTO.setDateModification(DateUtils.getCalendarInstance().getTime());

		return anomalieDTO;
	}
}
