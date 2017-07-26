package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.model.HistoriqueModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion de l'historique d'un dossier
 *
 * @author khalil
 */
@ManagedBean(name = ViewConstantes.HISTORIQUE_BEAN)
@RequestScoped
public class HistoriqueBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5322434356464346648L;

	@ManagedProperty("#{" + CoreConstantes.DOSSIER_SERVICE + "}")
	private IDossierService dossierService;

	/**
	 * Historique sélectionné dans le tableau.
	 */
	private HistoriqueModel selectedHistorique;

	/**
	 * Liste de l'historique du dossier.
	 */
	private List<HistoriqueModel> historiques;

	@PostConstruct
	public void setup() {
		// Initialization
		this.historiques = new ArrayList<HistoriqueModel>();

		// Supplier

	}

	public HistoriqueModel getSelectedHistorique() {
		return selectedHistorique;
	}

	public void setSelectedHistorique(final HistoriqueModel selectedHistorique) {
		this.selectedHistorique = selectedHistorique;
	}

	public List<HistoriqueModel> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(final List<HistoriqueModel> historiques) {
		this.historiques = historiques;
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}
}
