package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.statut_dossier.StatutDossierDTO;
import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierDisponibiliteEnum;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.reporting.core.api.IStatutDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.StatutDossierModelFactory;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.model.StatutDossierModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue du statut d'un dossier
 *
 * @author khalil
 */
@Named(ViewConstantes.STATUT_DOSSIER_BEAN)
@SessionScoped
public class StatutDossierBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6757741757173168648L;

	@Inject
	@Qualifier(CoreConstantes.STATUT_DOSSIER_SERVICE)
	private IStatutDossierService statutDossierService;

	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Statut d'un dossier sélectionné dans le tableau.
	 */
	private StatutDossierModel statutDossier;

	/**
	 * Liste par défaut des disponibilités d'un dossier connus.
	 */
	private List<String> listeDisponibilites;

	/**
	 * Liste par défaut des affectations d'un dossier connus.
	 */
	private List<String> listeAffectations;

	/**
	 * disponibilité sélectionné du dossier.
	 */
	private String selectedDisponibilite;

	/**
	 * affectation sélectionné du dossier.
	 */
	private String selectedAffectation;

	public void setup() {
		// Initialization
		this.listeDisponibilites = new ArrayList<String>();
		this.listeDisponibilites.addAll(DossierDisponibiliteEnum.getLibelles());
		this.listeAffectations = new ArrayList<String>();
		this.listeAffectations.addAll(DossierAffectationEnum.getLibelles());

		// Supplier
	}

	protected void alimenterStatutDossier(final DossierModel selectedDossier) {
		final Optional<StatutDossierDTO> statutDossierDTO = this.statutDossierService
				.genererStatutDossier(selectedDossier.getRenoiRHMatricule(), selectedDossier.getPayLot());
		if (statutDossierDTO.isPresent()) {
			this.statutDossier = StatutDossierModelFactory.createStatutDossierModel(statutDossierDTO.get());
			this.selectedDisponibilite = this.statutDossier.getDisponibilite();
			this.selectedAffectation = this.statutDossier.getAffectation();
		} else {
			this.selectedDisponibilite = "";
			this.selectedAffectation = DossierAffectationEnum.A_TRAITER.getLibelle();
		}
	}

	public void modifierStatutDossier() {
		final DossierModel selectedDossier = getCurrentDossier();
		if (selectedDossier != null) {
			final StatutDossierDTO statutDossierDTO = StatutDossierModelFactory.createStatutDossierDTO(
					this.selectedDisponibilite, this.selectedAffectation, selectedDossier.getPayLot(),
					selectedDossier.getRenoiRHMatricule());
			this.statutDossierService.modifierStatutDossier(statutDossierDTO);

			this.logger.logAction(Level.INFO, computeLogActionDTO(IHMUserActionEnum.MODIFICATION,
					IHMUserResultEnum.SUCCESS, IHMPageNameEnum.STATUT_DOSSIER, null, statutDossierDTO, null));

			final DossierModel dossier = getCurrentDossier();
			alimenterStatutDossier(dossier);

			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_INFO,
					"view.dossiers.statut_dossier.update.status.success");
		} else {
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.statut_dossier.erreur.no.dossier.selected");
		}
	}

	public StatutDossierModel getStatutDossier() {
		return statutDossier;
	}

	public void setStatutDossierModel(final StatutDossierModel statutDossier) {
		this.statutDossier = statutDossier;
	}

	public String getSelectedDisponibilite() {
		return selectedDisponibilite;
	}

	public void setSelectedDisponibilite(final String selectedDisponibilite) {
		this.selectedDisponibilite = selectedDisponibilite;
	}

	public String getSelectedAffectation() {
		return selectedAffectation;
	}

	public void setSelectedAffectation(final String selectedAffectation) {
		this.selectedAffectation = selectedAffectation;
	}

	public void setStatutDossier(final StatutDossierModel statutDossier) {
		this.statutDossier = statutDossier;
	}

	public void setListeAffectations(final List<String> listeAffectations) {
		this.listeAffectations = listeAffectations;
	}

	public List<String> getListeDisponibilites() {
		return listeDisponibilites;
	}

	public void setListeDisponibilites(final List<String> listeDisponibilites) {
		this.listeDisponibilites = listeDisponibilites;
	}

	public List<String> getListeAffectations() {
		return listeAffectations;
	}

	public void setListeAffectation(final List<String> listeAffectations) {
		this.listeAffectations = listeAffectations;
	}

}
