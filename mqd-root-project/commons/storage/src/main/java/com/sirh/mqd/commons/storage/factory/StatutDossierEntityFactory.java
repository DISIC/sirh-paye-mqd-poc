package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.statutdossier.StatutDossierDTO;
import com.sirh.mqd.commons.storage.entity.StatutDossierEntity;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Factory de création des entités des statut dossier.
 *
 * @author khalil
 */
public class StatutDossierEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private StatutDossierEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + StatutDossierEntityFactory.class.getName());
	}

	public static StatutDossierDTO createStatutDossierDTO(final StatutDossierEntity entity) {
		StatutDossierDTO statutDossier = null;
		if (entity != null) {
			statutDossier = new StatutDossierDTO();
			statutDossier.setDisponibilite(entity.getDisponibilite());
			statutDossier.setAffectation(entity.getAffectation());
			statutDossier.setPayLot(entity.getPayLot());
			statutDossier.setRenoiRHMatricule(entity.getRenoiRHMatricule());
		}
		return statutDossier;
	}

	public static StatutDossierEntity createStatutDossierEntity(final StatutDossierDTO statutDossier) {
		StatutDossierEntity entity = null;
		if (statutDossier != null) {
			entity = new StatutDossierEntity();
			entity.setId(generateEntityId(statutDossier.getPayLot(), statutDossier.getRenoiRHMatricule()));
			entity.setDisponibilite(statutDossier.getDisponibilite());
			entity.setAffectation(statutDossier.getAffectation());
			entity.setPayLot(statutDossier.getPayLot());
			entity.setRenoiRHMatricule(statutDossier.getRenoiRHMatricule());
		}
		return entity;
	}

	private static String generateEntityId(final String payLot, final String renoiRHMatricule) {
		final StringBuilder builder = new StringBuilder();
		builder.append(payLot);
		builder.append(Constantes.DASH);
		builder.append(renoiRHMatricule);
		builder.append(Constantes.DASH);
		return builder.toString();
	}
}