package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.DossierModel;

/**
 * Factory de création des dossiers à manipuler côté IHM.
 *
 * @author alexandre
 */
public final class DossierModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private DossierModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + DossierModelFactory.class.getName());
	}

	public static DossierModel createDossier(final DossierDTO dossierDTO) {
		final DossierModel dossierModel = new DossierModel();
		dossierModel.setMinistere(dossierDTO.getMinistere());
		dossierModel.setDossierNumero(dossierDTO.getDossierNumero());
		dossierModel.setDiGestionnaire(dossierDTO.getDiGestionnaire());
		dossierModel.setAdminCode(dossierDTO.getAdminCode());
		dossierModel.setAdminCodeDepartement(dossierDTO.getAdminCodeDepartement());
		dossierModel.setPayLot(dossierDTO.getPayLot());
		dossierModel.setRenoiRHMatricule(dossierDTO.getRenoiRHMatricule());
		dossierModel.setRenoiRHCorpsCode(dossierDTO.getRenoiRHCorpsCode());
		dossierModel.setRenoiRHCorpsLibelleCourt(dossierDTO.getRenoiRHCorpsLibelleCourt());
		dossierModel.setRenoiRHGradeCode(dossierDTO.getRenoiRHGradeCode());
		dossierModel.setRenoiRHGradeLibelleCourt(dossierDTO.getRenoiRHGradeLibelleCourt());
		dossierModel.setRenoiRHAffectationCode(dossierDTO.getRenoiRHAffectationCode());
		dossierModel.setRenoiRHAffectationLibelleCourt(dossierDTO.getRenoiRHAffectationLibelleCourt());
		dossierModel.setRenoiRHDateCertification(DateUtils.clonerDate(dossierDTO.getRenoiRHDateCertification()));
		switch (dossierDTO.getRenoiRHCivilite()) {
		case 1:
			dossierModel.setRenoiRHCivilite("Mr");
			break;
		case 2:
			dossierModel.setRenoiRHCivilite("Mme");
			break;
		default:
			dossierModel.setRenoiRHCivilite("?");
			break;
		}
		dossierModel.setRenoiRHNom(dossierDTO.getRenoiRHNom());
		dossierModel.setRenoiRHPrenom(dossierDTO.getRenoiRHPrenom());
		switch (dossierDTO.getRenoiRHSexe()) {
		case 1:
			dossierModel.setRenoiRHSexe("masculin");
			break;
		case 2:
			dossierModel.setRenoiRHSexe("féminin");
			break;
		default:
			dossierModel.setRenoiRHSexe("?");
			break;
		}
		dossierModel.setRenoiRHDateNaissance(DateUtils.clonerDate(dossierDTO.getRenoiRHDateNaissance()));
		return dossierModel;
	}

}
