package com.sirh.mqd.reporting.webapp.views.evenement;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.model.EventCalendrierGestionModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue du statut d'un dossier
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

	// @Inject
	// @Qualifier(CoreConstantes.EVENT_CALENDRIER_GESTION)
	// private ICalendrierGestionService calendrierGestionService;

	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	private ScheduleModel scheduleModel;

	private ScheduleEvent event = new DefaultScheduleEvent();

	public void setup() {
		scheduleModel = new DefaultScheduleModel();
		scheduleModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
		scheduleModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
		scheduleModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
		scheduleModel
				.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
	}

	protected void alimenterCalendrierGestion(final EventCalendrierGestionModel eventCalendrierGestionModel) {
		eventCalendrierGestionModel.setEvenement("evenenemnt test");
		eventCalendrierGestionModel.setCorps("corps_T");
		final DefaultScheduleEvent event = new DefaultScheduleEvent();
		event.setStartDate(DateUtils.getCalendarInstance().getTime());
		event.setTitle("Test");
		// this.scheduleModel.addEvent(event);
	}

	public Date getRandomDate(final Date base) {
		final Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public Date getInitialDate() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar.getTime();
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	private Calendar today() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

		return calendar;
	}

	/*
	 * public ScheduleModel getScheduleModel() { return scheduleModel; }
	 *
	 * public void setScheduleModel(final ScheduleModel scheduleModel) {
	 * this.scheduleModel = scheduleModel; }
	 */

	private Date previousDay8Pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 8);

		return t.getTime();
	}

	private Date previousDay11Pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
		t.set(Calendar.HOUR, 11);

		return t.getTime();
	}

	private Date today1Pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 1);

		return t.getTime();
	}

	private Date theDayAfter3Pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 3);

		return t.getTime();
	}

	private Date today6Pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.HOUR, 6);

		return t.getTime();
	}

	private Date nextDay9Am() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 9);

		return t.getTime();
	}

	private Date nextDay11Am() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.AM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
		t.set(Calendar.HOUR, 11);

		return t.getTime();
	}

	private Date fourDaysLater3pm() {
		final Calendar t = (Calendar) today().clone();
		t.set(Calendar.AM_PM, Calendar.PM);
		t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
		t.set(Calendar.HOUR, 3);

		return t.getTime();
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(final ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(final ActionEvent actionEvent) {
		if (event.getId() == null) {
			scheduleModel.addEvent(event);
		} else {
			scheduleModel.updateEvent(event);
		}

		event = new DefaultScheduleEvent();
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
