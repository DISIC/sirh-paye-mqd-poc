package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.model.SyntheseModel;

/**
 * Factory de création de la synthese d'un agent pour un dossier à manipuler
 * côté IHM.
 *
 * @author Maxime
 */

public final class SyntheseModelFactory {
	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private SyntheseModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + SyntheseModelFactory.class.getName());
	}

	public static SyntheseModel createSyntheseModel(final DossierDTO dossierDTO) {
		final SyntheseModel syntheseModel = new SyntheseModel();
		syntheseModel.setAdresse("Non renseigné");
		syntheseModel.setContactMail("Non renseigné");
		syntheseModel.setContactTel("Non renseigné");
		syntheseModel.setCpVille("Non renseigné");
		syntheseModel.setDateCertificationNir(null);
		if (dossierDTO.getRenoiRHDateNaissance() != null) {
			syntheseModel.setDateNaissance(DateUtils.clonerDate(dossierDTO.getRenoiRHDateNaissance()));
		} else {
			syntheseModel.setDateNaissance(null);
		}
		syntheseModel.setIdClassotheque("Non renseigné");
		syntheseModel.setMatriculeAncienSIRH("Non renseigné");
		syntheseModel.setNationalite("Non renseigné");
		if (dossierDTO.getNir() != null) {
			syntheseModel.setNir(dossierDTO.getNir());
		} else {
			syntheseModel.setNir("Non renseigné");
		}
		syntheseModel.setNomNaissance("Non renseigné");
		if (dossierDTO.getRenoiRHNom() != null) {
			syntheseModel.setNomUsage(dossierDTO.getRenoiRHNom());
		} else {
			syntheseModel.setNomUsage("Non renseigné");
		}
		if (dossierDTO.getRenoiRHPrenom() != null) {
			syntheseModel.setPrenom(dossierDTO.getRenoiRHPrenom());
		} else {
			syntheseModel.setPrenom("Non renseigné");
		}
		if (dossierDTO.getRenoiRHSexe() != null) {
			syntheseModel.setSexe(dossierDTO.getRenoiRHSexe());
		} else {
			syntheseModel.setSexe(null);
		}
		syntheseModel.setSituationFamiliale("Non renseigné");
		syntheseModel.setVilleNaissance("Non renseigné");

		return syntheseModel;
	}
}
