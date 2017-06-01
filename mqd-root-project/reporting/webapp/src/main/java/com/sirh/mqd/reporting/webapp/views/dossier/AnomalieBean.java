package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.AnomalieModelFactory;
import com.sirh.mqd.reporting.webapp.model.AnomalieModel;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion des anomalies d'un dossier
 *
 * @author alexandre
 */
@Named(ViewConstantes.ANOMALIE_BEAN)
@SessionScoped
public class AnomalieBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5601792267047025418L;

	@Inject
	@Qualifier(CoreConstantes.DOSSIER_SERVICE)
	private IDossierService dossierService;

	/**
	 * Anomalie sélectionnée dans le tableau.
	 */
	private AnomalieModel selectedAnomalie;

	/**
	 * Liste des anomalies existantes.
	 */
	private List<AnomalieModel> anomalies;

	/**
	 * Liste par défaut des états de correction connus.
	 */
	private List<String> listeEtatsCorrection;

	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.anomalies = new ArrayList<AnomalieModel>();
			this.listeEtatsCorrection = new ArrayList<String>();
			this.listeEtatsCorrection.addAll(AnomalieEtatEnum.getLibelles());
		}
	}

	public void alimenterAnomalies(final SelectEvent event) {
		final DossierModel selectedDossier = (DossierModel) event.getObject();
		this.anomalies.clear();
		final List<ComparaisonDTO> anomalies = this.dossierService.listerAnomalies(selectedDossier.getRenoiRHMatricule(),
				selectedDossier.getPayLot());
		for (int i = 0; i < anomalies.size(); i++) {
			final ComparaisonDTO anomalie = anomalies.get(i);
			this.anomalies.add(AnomalieModelFactory.createAnomalieModel(i, anomalie));
		}
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public AnomalieModel getSelectedAnomalie() {
		return selectedAnomalie;
	}

	public void setSelectedAnomalie(final AnomalieModel selectedAnomalie) {
		this.selectedAnomalie = selectedAnomalie;
	}

	public List<AnomalieModel> getAnomalies() {
		return anomalies;
	}

	public void setAnomalies(final List<AnomalieModel> anomalies) {
		this.anomalies = anomalies;
	}

	public List<String> getListeEtatsCorrection() {
		return listeEtatsCorrection;
	}

	public void setListeEtatsCorrection(final List<String> listeEtatsCorrection) {
		this.listeEtatsCorrection = listeEtatsCorrection;
	}
}
