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

import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.AlerteModelFactory;
import com.sirh.mqd.reporting.webapp.model.AlerteModel;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.model.comparator.AlerteModelPerimetreComparator;
import com.sirh.mqd.reporting.webapp.model.comparator.AlerteModelTypeComparator;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion des alertes d'un dossier
 *
 * @author alexandre
 */
@ManagedBean(name = ViewConstantes.ALERTE_BEAN)
@RequestScoped
public class AlerteBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -4665791775385979013L;

	@ManagedProperty("#{" + CoreConstantes.DOSSIER_SERVICE + "}")
	private IDossierService dossierService;

	@ManagedProperty("#{" + ConstantesTraces.FACADE_LOGS + "}")
	private IFacadeLogs logger;

	/**
	 * Identifiant unique de l'alerte sélectionnée en amont.
	 */
	private AlerteModel selectedAlerte;

	/**
	 * Liste des alertes existantes.
	 */
	private List<AlerteModel> alertes;

	@PostConstruct
	public void setup() {
		// Initialization
		this.alertes = new ArrayList<AlerteModel>();

		// Supplier
		alimenterAlertes(getCurrentDossier());
	}

	public void alimenterAlertes(final DossierModel selectedDossier) {
		this.alertes.clear();
		final List<AlerteDTO> alertesDTO = this.dossierService.listerAlertes(selectedDossier.getRenoiRHMatricule(),
				selectedDossier.getPayLot());
		this.alertes.addAll(alertesDTO.stream().map(alerte -> AlerteModelFactory.createAlerteModel(alerte))
				.collect(Collectors.toList()));
		Collections.sort(this.alertes, new AlerteModelTypeComparator());
		Collections.sort(this.alertes, new AlerteModelPerimetreComparator());
	}

	public void modifierEtat() {
		final DossierModel selectedDossier = getCurrentDossier();
		if (selectedDossier != null) {
			if (this.selectedAlerte != null) {
				final String userLogin = this.getCurrentUsername();
				final String userPrenom = this.getCurrentUserFirstname();
				final String userNom = this.getCurrentUserLastname();
				final AlerteDTO alerteDTO = AlerteModelFactory.createAlerteDTO(selectedDossier.getPayLot(),
						selectedDossier.getRenoiRHMatricule(), selectedAlerte, userLogin, userPrenom, userNom);
				this.dossierService.modifierAlerte(alerteDTO);

				this.logger.logAction(Level.INFO,
						computeLogActionDTO(IHMUserActionEnum.MODIFICATION, IHMUserResultEnum.SUCCESS,
								IHMPageNameEnum.ALERTE,
								DateUtils.formateDateJJMMAAAAhhmmss(alerteDTO.getDateModification()), alerteDTO, null));

				alimenterAlertes(selectedDossier);

				this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_INFO,
						"view.dossiers.alertes.update.status.success", alerteDTO.getType().getLibelle(),
						selectedDossier.getRenoiRHMatricule());
			} else {
				this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
						"view.dossiers.alertes.erreur.no.alerte.selected");
			}
		} else {
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.alertes.erreur.no.dossier.selected");
		}
	}

	public List<AlerteModel> getAlertes() {
		return alertes;
	}

	public void setAlertes(final List<AlerteModel> alertes) {
		this.alertes = alertes;
	}

	public AlerteModel getSelectedAlerte() {
		return selectedAlerte;
	}

	public void setSelectedAlerte(final AlerteModel selectedAlerte) {
		this.selectedAlerte = selectedAlerte;
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
