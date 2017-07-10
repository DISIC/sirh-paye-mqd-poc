package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Qualifier;

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
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Identifiant unique de l'anomalie sélectionnée en amont.
	 */
	private AnomalieModel selectedAnomalie;

	/**
	 * Liste des anomalies existantes.
	 */
	private List<AnomalieModel> anomalies;

	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.anomalies = new ArrayList<AnomalieModel>();
		}
	}

	public void alimenterAnomalies(final DossierModel selectedDossier) {
		this.anomalies.clear();
		final List<ComparaisonDTO> anomalies = this.dossierService
				.listerAnomalies(selectedDossier.getRenoiRHMatricule(), selectedDossier.getPayLot());
		for (int i = 0; i < anomalies.size(); i++) {
			final ComparaisonDTO anomalie = anomalies.get(i);
			this.anomalies.add(AnomalieModelFactory.createAnomalieModel(anomalie));
		}
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

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
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
}
