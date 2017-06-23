package com.sirh.mqd.reporting.webapp.views.evenement;

import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
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

	private ScheduleModel eventModel;

	public void setup() {
		eventModel = new DefaultScheduleModel();
	}

	public Date getRandomDate(final Date base) {
		final Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(final ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

}
