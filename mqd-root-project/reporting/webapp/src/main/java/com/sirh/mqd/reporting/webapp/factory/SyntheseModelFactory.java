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
		syntheseModel.setAdresse(null);
		syntheseModel.setContactMail(null);
		syntheseModel.setContactTel(null);
		syntheseModel.setCpVille(null);
		syntheseModel.setDateCertificatoinNir(null);
		syntheseModel.setDateNaissance(DateUtils.clonerDate(dossierDTO.getRenoiRHDateNaissance()));
		syntheseModel.setIdClassotheque(null);
		syntheseModel.setMatriculeAncienSIRH(null);
		syntheseModel.setNationalite(null);
		syntheseModel.setNir(dossierDTO.getNir());
		syntheseModel.setNomNaissance(null);
		syntheseModel.setNomUsage(dossierDTO.getRenoiRHNom());
		syntheseModel.setPrenom(dossierDTO.getRenoiRHPrenom());
		syntheseModel.setSexe(dossierDTO.getRenoiRHSexe());
		syntheseModel.setSituationFamiliale(null);
		syntheseModel.setVilleNaissance(null);

		return syntheseModel;
	}
}
