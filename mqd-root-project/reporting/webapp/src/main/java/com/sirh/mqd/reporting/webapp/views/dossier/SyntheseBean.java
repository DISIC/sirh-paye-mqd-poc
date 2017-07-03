package com.sirh.mqd.reporting.webapp.views.dossier;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.DossierModelFactory;
import com.sirh.mqd.reporting.webapp.factory.SyntheseModelFactory;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
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

	// @Inject
	// @Qualifier(CoreConstantes.SYNTHESE_SERVICE)
	// private ISyntheseService syntheseService;

	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/*
	 * Données personnelles
	 */
	private SyntheseModel syntheseModel;

	public void setup() {

		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
		}
	}

	public void alimenterSyntheseDossier(final DossierModel selectedDossier) {
		if (selectedDossier != null) {
			final DossierDTO dossierDTO = DossierModelFactory.createDossierDTO(selectedDossier);
			setSyntheseModel(SyntheseModelFactory.createSyntheseModel(dossierDTO));
		} else {
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.synthese.erreur.no.dossier.selected");
		}

	}

	public SyntheseModel getSyntheseModel() {
		return syntheseModel;
	}

	public void setSyntheseModel(final SyntheseModel syntheseModel) {
		this.syntheseModel = syntheseModel;
	}

}
