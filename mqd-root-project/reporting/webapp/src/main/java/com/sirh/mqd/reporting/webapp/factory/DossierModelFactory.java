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
		dossierModel.setPayLot(dossierDTO.getPayLot());
		dossierModel.setNir(dossierDTO.getNir());
		dossierModel.setRenoiRHMatricule(dossierDTO.getRenoiRHMatricule());
		dossierModel.setRenoiRHCorpsCode(dossierDTO.getRenoiRHCorpsCode());
		dossierModel.setRenoiRHCorpsLibelleCourt(dossierDTO.getRenoiRHCorpsLibelleCourt());
		dossierModel.setRenoiRHAffectationCode(dossierDTO.getRenoiRHAffectationCode());
		dossierModel.setRenoiRHAffectationLibelleCourt(dossierDTO.getRenoiRHAffectationLibelleCourt());
		dossierModel.setRenoiRHNom(dossierDTO.getRenoiRHNom());
		dossierModel.setRenoiRHPrenom(dossierDTO.getRenoiRHPrenom());
		dossierModel.setRenoiRHDateNaissance(DateUtils.clonerDate(dossierDTO.getRenoiRHDateNaissance()));
		dossierModel.setRenoiRHDateNaissanceAsString(DateUtils.formateDateMMAA(dossierDTO.getRenoiRHDateNaissance()));
		dossierModel.setNbAlertes(dossierDTO.getNbAlertes());
		dossierModel.setNbAnomalies(dossierDTO.getNbAnomalies());
		return dossierModel;
	}
}
