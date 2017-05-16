package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
import com.sirh.mqd.commons.utils.DateUtils;
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
		anomalieModel.setNumAnomalie("Ano" + numero);
		anomalieModel.setMinistere(anomalieDTO.getMinistere());
		anomalieModel.setPayCle(anomalieDTO.getPayCle());
		anomalieModel.setDossierNumero(anomalieDTO.getDossierNumero());
		anomalieModel.setDiGestionnaire(anomalieDTO.getDiGestionnaire());
		anomalieModel.setAdminCode(anomalieDTO.getAdminCode());
		anomalieModel.setAdminCodeDepartement(anomalieDTO.getAdminCodeDepartement());
		anomalieModel.setPayLot(anomalieDTO.getPayLot());
		switch (anomalieDTO.getPayCivilite()) {
		case 1:
			anomalieModel.setPayCivilite("Mr");
			break;
		case 2:
			anomalieModel.setPayCivilite("Mme");
			break;
		default:
			anomalieModel.setPayCivilite("?");
			break;
		}
		anomalieModel.setPayNom(anomalieDTO.getPayNom());
		anomalieModel.setPayPrenom(anomalieDTO.getPayPrenom());
		switch (anomalieDTO.getPaySexe()) {
		case 1:
			anomalieModel.setPaySexe("masculin");
			break;
		case 2:
			anomalieModel.setPaySexe("féminin");
			break;
		default:
			anomalieModel.setPaySexe("?");
			break;
		}
		anomalieModel.setPayDateNaissance(DateUtils.clonerDate(anomalieDTO.getPayDateNaissance()));
		anomalieModel.setRenoiRHMatricule(anomalieDTO.getRenoiRHMatricule());
		anomalieModel.setRenoiRHCorpsCode(anomalieDTO.getRenoiRHCorpsCode());
		anomalieModel.setRenoiRHCorpsLibelleCourt(anomalieDTO.getRenoiRHCorpsLibelleCourt());
		anomalieModel.setRenoiRHGradeCode(anomalieDTO.getRenoiRHGradeCode());
		anomalieModel.setRenoiRHGradeLibelleCourt(anomalieDTO.getRenoiRHGradeLibelleCourt());
		anomalieModel.setRenoiRHAffectationCode(anomalieDTO.getRenoiRHAffectationCode());
		anomalieModel.setRenoiRHAffectationLibelleCourt(anomalieDTO.getRenoiRHAffectationLibelleCourt());
		anomalieModel.setRenoiRHDateCertification(DateUtils.clonerDate(anomalieDTO.getRenoiRHDateCertification()));
		switch (anomalieDTO.getRenoiRHCivilite()) {
		case 1:
			anomalieModel.setRenoiRHCivilite("Mr");
			break;
		case 2:
			anomalieModel.setRenoiRHCivilite("Mme");
			break;
		default:
			anomalieModel.setRenoiRHCivilite("?");
			break;
		}
		anomalieModel.setRenoiRHNom(anomalieDTO.getRenoiRHNom());
		anomalieModel.setRenoiRHPrenom(anomalieDTO.getRenoiRHPrenom());
		switch (anomalieDTO.getRenoiRHSexe()) {
		case 1:
			anomalieModel.setRenoiRHSexe("masculin");
			break;
		case 2:
			anomalieModel.setRenoiRHSexe("féminin");
			break;
		default:
			anomalieModel.setRenoiRHSexe("?");
			break;
		}
		anomalieModel.setRenoiRHDateNaissance(DateUtils.clonerDate(anomalieDTO.getRenoiRHDateNaissance()));
		return anomalieModel;
	}

}
