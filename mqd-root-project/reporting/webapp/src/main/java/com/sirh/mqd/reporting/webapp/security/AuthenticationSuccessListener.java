package com.sirh.mqd.reporting.webapp.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.model.UserModel;

@Component(ContextConstantes.AUTHENTIFICATION_SUCCESS_LISTERNER)
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	@Qualifier(CoreConstantes.USER_SERVICE)
	private IUserService userService;

	@Override
	public void onApplicationEvent(final AuthenticationSuccessEvent event) {
		final Authentication authentication = event.getAuthentication();
		if (authentication != null) {
			final UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
			if (authenticatedUser != null) {
				final Date connexion = DateUtils.getCalendarInstance().getTime();
				userService.modifierDateDerniereConnexion(authenticatedUser.getUsername(), connexion);
				authenticatedUser.setDateAuthentification(connexion);
			}
		}
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}
}
