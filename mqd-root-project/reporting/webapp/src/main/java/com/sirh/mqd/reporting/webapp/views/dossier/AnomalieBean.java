package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.slf4j.event.Level;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.AnomalieModelFactory;
import com.sirh.mqd.reporting.webapp.model.AnomalieModel;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.model.comparator.AnomalieModelPerimetreComparator;
import com.sirh.mqd.reporting.webapp.model.comparator.AnomalieModelTypeComparator;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion des anomalies d'un dossier
 *
 * @author alexandre
 */
@ManagedBean(name = ViewConstantes.ANOMALIE_BEAN)
@RequestScoped
public class AnomalieBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5601792267047025418L;

	@ManagedProperty("#{" + CoreConstantes.DOSSIER_SERVICE + "}")
	private IDossierService dossierService;

	@ManagedProperty("#{" + ConstantesTraces.FACADE_LOGS + "}")
	private IFacadeLogs logger;

	/**
	 * Identifiant unique de l'anomalie sélectionnée en amont.
	 */
	private AnomalieModel selectedAnomalie;

	/**
	 * Liste des anomalies existantes.
	 */
	private List<AnomalieModel> anomalies;

	@PostConstruct
	public void setup() {
		// Initialization
		this.anomalies = new ArrayList<AnomalieModel>();

		// Supplier
		alimenterAnomalies(getCurrentDossier());
	}

	public void alimenterAnomalies(final DossierModel selectedDossier) {
		this.anomalies.clear();
		final List<ComparaisonDTO> anomaliesDTO = this.dossierService
				.listerAnomalies(selectedDossier.getRenoiRHMatricule(), selectedDossier.getPayLot());
		this.anomalies.addAll(anomaliesDTO.stream().map(anomalie -> AnomalieModelFactory.createAnomalieModel(anomalie))
				.collect(Collectors.toList()));
		Collections.sort(this.anomalies, new AnomalieModelTypeComparator());
		Collections.sort(this.anomalies, new AnomalieModelPerimetreComparator());
	}

	public void modifierEtatCorrection() {
		final DossierModel selectedDossier = getCurrentDossier();
		if (selectedDossier != null) {
			if (this.selectedAnomalie != null) {
				final String userLogin = this.getCurrentUsername();
				final String userPrenom = this.getCurrentUserFirstname();
				final String userNom = this.getCurrentUserLastname();
				final ComparaisonDTO comparaisonDTO = AnomalieModelFactory.createAnomalieDTO(
						selectedDossier.getPayLot(), selectedDossier.getRenoiRHMatricule(), selectedAnomalie, userLogin,
						userPrenom, userNom);
				this.dossierService.modifierAnomalie(comparaisonDTO);

				this.logger.logAction(Level.INFO,
						computeLogActionDTO(IHMUserActionEnum.MODIFICATION, IHMUserResultEnum.SUCCESS,
								IHMPageNameEnum.ANOMALIE,
								DateUtils.formateDateJJMMAAAAhhmmss(comparaisonDTO.getDateModification()),
								comparaisonDTO, null));

				alimenterAnomalies(selectedDossier);

				this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_INFO,
						"view.dossiers.anomalies.update.status.success", comparaisonDTO.getType().getLibelle(),
						selectedDossier.getRenoiRHMatricule());
			} else {
				this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
						"view.dossiers.anomalies.erreur.no.anomalie.selected");
			}
		} else {
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.anomalies.erreur.no.dossier.selected");
		}
	}

	public List<AnomalieModel> getAnomalies() {
		return anomalies;
	}

	public void setAnomalies(final List<AnomalieModel> anomalies) {
		this.anomalies = anomalies;
	}

	public AnomalieModel getSelectedAnomalie() {
		return selectedAnomalie;
	}

	public void setSelectedAnomalie(final AnomalieModel selectedAnomalie) {
		this.selectedAnomalie = selectedAnomalie;
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public IFacadeLogs getLogger() {
		return logger;
	}

	public void setLogger(final IFacadeLogs logger) {
		this.logger = logger;
	}
}
