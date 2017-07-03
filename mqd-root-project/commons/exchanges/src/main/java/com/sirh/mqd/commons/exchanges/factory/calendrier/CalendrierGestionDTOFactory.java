package com.sirh.mqd.commons.exchanges.factory.calendrier;

import org.apache.commons.lang3.StringUtils;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
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

	public static EventCalendrierDTO createEventCalendrierDTOFromDGAC(final String evenement, final String type,
			final String dateDebut, final String dateEcheance, final String acteurs, final String corps,
			final String service, final String couleur) {
		final EventCalendrierDTO eventCalendrier = new EventCalendrierDTO();
		eventCalendrier.setReferentiel(InteractionSirhEnum.DGAC);
		eventCalendrier.setActeurs(acteurs);
		eventCalendrier.setCorps(corps);
		eventCalendrier.setCouleur(couleur);
		if (StringUtils.isBlank(dateDebut)) {
			eventCalendrier.setDebut(DateUtils.getDateBoundHoursToMinimum(DateUtils.parseDateJMAAAA(dateEcheance)));
		} else {
			eventCalendrier.setDebut(DateUtils.getDateBoundHoursToMinimum(DateUtils.parseDateJMAAAA(dateDebut)));
		}
		eventCalendrier.setEcheance(DateUtils.getDateBoundHoursToMaximum(DateUtils.parseDateJMAAAA(dateEcheance)));
		eventCalendrier.setEvenement(evenement);
		eventCalendrier.setService(service);
		eventCalendrier.setType(type);
		return eventCalendrier;
	}
}
