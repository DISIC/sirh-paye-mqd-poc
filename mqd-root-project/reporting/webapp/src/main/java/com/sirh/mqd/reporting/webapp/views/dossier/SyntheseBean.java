package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.reporting.core.api.ICommentaireService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.model.SyntheseModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de synthèse Agent d'un dossier
 *
 * @author Maxime
 */
@Named(ViewConstantes.SYNTHESE_BEAN)
@SessionScoped
public class SyntheseBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5230591657668482045L;

	@Inject
	@Qualifier(CoreConstantes.SYNTHESE_SERVICE)
	private ISyntheseService syntheseService;

	/*
	 * Données personnelles
	 */
	private SyntheseModel donneesPersonnelles;

	public void setup() {

		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.donneesPersonnelles = new ArrayList<String>();
		}
	}

	public void alimenterDonneesPersonnelles(final SyntheseModel synteseModel) {
		final DossierDTO donneesPersonnelles = this.dossier
	}

}
