package com.sirh.mqd.reporting.webapp.factory;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.AlerteModel;

/**
 * Factory de création des alertes d'un dossier à manipuler côté IHM.
 *
 * @author alexandre
 */
public final class AlerteModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AlerteModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AlerteModelFactory.class.getName());
	}

	public static AlerteModel createAlerteModel(final AlerteDTO alerteDTO) {
		final AlerteModel alerteModel = new AlerteModel();
		alerteModel.setId(alerteDTO.getId());
		alerteModel.setDateEcheance(DateUtils.clonerDate(alerteDTO.getDateEcheance()));
		alerteModel.setDateModification(DateUtils.clonerDate(alerteDTO.getDateModification()));
		alerteModel.setDateCloture(DateUtils.clonerDate(alerteDTO.getDateCloture()));
		alerteModel.setValeur(alerteDTO.getValeur());
		alerteModel.setPerimetre(alerteDTO.getType().getPerimetre().getLibelle());
		alerteModel.setType(alerteDTO.getType().getLibelle());

		if (alerteDTO.getEtat() != null) {
			alerteModel.setEtat(alerteDTO.getEtat());
		} else {
			alerteModel.setEtat(AlerteEtatEnum.A_TRAITER);
		}
		if (alerteModel.getEtat() == null || AlerteEtatEnum.A_TRAITER.equals(alerteDTO.getEtat())) {
			alerteModel.setListeEtats(AlerteEtatEnum.getEtatsAlerte());
		} else {
			alerteModel.setListeEtats(AlerteEtatEnum.getEtatsAlerteDejaTraites());
		}

		if (alerteDTO.getResponsableLogin() != null) {
			alerteModel.setResponsableLogin(alerteDTO.getResponsableLogin());
		} else {
			alerteModel.setResponsableLogin(StringUtils.EMPTY);
		}
		if (alerteDTO.getResponsableNom() != null) {
			alerteModel.setResponsableNom(alerteDTO.getResponsableNom());
		} else {
			alerteModel.setResponsableNom(StringUtils.EMPTY);
		}
		if (alerteDTO.getResponsablePrenom() != null) {
			alerteModel.setResponsablePrenom(alerteDTO.getResponsablePrenom());
		} else {
			alerteModel.setResponsablePrenom(StringUtils.EMPTY);
		}

		return alerteModel;
	}

	public static AlerteDTO createAlerteDTO(final String dossierPayLot, final String dossierMatricule,
			final AlerteModel alerteModel, final String userLogin, final String userPrenom, final String userNom) {
		final AlerteDTO alerteDTO = new AlerteDTO();

		alerteDTO.setPayLot(dossierPayLot);
		alerteDTO.setMatricule(dossierMatricule);

		alerteDTO.setDateEcheance(DateUtils.clonerDate(alerteModel.getDateEcheance()));
		alerteDTO.setDateCloture(DateUtils.clonerDate(alerteModel.getDateCloture()));

		alerteDTO.setId(alerteModel.getId());
		alerteDTO.setValeur(alerteModel.getValeur());

		alerteDTO.setEtat(alerteModel.getEtat());
		alerteDTO.setType(AnomalieTypeEnum.enumOf(alerteModel.getType()));

		alerteDTO.setResponsableLogin(userLogin);
		alerteDTO.setResponsableNom(userNom);
		alerteDTO.setResponsablePrenom(userPrenom);
		alerteDTO.setDateModification(DateUtils.getCalendarInstance().getTime());

		return alerteDTO;
	}
}
