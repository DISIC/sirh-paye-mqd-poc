package com.sirh.mqd.commons.storage.factory;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.entity.AlerteEntity;
import com.sirh.mqd.commons.utils.DateUtils;
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

	public static AlerteEntity createAlerteEntity(final String payLot, final String matricule,
			final AnomalieTypeEnum typeDonnee, final DifferenceDTO donnees) {
		final AlerteEntity entity = new AlerteEntity();
		entity.setId(generateEntityId(payLot, matricule, typeDonnee));
		entity.setPayLot(payLot);
		entity.setMatricule(matricule);
		entity.setType(typeDonnee);
		if (donnees != null) {
			entity.setValeur(donnees.getDonneeGA());
		}
		entity.setDateModification(DateUtils.getCalendarInstance().getTime());
		entity.setEtat(AlerteEtatEnum.A_TRAITER);
		return entity;
	}

	public static AlerteEntity createAlerteEntity(final AlerteDTO alerte) {
		final AlerteEntity entity = new AlerteEntity();
		entity.setId(generateEntityId(alerte.getPayLot(), alerte.getMatricule(), alerte.getType()));
		entity.setPayLot(alerte.getPayLot());
		entity.setMatricule(alerte.getMatricule());
		entity.setType(alerte.getType());
		entity.setValeur(alerte.getValeur());
		entity.setDateModification(alerte.getDateModification());
		entity.setDateCloture(alerte.getDateCloture());
		entity.setDateEcheance(alerte.getDateEcheance());
		entity.setEtat(alerte.getEtat());
		entity.setResponsableLogin(alerte.getResponsableLogin());
		entity.setResponsableNom(alerte.getResponsableNom());
		entity.setResponsablePrenom(alerte.getResponsablePrenom());
		return entity;
	}

	public static AlerteDTO createAlerteDTO(final AlerteEntity entity) {
		final AlerteDTO alerte = new AlerteDTO();
		alerte.setId(entity.getId());
		alerte.setPayLot(entity.getPayLot());
		alerte.setMatricule(entity.getMatricule());
		alerte.setType(entity.getType());
		alerte.setValeur(entity.getValeur());
		alerte.setDateModification(entity.getDateModification());
		alerte.setDateCloture(entity.getDateCloture());
		alerte.setDateEcheance(entity.getDateEcheance());
		alerte.setEtat(entity.getEtat());
		alerte.setResponsableLogin(entity.getResponsableLogin());
		alerte.setResponsableNom(entity.getResponsableNom());
		alerte.setResponsablePrenom(entity.getResponsablePrenom());
		return alerte;
	}

	private static String generateEntityId(final String payLot, final String renoiRHMatricule,
			final AnomalieTypeEnum typeDonnee) {
		final StringBuilder builder = new StringBuilder();
		final Joiner joiner = Joiner.on(Constantes.DASH).useForNull(Constantes.MONGO_COLLECTION_ID_DEFAULT_VALUE);
		joiner.appendTo(builder, payLot, renoiRHMatricule, typeDonnee.name());
		return builder.toString();
	}
}
