package com.sirh.mqd.reporting.webapp.views.validators;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ValidatorConstantes;
import com.sirh.mqd.reporting.webapp.utils.JsfUtils;

@Named(ValidatorConstantes.REQUIRED_VALIDATOR)
@RequestScoped
public class RequiredValidator implements Validator<Object> {

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Inject
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
}
