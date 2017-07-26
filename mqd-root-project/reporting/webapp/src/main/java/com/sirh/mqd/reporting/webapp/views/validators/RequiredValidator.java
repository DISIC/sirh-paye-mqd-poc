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

import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ValidatorConstantes;
import com.sirh.mqd.reporting.webapp.utils.JsfUtils;

@Component(ValidatorConstantes.REQUIRED_VALIDATOR)
@RequestScope
public class RequiredValidator implements Validator {

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Autowired
	@Qualifier(ContextConstantes.JSF_UTILS)
	protected JsfUtils jsfUtils;

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
			throws ValidatorException {
		if (StringUtils.isBlank(value.toString())) {
			context.validationFailed();
			this.jsfUtils.throwValidationExceptionByCode(FacesMessage.SEVERITY_ERROR,
					"error.functional.mandatory.field");
		}
	}

	public JsfUtils getJsfUtils() {
		return jsfUtils;
	}

	public void setJsfUtils(final JsfUtils jsfUtils) {
		this.jsfUtils = jsfUtils;
	}
}
