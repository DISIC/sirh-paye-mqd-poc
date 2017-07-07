package com.sirh.mqd.commons.storage.bc;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ICalendrierGestionDAO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierEntity;
import com.sirh.mqd.commons.storage.factory.CalendrierGestionEntityFactory;

/**
 * Implémentation du BusinessController permettant de gérer les evenements du
 * calendrier gestion
 *
 * @author khalil
 */
@Service(PersistenceConstantes.CALENDRIER_GESTION_BC)
public class CalendrierGestionBC {

	@Autowired
	@Qualifier(PersistenceConstantes.CALENDRIER_GESTION_DAO)
	private ICalendrierGestionDAO eventCalendrierGestionDAO;

	/**
	 * Méthode permettant de récuperer un evenement du calendrier gestion
	 *
	 * @param id
	 *            l'identifiant unique de l'événement généré lors de sa création
	 * @return {@link EventCalendrierDTO} correspondant à l'evenement d'un
	 *         calendrier gestion contenant les informations issues de la base
	 *         de données
	 */
	public Optional<EventCalendrierDTO> rechercherEvent(final String id) {
		final EventCalendrierEntity eventCalendrierGestionEntity = this.eventCalendrierGestionDAO
				.selectEventCalendrierGestion(id);
		return Optional
				.ofNullable(CalendrierGestionEntityFactory.createEventCalendrierDTO(eventCalendrierGestionEntity));
	}

	/**
	 * Méthode permettant de lister les evenements du calendrier gestion
	 *
	 * @param referentiel
	 *            le sirh
	 * @param service
	 *            le service associé à l'événement
	 * @param dateFin
	 *            la date de début de recherche
	 * @param dateDebut
	 *            la date de fin de recherche
	 * @return {@link List} correspondant à tous les evenements d'un calendrier
	 *         gestion présents dans la base de donnée
	 */
	public List<EventCalendrierDTO> listerEvents(final InteractionSirhEnum referentiel, final String service,
			final Date dateDebut, final Date dateFin) {
		final List<EventCalendrierEntity> eventCalendrierGestionEntities = this.eventCalendrierGestionDAO
				.selectEventsCalendrierGestion(referentiel, service, dateDebut, dateFin);
		return eventCalendrierGestionEntities.stream()
				.map(eventCalendrierGestionEntity -> CalendrierGestionEntityFactory
						.createEventCalendrierDTO(eventCalendrierGestionEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant de mettre à jour ou de créer un evenement
	 *
	 * @param eventCalendrierDTO
	 *            l'evenement du calendrier gestion à enregistrer
	 */
	public void modifierCreerEvent(final EventCalendrierDTO eventCalendrierDTO) {
		final EventCalendrierEntity entity = CalendrierGestionEntityFactory
				.createEventCalendrierEntity(eventCalendrierDTO);
		this.eventCalendrierGestionDAO.updateCalendrierGestion(entity);
	}

	/**
	 * Méthode permettant de supprimer un evenement
	 *
	 * @param eventCalendrierDTO
	 *            l'evenement du calendrier gestion à supprimer
	 */
	public void supprimerEvent(final EventCalendrierDTO eventCalendrierDTO) {
		final EventCalendrierEntity entity = CalendrierGestionEntityFactory
				.createEventCalendrierEntity(eventCalendrierDTO);
		this.eventCalendrierGestionDAO.deleteCalendrierGestion(entity);
	}
}
