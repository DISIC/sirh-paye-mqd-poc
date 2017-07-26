package com.sirh.mqd.reporting.webapp.views.dossier;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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
 * @author maxime
 */
@ManagedBean(name = ViewConstantes.SYNTHESE_BEAN)
@RequestScoped
public class SyntheseBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5326399354586705300L;

	// @ManagedProperty("#{" + CoreConstantes.SYNTHESE_SERVICE + "}")
	// private ISyntheseService syntheseService;

	@ManagedProperty("#{" + ConstantesTraces.FACADE_LOGS + "}")
	private IFacadeLogs logger;

	/*
	 * Données personnelles
	 */
	private SyntheseModel syntheseModel;

	@PostConstruct
	public void setup() {
		// Initialization

		// Supplier
		alimenterSyntheseDossier(getCurrentDossier());
	}

	public void alimenterSyntheseDossier(final DossierModel selectedDossier) {
		if (selectedDossier != null) {
			final DossierDTO dossierDTO = DossierModelFactory.createDossierDTO(selectedDossier);
			this.syntheseModel = SyntheseModelFactory.createSyntheseModel(dossierDTO);
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

	public IFacadeLogs getLogger() {
		return logger;
	}

	public void setLogger(final IFacadeLogs logger) {
		this.logger = logger;
	}
}
