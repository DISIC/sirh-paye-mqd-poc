package com.sirh.mqd.reporting.webapp.views.evenement;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.reporting.core.api.ICalendrierGestionService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.CalendrierGestionModelFactory;
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
	 * Attributs de l'Event à créer/modifier non présent parmi les attributs de
	 * la classe DefaultScheduleEvent
	 */
	private String typeNouveauEvenement;

	private String acteurNouveauEvenement;

	private String corpsNouveauEvenement;

	private String serviceNouveauEvenement;

	private String couleurNouveauEvenement;

	/**
	 *
	 */
	private ScheduleEvent event = new DefaultScheduleEvent();

	public void setup() {
		// Initialization
		this.scheduleModel = new DefaultScheduleModel();

		// Supplier
		listerEventCalendrierGestion();
	}

	private void listerEventCalendrierGestion() {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService());
		eventsCalendrierDTO.forEach(eventCalendrierDTO -> this.scheduleModel
				.addEvent(CalendrierGestionModelFactory.createEventCalendrierModel(eventCalendrierDTO)));
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

	public String getTypeNouveauEvenement() {
		return typeNouveauEvenement;
	}

	public void setTypeNouveauEvenement(final String typeNouveauEvenement) {
		this.typeNouveauEvenement = typeNouveauEvenement;
	}

	public String getActeurNouveauEvenement() {
		return acteurNouveauEvenement;
	}

	public void setActeurNouveauEvenement(final String acteurNouveauEvenement) {
		this.acteurNouveauEvenement = acteurNouveauEvenement;
	}

	public String getCorpsNouveauEvenement() {
		return corpsNouveauEvenement;
	}

	public void setCorpsNouveauEvenement(final String corpsNouveauEvenement) {
		this.corpsNouveauEvenement = corpsNouveauEvenement;
	}

	public String getServiceNouveauEvenement() {
		return serviceNouveauEvenement;
	}

	public void setServiceNouveauEvenement(final String serviceNouveauEvenement) {
		this.serviceNouveauEvenement = serviceNouveauEvenement;
	}

	public String getCouleurNouveauEvenement() {
		return couleurNouveauEvenement;
	}

	public void setCouleurNouveauEvenement(final String couleurNouveauEvenement) {
		this.couleurNouveauEvenement = couleurNouveauEvenement;
	}

	public void onEventSelect(final SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(final SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	}

	public void onEventMove(final ScheduleEntryMoveEvent event) {
		final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(final ScheduleEntryResizeEvent event) {
		final FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(final FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
