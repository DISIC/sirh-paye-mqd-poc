package com.sirh.mqd.reporting.webapp.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.resources.LabelSourceBundle;
import com.sirh.mqd.reporting.webapp.resources.TitreSourceBundle;

@Named
@RequestScoped
public class BundleUtils {

	@Produces
	public LabelSourceBundle getLabelsBundle() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{" + ContextConstantes.LABEL + "}",
				LabelSourceBundle.class);
	}

	@Produces
	public TitreSourceBundle getTitresBundle() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, "#{" + ContextConstantes.TITRE + "}",
				TitreSourceBundle.class);
	}
}
