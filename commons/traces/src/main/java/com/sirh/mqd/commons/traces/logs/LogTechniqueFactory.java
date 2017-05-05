package com.sirh.mqd.commons.traces.logs;

import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.enums.LogType;

/**
 * Factory qui permet de créer un objet LogTechniqueDTO
 *
 * @author Ahmed
 *
 */
public class LogTechniqueFactory {

	/**
	 * créer un objet LogTechniqueDTO utlisé pour tracer les logs techniques
	 *
	 * @param date
	 * @param logType
	 * @param composant
	 * @param srcClassName
	 * @param name
	 * @param content
	 * @return logTechniqueDTO
	 */
	public static LogTechniqueDTO getLogTechnique(final Date date, final LogType logType, final String srcClassName, final String name,
			final String content) {
		return new LogTechniqueDTO(date, logType, FacadeLogs.getComposant(srcClassName), srcClassName, name, content);
	}

}
