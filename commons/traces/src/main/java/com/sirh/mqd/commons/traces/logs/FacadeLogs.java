package com.sirh.mqd.commons.traces.logs;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.resource.PropertiesUtils;
import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogExceptionDTO;
import com.sirh.mqd.commons.traces.dto.LogMetierDTO;
import com.sirh.mqd.commons.traces.dto.LogPerformanceDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.dto.LogWorkflowDTO;
import com.sirh.mqd.commons.traces.dto.LogsMetierDTO;
import com.sirh.mqd.commons.traces.enums.ExceptionType;
import com.sirh.mqd.commons.traces.enums.LogType;

/**
 * Façade pour les logs. C'est un Singleton.
 *
 * @author alexandre
 */
@Component(ConstantesTraces.FACADE_LOGS)
public class FacadeLogs implements IFacadeLogs {

	/**
	 * Logger pour les logs métier success
	 */
	private final Logger logSiriSuccess;

	/**
	 * Logger pour les logs métier error
	 */
	private final Logger logSiriError;

	/**
	 * Logger pour les logs techniques haut-niveau
	 */
	private final Logger logTechnique;

	/**
	 * Logger pour les logs Debug
	 */
	private final Logger logWorkflowInfo;

	/**
	 * Logger pour les logs définissant une étape de workflow applicative
	 */
	private final Logger logWorkflowDebug;

	/**
	 * Logger pour les logs définissant une action IHM
	 */
	private final Logger logAction;

	/**
	 * Logger pour les logs définissant les performances
	 */
	private final Logger logPerf;

	/**
	 * Nom de la classe source où le logger est utilisé
	 */
	private String srcClassName;

	private AsyncTaskExecutor logTask;

	/**
	 * Constructeur
	 */
	public FacadeLogs() {
		this.logAction = LoggerFactory.getLogger("com.sirh.mqd.log.system.action");
		this.logTechnique = LoggerFactory.getLogger("com.sirh.mqd.log.system.error");
		this.logWorkflowDebug = LoggerFactory.getLogger("com.sirh.mqd.log.system.workflow");
		this.logWorkflowInfo = LoggerFactory.getLogger("com.sirh.mqd.log.system.debug");
		this.logPerf = LoggerFactory.getLogger("com.sirh.mqd.log.system.performance");
		String applicationName;
		try {
			final Properties properties = PropertiesUtils.loadFromClasspath("properties/constantes.properties");
			applicationName = properties.getProperty("log.application.name", FacadeLogs.class.getName());
		} catch (final IOException e) {
			applicationName = FacadeLogs.class.getName();
			logException(LogExceptionFactory.getLogException(LogType.OTHER, this.getClass().getName(),
					ExceptionType.GLOBAL_EXCEPTION,
					"File \"constantes.properties\" not found. Couldn't get application name. Default value is "
							+ applicationName + "- " + FacadeLogs.getTraceString(e)));

		}
		this.logSiriSuccess = LoggerFactory.getLogger("com.sirh.mqd.log.metier." + applicationName + ".siri");
		this.logSiriError = LoggerFactory.getLogger("com.sirh.mqd.log.metier." + applicationName + ".error");
	}

	/**
	 * Constructeur
	 *
	 * @param srcClass
	 *            Classe où le logger est utilisé
	 */
	public FacadeLogs(final Class<?> srcClass) {
		this();
		this.srcClassName = srcClass.getName();
	}

	/**
	 * Constructeur
	 *
	 * @param srcClass
	 *            Nom de la classe où le logger est utilisé
	 */
	public FacadeLogs(final String srcClass) {
		this();
		this.srcClassName = srcClass;
	}

	public static String getTraceString(final Throwable exceptionLevee) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		exceptionLevee.printStackTrace(pw);
		return sw.toString();

	}

	public static String getComposant(final String srcClassName) {
		String composant = "null";
		if (srcClassName != null) {
			if (srcClassName.contains("reception")) {
				composant = "ACQUISITION";
			} else if (srcClassName.contains("emission")) {
				composant = "EMISSION";
			} else if (srcClassName.contains("administration")) {
				composant = "ORCHESTRATION";
			} else if (srcClassName.contains("reporting")) {
				composant = "REPORTING";
			}
		}
		return composant;
	}

	@Override
	public void logException(final LogExceptionDTO logExceptionDTO) {
		new LogThread(() -> {
			logTechnique.error(logExceptionDTO.getLogTechniqueDTO().toString());
			logWorkflowInfo.debug(logExceptionDTO.getLogWorkflowDTO().toString());

		}).start(logTask);

	}

	@Override
	public void logPerformance(final LogPerformanceDTO logPerformanceDTO) {
		new LogThread(() -> logPerf.info(logPerformanceDTO.toString())).start(logTask);
	}

	@Override
	public void logTechniqueError(final LogTechniqueDTO logTechniqueDTO) {
		new LogThread(() -> logTechnique.error(logTechniqueDTO.toString())).start(logTask);

	}

	@Override
	public void logTechniqueWarn(final LogTechniqueDTO logTechniqueDTO) {
		new LogThread(() -> logTechnique.warn(logTechniqueDTO.toString())).start(logTask);

	}

	@Override
	public void logsMetierSuccess(final LogsMetierDTO logsMetier) {
		new LogThread(() -> {
			for (final LogMetierDTO logMetier : logsMetier) {
				logSiriSuccess.info(logMetier.toString());
			}
		}).start(logTask);
	}

	@Override
	public void logsMetierError(final LogsMetierDTO logsMetier) {
		new LogThread(() -> {
			for (final LogMetierDTO logMetier : logsMetier) {
				logSiriError.error(logMetier.toString());
			}
		}).start(logTask);
	}

	@Override
	public void logWorkflowInfo(final LogWorkflowDTO logWorkflowDTO) {
		new LogThread(() -> logWorkflowInfo.info(logWorkflowDTO.toString())).start(logTask);
	}

	@Override
	public void logWorkflowDebug(final LogWorkflowDTO logWorkflowDTO) {
		new LogThread(() -> logWorkflowDebug.debug(logWorkflowDTO.toString())).start(logTask);
	}

	@Override
	public void logAction(final LogActionDTO logActionDTO) {
		new LogThread(() -> logAction.info(logActionDTO.toString())).start(logTask);
	}

	public AsyncTaskExecutor getLogTask() {
		return logTask;
	}

	public void setLogTask(final AsyncTaskExecutor logTask) {
		this.logTask = logTask;
	}
}