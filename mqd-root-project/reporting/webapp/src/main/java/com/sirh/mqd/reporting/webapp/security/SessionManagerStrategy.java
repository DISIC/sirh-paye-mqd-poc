package com.sirh.mqd.reporting.webapp.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.ExceptionTypeEnum;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.traces.enums.InteractionModuleEnum;
import com.sirh.mqd.commons.traces.enums.InteractionToolEnum;
import com.sirh.mqd.commons.traces.factory.LogActionFactory;
import com.sirh.mqd.commons.traces.factory.LogTechniqueFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.model.UserModel;

public class SessionManagerStrategy extends ConcurrentSessionControlAuthenticationStrategy {

	/**
	 * Logger
	 */
	@Autowired
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	@Autowired
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle messagesBundle;

	public SessionManagerStrategy(final SessionRegistry sessionRegistry) {
		super(sessionRegistry);
	}

	@Override
	public void onAuthentication(final Authentication authentication, final HttpServletRequest request,
			final HttpServletResponse response) {
		try {
			super.onAuthentication(authentication, request, response);
		} catch (final SessionAuthenticationException e) {
			this.logger.logTechnique(Level.WARN, LogTechniqueFactory.createLogTechnique(InteractionToolEnum.IHM,
					InteractionModuleEnum.REPORTING, ExceptionTypeEnum.SECURITY_EXCEPTION, e));
			String username = "UNKNOWN USERNAME";
			String roles = "UNKNOWN ROLES";
			Date authenticationDate = DateUtils.getCalendarInstance().getTime();
			if (authentication != null) {
				final UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
				if (authenticatedUser != null) {
					username = authenticatedUser.getUsername();
					roles = authenticatedUser.getRolesAsString();
					if (authenticatedUser.getDateAuthentification() != null) {
						authenticationDate = DateUtils.clonerDate(authenticatedUser.getDateAuthentification());
					}
				}
			}
			this.logger.logAction(Level.WARN, LogActionFactory.createLogAction(username, roles, authenticationDate,
					IHMUserActionEnum.AUTHENTIFICATION,
					IHMUserResultEnum.AUTHENTIFICATION_FAILURE, IHMPageNameEnum.AUTHENTIFICATION, messagesBundle
							.getMessage("security.warn.functional.user.session.expired", new Object[] { username }),
					null));
			throw e;
		}
	}

	public IFacadeLogs getLogger() {
		return logger;
	}

	public void setLogger(final IFacadeLogs logger) {
		this.logger = logger;
	}

	public IMessageSourceBundle getMessagesBundle() {
		return messagesBundle;
	}

	public void setMessagesBundle(final IMessageSourceBundle messagesBundle) {
		this.messagesBundle = messagesBundle;
	}
}
