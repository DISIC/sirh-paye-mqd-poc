package com.sirh.mqd.reporting.webapp.views.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ValidatorConstantes;
import com.sirh.mqd.reporting.webapp.utils.JsfUtils;

@Component(ValidatorConstantes.USERNAME_VALIDATOR)
@RequestScope
public class UsernameValidator implements Validator {

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Autowired
	@Qualifier(ContextConstantes.JSF_UTILS)
	protected JsfUtils jsfUtils;

	@Autowired
	@Qualifier(CoreConstantes.USER_SERVICE)
	private IUserService userService;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException {
		final String inputtext = value.toString();
		if (StringUtils.isBlank(inputtext)) {
			context.validationFailed();
			this.jsfUtils.throwValidationExceptionByCode(FacesMessage.SEVERITY_ERROR,
					"error.functional.mandatory.field");
		} else if (!this.userService.rechercherUtilisateur(inputtext).isPresent()) {
			context.validationFailed();
			this.jsfUtils.throwValidationExceptionByCode(FacesMessage.SEVERITY_ERROR,
					"security.error.functional.no.username.found", inputtext);
		}
	}

	public JsfUtils getJsfUtils() {
		return jsfUtils;
	}

	public void setJsfUtils(final JsfUtils jsfUtils) {
		this.jsfUtils = jsfUtils;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}
}
