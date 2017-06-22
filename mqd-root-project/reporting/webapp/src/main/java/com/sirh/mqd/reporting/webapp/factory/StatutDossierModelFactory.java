package com.sirh.mqd.reporting.webapp.factory;

import com.sirh.mqd.commons.exchanges.dto.statutdossier.StatutDossierDTO;
import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierDisponibiliteEnum;
import com.sirh.mqd.reporting.webapp.model.StatutDossierModel;

/**
 * Factory de création des statuts d'un dossier à manipuler côté IHM.
 *
 * @author khalil
 */
public final class StatutDossierModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private StatutDossierModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + StatutDossierModelFactory.class.getName());
	}

	public static StatutDossierModel createStatutDossierModel(final StatutDossierDTO statutDossierDTO) {
		final StatutDossierModel statutDossierModel = new StatutDossierModel();
		statutDossierModel.setDisponibilite(statutDossierDTO.getDisponibilite());
		statutDossierModel.setAffectation(statutDossierDTO.getAffectation());
		return statutDossierModel;
	}

	public static StatutDossierDTO createStatutDossierDTO(final StatutDossierModel statutDossierModel,
			final String payLot, final String matricule) {
		final StatutDossierDTO statutDossierDTO = new StatutDossierDTO();
		statutDossierDTO.setDisponibilite(statutDossierModel.getDisponibilite());
		statutDossierDTO.setAffectation(statutDossierModel.getAffectation());
		statutDossierDTO.setPayLot(payLot);
		statutDossierDTO.setRenoiRHMatricule(matricule);
		return statutDossierDTO;
	}

	public static StatutDossierDTO createStatutDossierDTO(final DossierDisponibiliteEnum disponibilite,
			final DossierAffectationEnum affectation, final String payLot, final String matricule) {
		final StatutDossierDTO statutDossierDTO = new StatutDossierDTO();
		statutDossierDTO.setDisponibilite(disponibilite);
		statutDossierDTO.setAffectation(affectation);
		statutDossierDTO.setPayLot(payLot);
		statutDossierDTO.setRenoiRHMatricule(matricule);
		return statutDossierDTO;
	}
}
