package com.sirh.mqd.reporting.webapp.views.evenement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.ICalendrierGestionService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.CalendrierGestionModelFactory;
import com.sirh.mqd.reporting.webapp.model.EventCalendrierModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue du calendrier de gestion
 *
 * @author khalil
 */
@Named(ViewConstantes.CALENDRIER_GESTION_BEAN)
@SessionScoped
public class CalendrierGestionBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6838265929563237041L;

	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	@Inject
	@Qualifier(CoreConstantes.CALENDRIER_GESTION_SERVICE)
	private ICalendrierGestionService calendrierGestionService;

	/**
	 * Calendrier qui contiendra les events
	 */
	private ScheduleModel scheduleModel;

	/**
	 * Evénement sélectionné du calendrier
	 */
	private ScheduleEvent event;

	/**
	 * Liste des événements de type "information"
	 */
	private List<EventCalendrierModel> listeInformations;

	private Date viewStartDate;

	private Date viewEndDate;

	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.listeInformations = new ArrayList<EventCalendrierModel>();
			this.scheduleModel = new DefaultScheduleModel();
			this.event = new DefaultScheduleEvent();
			listerEvents();
		}
	}

	private void listerEvents() {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService());
		listerEventInformations(eventsCalendrierDTO);
		listerEventCalendrierGestion(eventsCalendrierDTO);
	}

	private void listerEventInformations(final List<EventCalendrierDTO> eventsCalendrierDTO) {
		if (this.viewStartDate == null) {
			this.viewStartDate = DateUtils.getDateBoundDaysToMinimum(DateUtils.getCalendarInstance().getTime());
		}
		if (this.viewEndDate == null) {
			this.viewEndDate = DateUtils.getDateBoundDaysToMaximum(DateUtils.getCalendarInstance().getTime());
		}
		this.listeInformations.clear();
		eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
			if ("information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
				if (eventCalendrierDTO.getEcheance().after(this.viewStartDate)
						&& eventCalendrierDTO.getDebut().before(this.viewEndDate)) {
					this.listeInformations
							.add(CalendrierGestionModelFactory.createEventCalendrierModel(eventCalendrierDTO));
				}
			}
		});
	}

	private void listerEventCalendrierGestion(final List<EventCalendrierDTO> eventsCalendrierDTO) {
		this.scheduleModel.clear();
		eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
			if (!"information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
				this.scheduleModel
						.addEvent(CalendrierGestionModelFactory.createDefaultScheduleEvent(eventCalendrierDTO));
			}
		});
	}

	public void onEventSelect(final SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(final SelectEvent selectEvent) {
		this.event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onViewChange(final SelectEvent selectEvent) {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService());
		listerEventInformations(eventsCalendrierDTO);
	}

	/*
	 * L'AJOUT ET LA MODIFICATION N'ONT PAS ETE DEMANDE EN TANT QUE USER STORY
	 * public void ajouterModifierEvenement(final ActionEvent actionEvent) {
	 * ScheduleEvent eventInitial = new DefaultScheduleEvent(); if
	 * (this.event.getTitle() == null && this.event.getStartDate() == null &&
	 * this.event.getEndDate() == null) {
	 * this.scheduleModel.addEvent(this.event); } else { eventInitial =
	 * this.event; this.scheduleModel.updateEvent(this.event); } final
	 * EventCalendrierDTO eventCalendrierGestionDTO =
	 * CalendrierGestionModelFactory .createEventCalendrierGestionDTO(event);
	 *
	 * eventCalendrierGestionDTO.setEvenement(this.event.getTitle());
	 * eventCalendrierGestionDTO.setType(this.typeNouveauEvenement);
	 * eventCalendrierGestionDTO.setDebut(this.event.getStartDate());
	 * eventCalendrierGestionDTO.setEcheance(this.event.getEndDate());
	 * eventCalendrierGestionDTO.setActeurs(this.acteurNouveauEvenement);
	 * eventCalendrierGestionDTO.setCorps(this.corpsNouveauEvenement);
	 * eventCalendrierGestionDTO.setService(this.serviceNouveauEvenement);
	 * eventCalendrierGestionDTO.setCouleur(this.couleurNouveauEvenement);
	 * eventCalendrierGestionDTO.setCommentaire(this.event.getDescription;
	 *
	 * this.calendrierGestionService.ajouterEvent(eventCalendrierGestionDTO);
	 * this.logger.logAction(Level.INFO,
	 * computeLogActionDTO(IHMUserActionEnum.CREATION,
	 * IHMUserResultEnum.SUCCESS, IHMPageNameEnum.EVENEMENT_CALENDRIER_GESTION,
	 * null, eventInitial, this.event));
	 *
	 * this.event = new DefaultScheduleEvent(); }
	 */

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(final ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public ScheduleEvent getEvent() {
		return this.event;
	}

	public void setEvent(final DefaultScheduleEvent event) {
		this.event = event;
	}

	public List<EventCalendrierModel> getListeInformations() {
		return listeInformations;
	}

	public void setListeInformations(final List<EventCalendrierModel> listeInformations) {
		this.listeInformations = listeInformations;
	}

	public Date getViewStartDate() {
		return viewStartDate;
	}

	public void setViewStartDate(final Date viewStartDate) {
		this.viewStartDate = viewStartDate;
	}

	public Date getViewEndDate() {
		return viewEndDate;
	}

	public void setViewEndDate(final Date viewEndDate) {
		this.viewEndDate = viewEndDate;
	}
}
