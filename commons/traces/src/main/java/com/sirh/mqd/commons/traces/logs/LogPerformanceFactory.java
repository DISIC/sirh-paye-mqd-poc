package com.sirh.mqd.commons.traces.logs;

import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogPerformanceDTO;

public class LogPerformanceFactory {

	/**
	 * créer un objet LogPerformanceDTO utlisé pour tracer les logs de performances
	 * 
	 * @param date
	 * @param composant
	 * @param service
	 * @param start
	 * @param end
	 * @param identifiant
	 * @return
	 */
	public static LogPerformanceDTO getLogPerformance(final Date date, final String composant, final String service, final long start,
			final long end, final String identifiant) {
		return new LogPerformanceDTO(date, composant, service, start, end, identifiant);
	}
}
