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
import org.primefaces.model.timeline.TimelineModel;
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

	/**
	 * Date début limite affichable
	 */
	private Date limitStartDate;

	/**
	 * Date de fin limite affichable
	 */
	private Date limitEndDate;

	/**
	 * Date début du calendrier actuellement affichée
	 */
	private Date viewStartDate;

	/**
	 * Date de fin du calendrier actuellement affichée
	 */
	private Date viewEndDate;

	/**
	 * Timeline qui contiendra les events
	 */
	private TimelineModel timelineModel;

	/**
	 * Méthode permettant de lister tous les evenements du calendrier gestion
	 * avec une limitation dans le temps :
	 * <ul>
	 * <li>date de début : 1 mois avant la date actuelle (tout le mois compris)
	 * </li>
	 * <li>date de fin : 6 mois après la date actuelle (tout le dernier mois
	 * compris)</li>
	 * </ul>
	 */
	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.listeInformations = new ArrayList<EventCalendrierModel>();
			this.timelineModel = new TimelineModel();
			this.scheduleModel = new DefaultScheduleModel();
			this.event = new DefaultScheduleEvent();
			final Date now = DateUtils.getCalendarInstance().getTime();
			this.limitStartDate = DateUtils.addMonthsWithBounds(now, -1);
			this.limitEndDate = DateUtils.addMonthsWithBounds(now, 6);
			listerEvents();
		}
	}

	private void listerEvents() {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService(),
						this.limitStartDate, this.limitEndDate);
		listerEventInformations(eventsCalendrierDTO);
		listerEventTimeline(eventsCalendrierDTO);
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

	private void listerEventTimeline(final List<EventCalendrierDTO> eventsCalendrierDTO) {
		this.timelineModel.clear();
		eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
			if (!"information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
				this.timelineModel.add(CalendrierGestionModelFactory.createDefaultTimelineEvent(eventCalendrierDTO));
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
		this.event = new DefaultScheduleEvent(StringUtils.EMPTY, (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public void onViewChange(final SelectEvent selectEvent) {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService(),
						this.limitStartDate, this.limitEndDate);
		listerEventInformations(eventsCalendrierDTO);
	}

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

	public Date getLimitStartDate() {
		return limitStartDate;
	}

	public void setLimitStartDate(final Date limitStartDate) {
		this.limitStartDate = limitStartDate;
	}

	public Date getLimitEndDate() {
		return limitEndDate;
	}

	public void setLimitEndDate(final Date limitEndDate) {
		this.limitEndDate = limitEndDate;
	}

	public TimelineModel getTimelineModel() {
		return timelineModel;
	}

	public void setTimelineModel(final TimelineModel timelineModel) {
		this.timelineModel = timelineModel;
	}
}
