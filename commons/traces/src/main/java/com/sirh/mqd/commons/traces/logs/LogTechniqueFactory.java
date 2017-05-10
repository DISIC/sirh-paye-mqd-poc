package com.sirh.mqd.commons.traces.logs;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.enums.ExceptionTypeEnum;
import com.sirh.mqd.commons.traces.enums.InteractionModuleEnum;
import com.sirh.mqd.commons.traces.enums.InteractionToolEnum;

/**
 * Factory de création des messages d'erreur technique
 *
 * @author alexandre
 */
public final class LogTechniqueFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private LogTechniqueFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + LogTechniqueFactory.class.getName());
	}

	/**
	 * Méthode permettant de créer un objet {@link LogTechniqueDTO} utilisé pour
	 * tracer les logs techniques.<br/>
	 * Le contenu du message doit être un message technique customisé pour cette
	 * méthode.
	 *
	 * @param technologie
	 * @param composant
	 * @param srcClassName
	 * @param exception
	 * @param content
	 * @return {@link LogTechniqueDTO} le DTO complété du log technique
	 */
	public static LogTechniqueDTO createLogTechnique(final InteractionToolEnum technologie,
			final InteractionModuleEnum composant, final String srcClassName, final ExceptionTypeEnum exception,
			final String content) {
		final LogTechniqueDTO logTechnique = new LogTechniqueDTO();
		logTechnique.setTechnologie(technologie);
		logTechnique.setComposant(composant);
		logTechnique.setSrcClassName(srcClassName);
		logTechnique.setException(exception);
		logTechnique.setContent(content);
		return logTechnique;
	}

	/**
	 * Méthode permettant de créer un objet {@link LogTechniqueDTO} utilisé pour
	 * tracer les logs techniques.<br/>
	 * Le contenu du message doit être un exception levée pour cette méthode.
	 *
	 * @param technologie
	 * @param composant
	 * @param srcClassName
	 * @param exception
	 * @param content
	 * @return {@link LogTechniqueDTO} le DTO complété du log technique
	 */
	public static LogTechniqueDTO createLogTechnique(final InteractionToolEnum technologie,
			final InteractionModuleEnum composant, final String srcClassName, final ExceptionTypeEnum exception,
			final Throwable content) {
		final LogTechniqueDTO logTechnique = new LogTechniqueDTO();
		logTechnique.setTechnologie(technologie);
		logTechnique.setComposant(composant);
		logTechnique.setSrcClassName(srcClassName);
		logTechnique.setException(exception);
		if (content != null) {
			logTechnique.setContent(getExceptionContent(content));
		}
		return logTechnique;
	}

	/**
	 * Méthode permettant de construire des messages d'exception lisibles
	 *
	 * @param exception
	 *            l'exception levée
	 * @return String l'exception au format lisible
	 */
	private static String getExceptionContent(final Throwable exception) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
}
