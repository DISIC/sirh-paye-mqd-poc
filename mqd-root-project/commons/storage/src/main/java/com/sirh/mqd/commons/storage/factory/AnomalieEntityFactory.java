package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.entity.AnomalieEntity;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DifferenceEntity;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Factory de création des entités des données de synthèse en comparaison et des
 * anomalies.
 *
 * @author alexandre
 */
public class AnomalieEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private AnomalieEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + AnomalieEntityFactory.class.getName());
	}

	public static ComparaisonDTO createComparaisonDTO(final ComparaisonEntity entity) {
		ComparaisonDTO comparaison = null;
		if (entity != null) {
			comparaison = new ComparaisonDTO();
			comparaison.setPayLot(entity.getPayLot());
			comparaison.setRenoiRHMatricule(entity.getRenoiRHMatricule());
			comparaison.setType(entity.getType());

			if (entity.getAnomalie() != null) {
				comparaison.setAnomalieDonnees(true);
				comparaison.setDateCloture(entity.getAnomalie().getDateCloture());
				comparaison.setDateEcheance(entity.getAnomalie().getDateEcheance());
				comparaison.setEtatCorrection(entity.getAnomalie().getEtatCorrection());
				comparaison.setModeOperatoire(entity.getAnomalie().getModeOperatoire());

				comparaison.setResponsableNom(entity.getAnomalie().getResponsableNom());
				comparaison.setResponsableLogin(entity.getAnomalie().getResponsableLogin());
				comparaison.setResponsablePrenom(entity.getAnomalie().getResponsablePrenom());
				comparaison.setDateModification(entity.getAnomalie().getDateModification());
			}
			comparaison.setAnomalieReouverte(entity.isAnomalieReouverte());

			final DifferenceDTO donnees = new DifferenceDTO();
			if (entity.getDonnees() != null) {
				donnees.setDonneeGA(entity.getDonnees().getDonneeGA());
				donnees.setDonneePAY(entity.getDonnees().getDonneePAY());
			}
			comparaison.setDonnees(donnees);
		}
		return comparaison;
	}

	public static ComparaisonEntity createComparaisonEntity(final ComparaisonDTO comparaison) {
		ComparaisonEntity entity = null;
		if (comparaison != null) {
			entity = new ComparaisonEntity();
			entity.setId(generateEntityId(comparaison.getPayLot(), comparaison.getRenoiRHMatricule(),
					comparaison.getType()));
			entity.setPayLot(comparaison.getPayLot());
			entity.setRenoiRHMatricule(comparaison.getRenoiRHMatricule());
			entity.setType(comparaison.getType());

			if (comparaison.isAnomalieDonnees()) {
				final AnomalieEntity anomalie = new AnomalieEntity();
				anomalie.setDateCloture(comparaison.getDateCloture());
				anomalie.setDateEcheance(comparaison.getDateEcheance());
				anomalie.setEtatCorrection(comparaison.getEtatCorrection());
				anomalie.setModeOperatoire(comparaison.getModeOperatoire());

				anomalie.setResponsableNom(comparaison.getResponsableNom());
				anomalie.setResponsableLogin(comparaison.getResponsableLogin());
				anomalie.setResponsablePrenom(comparaison.getResponsablePrenom());
				anomalie.setDateModification(comparaison.getDateModification());

				entity.setAnomalie(anomalie);
			}
			entity.setAnomalieReouverte(comparaison.isAnomalieReouverte());

			if (comparaison.getDonnees() != null) {
				final DifferenceEntity donnees = new DifferenceEntity();
				donnees.setDonneeGA(comparaison.getDonnees().getDonneeGA());
				donnees.setDonneePAY(comparaison.getDonnees().getDonneePAY());
				entity.setDonnees(donnees);
			}
		}
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
