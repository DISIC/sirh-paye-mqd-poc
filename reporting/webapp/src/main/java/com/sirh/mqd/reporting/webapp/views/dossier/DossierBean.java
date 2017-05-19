package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.DossierModelFactory;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion des dossiers rattachés à un utilisateur
 *
 * @author alexandre
 */
@Named(ViewConstantes.DOSSIER_BEAN)
@SessionScoped
public class DossierBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 555762255490874333L;

	@Inject
	@Qualifier(CoreConstantes.DOSSIER_SERVICE)
	private IDossierService dossierService;

	/**
	 * Identifiant unique du dossier sélectionnée en amont.
	 */
	private DossierModel selectedDossier;

	/**
	 * Liste des dossiers existantes.
	 */
	private List<DossierModel> dossiers;

	public void setup() {
		// Initialization
		this.dossiers = new ArrayList<DossierModel>();

		// Supplier
		final List<DossierDTO> dossiers = this.dossierService.listerDossiers(this.getCurrentUsername());
		for (final DossierDTO dossier : dossiers) {
			this.dossiers.add(DossierModelFactory.createDossier(dossier));
		}
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public DossierModel getSelectedDossier() {
		return selectedDossier;
	}

	public void setSelectedDossier(final DossierModel selectedDossier) {
		this.selectedDossier = selectedDossier;
	}

	public List<DossierModel> getDossiers() {
		return dossiers;
	}

	public void setDossiers(final List<DossierModel> dossiers) {
		this.dossiers = dossiers;
	}
}
