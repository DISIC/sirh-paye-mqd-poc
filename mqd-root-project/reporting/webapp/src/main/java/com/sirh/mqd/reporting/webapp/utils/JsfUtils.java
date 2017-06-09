package com.sirh.mqd.reporting.webapp.utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.ExceptionTypeEnum;
import com.sirh.mqd.commons.traces.enums.InteractionModuleEnum;
import com.sirh.mqd.commons.traces.enums.InteractionToolEnum;
import com.sirh.mqd.commons.traces.factory.LogTechniqueFactory;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * Classe utilitaire pour JSF.<br/>
 * L'ajout des messages par code est délégué au service de gestion des messages.
 *
 * @author Thales Services
 */
@Component(ContextConstantes.JSF_UTILS)
public class JsfUtils implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 8655311462397794689L;

	/**
	 * Service technique de gestion des messages
	 */
	@Autowired
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle messagesBundle;

	/**
	 * Service technique de gestion des messages
	 */
	@Autowired
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle bundle;

	/**
	 * Logger
	 */
	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Ajout d'un message global au contexte JSF courant par code.
	 * <p>
	 * Le code permet de chercher les deux attributs d'un message JSF :
	 * <ul>
	 * <li>summary : le résumé du message correspond à la valeur de la propriété
	 * <i>code.summary</i>. Par défaut, elle prend la valeur <i>[code]</i>.</li>
	 * <li>detail : le détail du message correspond à la valeur de la propriété
	 * <i>code</i>. Par défaut, voir le comportement du service de gestion des
	 * messages {@link bundle#getMessage}.</li>
	 * </ul>
	 * </p>
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param code
	 *            Propriété associée
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessageByCode(final Severity severity, final String code) {
		final String[] s = getSummaryAndDetailFromCode(severity, code, null);
		getFacesCurrentInstance().addMessage(null, new FacesMessage(severity, s[0], s[1]));
	}

	/**
	 * Ajout d'un message au contexte JSF courant par code.
	 * <p>
	 * Le code permet de chercher les deux attributs d'un message JSF :
	 * <ul>
	 * <li>summary : le résumé du message correspond à la valeur de la propriété
	 * <i>code.summary</i>. Par défaut, elle prend la valeur <i>[code]</i>.</li>
	 * <li>detail : le détail du message correspond à la valeur de la propriété
	 * <i>code</i>. Par défaut, voir le comportement du service de gestion des
	 * messages {@link bundle#getMessage}.</li>
	 * </ul>
	 * </p>
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param code
	 *            Propriété associée
	 * @param clientId
	 *            Identifiant client du composant attaché au message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessageByCode(final Severity severity, final String code, final String clientId) {
		final String[] s = getSummaryAndDetailFromCode(severity, code, null);
		getFacesCurrentInstance().addMessage(clientId, new FacesMessage(severity, s[0], s[1]));
	}

	/**
	 * Ajout d'un message global au contexte JSF courant par code.
	 * <p>
	 * Le code permet de chercher les deux attributs d'un message JSF :
	 * <ul>
	 * <li>summary : le résumé du message correspond à la valeur de la propriété
	 * <i>code.summary</i>. Par défaut, elle prend la valeur <i>[code]</i>.</li>
	 * <li>detail : le détail du message correspond à la valeur de la propriété
	 * <i>code</i>. Par défaut, voir le comportement du service de gestion des
	 * messages {@link bundle#getMessage}.</li>
	 * </ul>
	 * </p>
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param code
	 *            Propriété associée
	 * @param arguments
	 *            Arguments éventuels du message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessageByCode(final Severity severity, final String code, final Object[] arguments) {
		final String[] s = getSummaryAndDetailFromCode(severity, code, arguments);
		getFacesCurrentInstance().addMessage(null, new FacesMessage(severity, s[0], s[1]));
	}

	/**
	 * Ajout d'un message au contexte JSF courant par code.
	 * <p>
	 * Le code permet de chercher les deux attributs d'un message JSF :
	 * <ul>
	 * <li>summary : le résumé du message correspond à la valeur de la propriété
	 * <i>code.summary</i>. Par défaut, elle prend la valeur <i>[code]</i>.</li>
	 * <li>detail : le détail du message correspond à la valeur de la propriété
	 * <i>code</i>. Par défaut, voir le comportement du service de gestion des
	 * messages {@link bundle#getMessage}.</li>
	 * </ul>
	 * </p>
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param code
	 *            Propriété associée
	 * @param arguments
	 *            Arguments éventuels du message
	 * @param clientId
	 *            Identifiant client du composant attaché au message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessageByCode(final Severity severity, final String code, final Object[] arguments,
			final String clientId) {
		final String[] s = getSummaryAndDetailFromCode(severity, code, arguments);
		getFacesCurrentInstance().addMessage(clientId, new FacesMessage(severity, s[0], s[1]));
	}

	/**
	 * Ajout d'un message global d'information (
	 * {@link FacesMessage#SEVERITY_INFO}) sans détail au contexte JSF courant.
	 *
	 * @param summary
	 *            Résumé du message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessage(final String summary) {
		getFacesCurrentInstance().addMessage(null, new FacesMessage(summary));
	}

	/**
	 * Ajout d'un message d'information ({@link FacesMessage#SEVERITY_INFO}) au
	 * contexte JSF courant.
	 *
	 * @param summary
	 *            Résumé du message
	 * @param detail
	 *            Détail du message
	 * @param clientId
	 *            Identifiant client du composant attaché au message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessage(final String summary, final String detail, final String clientId) {
		getFacesCurrentInstance().addMessage(clientId, new FacesMessage(summary, detail));
	}

	/**
	 * Ajout d'un message au contexte JSF courant.
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param summary
	 *            Résumé du message
	 * @param detail
	 *            Détail du message
	 * @param clientId
	 *            Identifiant client du composant attaché au message
	 * @see FacesContext#addMessage(String, FacesMessage)
	 */
	public void addMessage(final Severity severity, final String summary, final String detail, final String clientId) {
		getFacesCurrentInstance().addMessage(clientId, new FacesMessage(severity, summary, detail));
	}

	/**
	 * Construction des attributs JSF du message accédé par code.
	 *
	 * @param severity
	 *            Niveau de sévérité du message
	 * @param code
	 *            La propriété du message
	 * @param arguments
	 *            Les arguments éventuels du message
	 * @return String[] [résumé, détail]
	 */
	private String[] getSummaryAndDetailFromCode(final Severity severity, final String code, final Object[] arguments) {
		final String[] s = new String[2];
		// Résumé
		try {
			s[0] = messagesBundle.getMessageWithError(code + ".summary");
		} catch (final NoSuchMessageException e) {
			s[0] = "[" + code + "]";
		}
		// Détail
		s[1] = messagesBundle.getMessage(code, arguments);

		if (FacesMessage.SEVERITY_ERROR.equals(severity) || FacesMessage.SEVERITY_FATAL.equals(severity)) {
			this.logger.logTechnique(Level.ERROR, LogTechniqueFactory.createLogTechnique(InteractionToolEnum.IHM,
					InteractionModuleEnum.REPORTING, ExceptionTypeEnum.APPLICATION_EXCEPTION, s[1]));
		}

		return s;
	}

	/**
	 * Méthode permettant de retourner le context courant de faces.
	 *
	 * @return le context courant de faces.
	 */
	private FacesContext getFacesCurrentInstance() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Méthode permettant de récupérer un Bean existant en session ou dans le
	 * scope de l'application
	 *
	 * @param beanName
	 *            le nom du Bean
	 * @param className
	 *            la classe Java du Bean qui doit hériter de la classe
	 *            GenericBean
	 * @return le bean attendu ou null s'il n'existe pas dans le contexte JSF
	 */
	public <T extends GenericBean> T getBean(final String beanName, final Class<T> className) {
		final FacesContext facesContext = getFacesCurrentInstance();
		if (facesContext != null) {
			return facesContext.getApplication().evaluateExpressionGet(facesContext, "#{" + beanName + "}", className);
		}
		return null;
	}
}
