package com.sirh.mqd.reporting.routing.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.ExportService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

//
/**
 * Bean permettant la réponse à un GET restfull d'une demande de log d'echange http://localhost:8480/reporting/log/echange
 */
@ControllerAdvice
@RestController
public class ExportBean {

	/**
	 * Service de Vérification de l'Etat du Système
	 */
	@Autowired
	@Qualifier(CoreConstantes.EXPORT_SERVICE)
	private ExportService exportService;

	@RequestMapping("/echange/{from}")
	public String getTextFile(@RequestParam("from") final Date from) {

		return exportService.getTextFile(from, DateUtils.getCalendarInstance().getTime());

	}

	@RequestMapping("/echange")
	public String getTextFile() {

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, -7);
		return exportService.getTextFile(null, null);

	}

	@RequestMapping("/echange/{from}/{to}")
	public String getTextFile(@RequestParam("from") final Date from, @RequestParam("to") final Date to) {

		return exportService.getTextFile(from, to);

	}
}
