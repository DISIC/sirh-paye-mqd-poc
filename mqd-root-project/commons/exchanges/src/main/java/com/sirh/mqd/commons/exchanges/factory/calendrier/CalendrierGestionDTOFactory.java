package com.sirh.mqd.commons.exchanges.factory.calendrier;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Factory de création des dossiers à partir des fichiers reçus en entrée du
 * système.
 *
 * @author alexandre
 */
public final class CalendrierGestionDTOFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private CalendrierGestionDTOFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + CalendrierGestionDTOFactory.class.getName());
	}

	public static EventCalendrierGestionDTO createEventDTOFromDGAC(final String evenement, final String type,
			final String dateDebut, final String dateEcheance, final String acteurs, final String corps,
			final String service, final String couleur) {
		final EventCalendrierGestionDTO calendrierGestion = new EventCalendrierGestionDTO();
		calendrierGestion.setReferentiel(InteractionSirhEnum.DGAC);
		calendrierGestion.setActeurs(acteurs);
		calendrierGestion.setCorps(corps);
		calendrierGestion.setCouleur(couleur);
		if (StringUtils.isBlank(dateDebut)) {
			calendrierGestion.setDebut(DateUtils.getDateBoundHoursToMinimum(DateUtils.parseDateJMAAAA(dateEcheance)));
		} else {
			calendrierGestion.setDebut(DateUtils.getDateBoundHoursToMinimum(DateUtils.parseDateJMAAAA(dateDebut)));
		}
		calendrierGestion.setEcheance(DateUtils.getDateBoundHoursToMaximum(DateUtils.parseDateJMAAAA(dateEcheance)));
		calendrierGestion.setEvenement(evenement);
		calendrierGestion.setService(service);
		calendrierGestion.setType(type);
		return calendrierGestion;
	}
}
