package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.entity.AlerteEntity;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Factory de création des entités des alertes sur les corrections d'anomalies.
 *
 * @author alexandre
 */
public class AlerteEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AlerteEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AlerteEntityFactory.class.getName());
	}

	public static AlerteEntity createAlerteEntity(final String payLot, final String renoiRHMatricule,
			final AnomalieTypeEnum typeDonnee) {
		final AlerteEntity entity = new AlerteEntity();
		entity.setId(generateEntityId(payLot, renoiRHMatricule, typeDonnee));
		entity.setPayLot(payLot);
		entity.setRenoiRHMatricule(renoiRHMatricule);
		entity.setType(typeDonnee);
		return entity;
	}

	private static String generateEntityId(final String payLot, final String renoiRHMatricule,
			final AnomalieTypeEnum typeDonnee) {
		final StringBuilder builder = new StringBuilder();
		builder.append(payLot);
		builder.append(Constantes.DASH);
		builder.append(renoiRHMatricule);
		builder.append(Constantes.DASH);
		builder.append(typeDonnee.name());
		return builder.toString();
	}
}
