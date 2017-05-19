package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.AnomalieModelFactory;
import com.sirh.mqd.reporting.webapp.model.AnomalieModel;
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

	@Inject
	@Qualifier(ViewConstantes.DOSSIER_BEAN)
	private DossierBean dossierBean;

	/**
	 * Anomalie sélectionnée dans le tableau.
	 */
	private AnomalieModel selectedAnomalie;

	/**
	 * Liste des anomalies existantes.
	 */
	private List<AnomalieModel> anomalies;

	public void setup() {
		// Initialization
		this.anomalies = new ArrayList<AnomalieModel>();
	}

	public void alimenterAnomalies() {
		this.anomalies.clear();
		final List<AnomalieDTO> anomalies = this.dossierService.listerAnomalies(
				this.dossierBean.getSelectedDossier().getRenoiRHMatricule(),
				this.dossierBean.getSelectedDossier().getPayLot());
		for (int i = 0; i < anomalies.size(); i++) {
			final AnomalieDTO anomalie = anomalies.get(i);
			this.anomalies.add(AnomalieModelFactory.createAnomalie(i, anomalie));
		}
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public DossierBean getDossierBean() {
		return dossierBean;
	}

	public void setDossierBean(final DossierBean dossierBean) {
		this.dossierBean = dossierBean;
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
}
