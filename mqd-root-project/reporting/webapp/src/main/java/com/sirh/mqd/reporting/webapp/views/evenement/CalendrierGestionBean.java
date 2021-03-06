package com.sirh.mqd.reporting.webapp.views.evenement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

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
@ManagedBean(name = ViewConstantes.CALENDRIER_GESTION_BEAN)
@SessionScoped
public class CalendrierGestionBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6838265929563237041L;

	@ManagedProperty("#{" + ConstantesTraces.FACADE_LOGS + "}")
	private IFacadeLogs logger;

	@ManagedProperty("#{" + CoreConstantes.CALENDRIER_GESTION_SERVICE + "}")
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
	 * Evénement sélectionné du timeline
	 */
	private TimelineEvent selectedTimelineEvent;

	/**
	 * Liste des couleurs sur lesquels on peut filtrer
	 */
	private List<String> listeFiltreCorps;

	/**
	 * Liste des couleurs sur lesquels on peut filtrer
	 */
	private List<String> listeFiltreType;

	/**
	 * Liste des couleurs sur lesquels on peut filtrer
	 */
	private List<String> listeFiltreCouleur;

	/**
	 * Corps selectionné comme filtre
	 */
	private String selectedCorps;

	/**
	 * Type selectionné comme filtre
	 */
	private String selectedType;

	/**
	 * Couleur selectionnee comme filtre
	 */
	private String selectedCouleur;

	/**
	 * Affichage des événements de type "informations"
	 */
	private boolean informationsDisplayable;

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
	@PostConstruct
	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.listeFiltreCouleur = new ArrayList<String>();
			this.listeFiltreCouleur.addAll(this.calendrierGestionService.listerCouleursEvents());
			this.listeFiltreCouleur.sort((couleur1, couleur2) -> couleur1.compareTo(couleur2));
			this.listeFiltreCorps = new ArrayList<String>();
			this.listeFiltreCorps.addAll(this.calendrierGestionService.listerCorpsEvents());
			this.listeFiltreCorps.sort((corps1, corps2) -> corps1.compareTo(corps2));
			this.listeFiltreType = new ArrayList<String>();
			this.listeFiltreType.addAll(this.calendrierGestionService.listerTypesEvents());
			this.listeFiltreType.sort((type1, type2) -> type1.compareTo(type2));

			this.informationsDisplayable = Boolean.TRUE;
			this.listeInformations = new ArrayList<EventCalendrierModel>();
			this.timelineModel = new TimelineModel();
			this.scheduleModel = new LazyScheduleModel() {
				/**
				 * Generated UID
				 */
				private static final long serialVersionUID = -1712833719075081547L;

				@Override
				public void loadEvents(final Date start, final Date end) {
					final List<EventCalendrierDTO> eventsCalendrierDTO = calendrierGestionService
							.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService(),
									start, end, selectedType, selectedCouleur, selectedCorps);

					this.clear();

					eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
						if (!"information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
							this.addEvent(CalendrierGestionModelFactory.createDefaultScheduleEvent(eventCalendrierDTO));
						}
					});
				}
			};
			this.event = new DefaultScheduleEvent();
			final Date now = DateUtils.getCalendarInstance().getTime();
			this.limitStartDate = DateUtils.addMonthsWithBounds(now, -1);
			this.limitEndDate = DateUtils.addMonthsWithBounds(now, 6);
			listerEvents();
		}
	}

	public void listerEvents() {
		listerEventTimeline();
		listerEventInformations();
	}

	private void listerEventInformations() {
		this.listeInformations.clear();
		if (this.informationsDisplayable) {
			if (this.viewStartDate == null) {
				this.viewStartDate = DateUtils.getDateBoundDaysToMinimum(DateUtils.getCalendarInstance().getTime());
			}
			if (this.viewEndDate == null) {
				this.viewEndDate = DateUtils.getDateBoundDaysToMaximum(DateUtils.getCalendarInstance().getTime());
			}

			final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
					.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService(),
							this.viewStartDate, this.viewEndDate, this.selectedType, this.selectedCouleur,
							this.selectedCorps);

			eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
				if ("information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
					this.listeInformations
							.add(CalendrierGestionModelFactory.createEventCalendrierModel(eventCalendrierDTO));
				}
			});
		}
	}

	private void listerEventTimeline() {
		final List<EventCalendrierDTO> eventsCalendrierDTO = this.calendrierGestionService
				.listerEventsAvecBornesTemporelles(getCurrentUserMinistere(), getCurrentUserService(),
						this.limitStartDate, this.limitEndDate, this.selectedType, this.selectedCouleur,
						this.selectedCorps);
		this.timelineModel.clear();
		eventsCalendrierDTO.forEach(eventCalendrierDTO -> {
			if (!"information".equals(StringUtils.normalizeSpace(eventCalendrierDTO.getType()))) {
				this.timelineModel.add(CalendrierGestionModelFactory.createDefaultTimelineEvent(eventCalendrierDTO));
			}
		});
	}

	public void onViewChange(final SelectEvent selectEvent) {
		listerEventInformations();
	}

	public void onEventSelect(final SelectEvent selectEvent) {
		this.event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onSelectTimelineEvent(final TimelineSelectEvent selectEvent) {
		this.selectedTimelineEvent = selectEvent.getTimelineEvent();
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

	public void setEvent(final ScheduleEvent event) {
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

	public TimelineEvent getSelectedTimelineEvent() {
		return selectedTimelineEvent;
	}

	public void setSelectedTimelineEvent(final TimelineEvent selectedTimelineEvent) {
		this.selectedTimelineEvent = selectedTimelineEvent;
	}

	public List<String> getListeFiltreCorps() {
		return listeFiltreCorps;
	}

	public void setListeFiltreCorps(final List<String> listeFiltreCorps) {
		this.listeFiltreCorps = listeFiltreCorps;
	}

	public List<String> getListeFiltreType() {
		return listeFiltreType;
	}

	public void setListeFiltreType(final List<String> listeFiltreType) {
		this.listeFiltreType = listeFiltreType;
	}

	public List<String> getListeFiltreCouleur() {
		return listeFiltreCouleur;
	}

	public void setListeFiltreCouleur(final List<String> listeFiltreCouleur) {
		this.listeFiltreCouleur = listeFiltreCouleur;
	}

	public String getSelectedCouleur() {
		return selectedCouleur;
	}

	public void setSelectedCouleur(final String selectedCouleur) {
		this.selectedCouleur = selectedCouleur;
	}

	public String getSelectedCorps() {
		return selectedCorps;
	}

	public void setSelectedCorps(final String selectedCorps) {
		this.selectedCorps = selectedCorps;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(final String selectedType) {
		this.selectedType = selectedType;
	}

	public boolean isInformationsDisplayable() {
		return informationsDisplayable;
	}

	public void setInformationsDisplayable(final boolean informationsDisplayable) {
		this.informationsDisplayable = informationsDisplayable;
	}

	public IFacadeLogs getLogger() {
		return logger;
	}

	public void setLogger(final IFacadeLogs logger) {
		this.logger = logger;
	}

	public ICalendrierGestionService getCalendrierGestionService() {
		return calendrierGestionService;
	}

	public void setCalendrierGestionService(final ICalendrierGestionService calendrierGestionService) {
		this.calendrierGestionService = calendrierGestionService;
	}
}
