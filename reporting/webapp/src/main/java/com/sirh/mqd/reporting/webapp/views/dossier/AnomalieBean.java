package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.List;

import javax.annotation.PostConstruct;
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

/**
 * La vue de la page de gestion des anomalies d'un dossier
 *
 * @author alexandre
 */
@Named(ViewConstantes.ANOMALIE_BEAN)
@SessionScoped
public class AnomalieBean {

	@Inject
	@Qualifier(CoreConstantes.DOSSIER_SERVICE)
	private IDossierService dossierService;

	/**
	 * Identifiant unique du dossier sélectionnée en amont.
	 */
	private String dossierId;

	/**
	 * Anomalie sélectionnée dans le tableau.
	 */
	private AnomalieModel selectedAnomalie;

	/**
	 * Liste des anomalie existantes.
	 */
	private List<AnomalieModel> anomalies;

	@PostConstruct
	public void setup() {
		dossierId = "test";
		final List<AnomalieDTO> anomalies = dossierService.listerAnomalies(dossierId);
		for (final AnomalieDTO anomalie : anomalies) {
			this.anomalies.add(AnomalieModelFactory.createAnomalie(anomalie));
		}
	}

	public List<AnomalieModel> getAnomalies() {
		return anomalies;
	}

	public void setAnomalies(final List<AnomalieModel> anomalies) {
		this.anomalies = anomalies;
	}

	public String getDossierId() {
		return dossierId;
	}

	public void setDossierId(final String dossierId) {
		this.dossierId = dossierId;
	}

	public AnomalieModel getSelectedAnomalie() {
		return selectedAnomalie;
	}

	public void setSelectedAnomalie(final AnomalieModel selectedAnomalie) {
		this.selectedAnomalie = selectedAnomalie;
	}
}
