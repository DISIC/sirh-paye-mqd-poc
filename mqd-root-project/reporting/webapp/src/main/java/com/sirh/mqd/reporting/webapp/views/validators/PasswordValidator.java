package com.sirh.mqd.reporting.webapp.views.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ValidatorConstantes;
import com.sirh.mqd.reporting.webapp.utils.JsfUtils;

@Component(ValidatorConstantes.PASSWORD_VALIDATOR)
@RequestScope
public class PasswordValidator implements Validator {

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Autowired
	@Qualifier(ContextConstantes.JSF_UTILS)
	protected JsfUtils jsfUtils;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException {
		final UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmPassword");
		final String confirmPassword = uiInputConfirmPassword.getSubmittedValue().toString();
		if (!StringUtils.equals(value.toString(), confirmPassword)) {
			context.validationFailed();
			uiInputConfirmPassword.setValid(Boolean.FALSE);
			this.jsfUtils.throwValidationExceptionByCode(FacesMessage.SEVERITY_ERROR,
					"view.login.erreur.passwords.not.matching");
		}
	}

	public JsfUtils getJsfUtils() {
		return jsfUtils;
	}

	public void setJsfUtils(final JsfUtils jsfUtils) {
		this.jsfUtils = jsfUtils;
	}
}
