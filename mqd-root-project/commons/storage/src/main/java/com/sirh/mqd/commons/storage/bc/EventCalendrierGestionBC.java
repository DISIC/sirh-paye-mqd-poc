package com.sirh.mqd.commons.storage.bc;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IEventCalendrierGestionDAO;
import com.sirh.mqd.commons.storage.entity.EventCalendrierGestionEntity;
import com.sirh.mqd.commons.storage.factory.EventCalendrierGestionEntityFactory;

/**
 * Implémentation du BusinessController permettant de gérer les evenements du
 * calendrier gestion
 *
 * @author khalil
 */
@Service(PersistenceConstantes.EVENT_CALENDRIER_GESTION_BC)
public class EventCalendrierGestionBC {

	@Autowired
	@Qualifier(PersistenceConstantes.EVENT_CALENDRIER_GESTION_DAO)
	private IEventCalendrierGestionDAO eventCalendrierGestionDAO;

	/**
	 * Méthode permettant de récuperer un evenement du calendrier gestion
	 *
	 * @param evenement
	 * @param debut
	 * @param echeance
	 * @return {@link EventCalendrierGestionDTO} correspondant à l'evenement
	 *         d'un calendrier gestion contenant les informations issues de la
	 *         base de données
	 */
	public Optional<EventCalendrierGestionDTO> rechercherEventCalendrierGestion(final String evenement,
			final Date debut, final Date echeance) {
		final EventCalendrierGestionEntity eventCalendrierGestionEntity = this.eventCalendrierGestionDAO
				.selectEventCalendrierGestion(evenement, debut, echeance);
		return Optional.ofNullable(
				EventCalendrierGestionEntityFactory.createEventCalendrierGestionDTO(eventCalendrierGestionEntity));
	}

	/**
	 * Méthode permettant de lister les evenements du calendrier gestion
	 *
	 * @return {@link List} correspondant à tous les evenements d'un calendrier
	 *         gestion présents dans la base de donnée
	 */
	public void listerEventCalendrierGestion(final EventCalendrierGestionDTO eventCalendrierGestionDTO) {
		final EventCalendrierGestionEntity entity = EventCalendrierGestionEntityFactory
				.createEventCalendrierGestionEntity(eventCalendrierGestionDTO);
		eventCalendrierGestionDAO.upsertCalendrierGestion(entity);
	}

	public List<EventCalendrierGestionDTO> listerEventCalendrierGestion() {
		final List<EventCalendrierGestionEntity> eventCalendrierGestionEntities = this.eventCalendrierGestionDAO
				.selectEventsCalendrierGestion();
		return eventCalendrierGestionEntities.stream()
				.map(eventCalendrierGestionEntity -> EventCalendrierGestionEntityFactory
						.createEventCalendrierGestionDTO(eventCalendrierGestionEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant de mettre à jour ou de créer un evenement
	 *
	 * @param eventCalendrierGestionDTO
	 *            l'evenement du calendrier gestion à enregistrer
	 */
	public void modifierCreerEventCalendrierGestion(final EventCalendrierGestionDTO eventCalendrierGestionDTO) {
		final EventCalendrierGestionEntity entity = EventCalendrierGestionEntityFactory
				.createEventCalendrierGestionEntity(eventCalendrierGestionDTO);
		eventCalendrierGestionDAO.upsertCalendrierGestion(entity);
	}

	/**
	 * Méthode permettant de supprimer un evenement
	 *
	 * @param eventCalendrierGestionDTO
	 *            l'evenement du calendrier gestion à supprimer
	 */
	public void supprimerEventCalendrierGestion(final EventCalendrierGestionDTO eventCalendrierGestionDTO) {
		final EventCalendrierGestionEntity entity = EventCalendrierGestionEntityFactory
				.createEventCalendrierGestionEntity(eventCalendrierGestionDTO);
		eventCalendrierGestionDAO.deleteCalendrierGestion(entity);
	}
}
