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

import com.sirh.mqd.commons.exchanges.dto.pivot.StatutDossierDTO;
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
	 * Statut initial d'un dossier avant modifications.
	 */
	private StatutDossierDTO statutDossierInitial;

	/**
	 * Liste par défaut des disponibilités d'un dossier connus.
	 */
	private List<DossierDisponibiliteEnum> listeDisponibilites;

	/**
	 * Liste par défaut des affectations d'un dossier connus.
	 */
	private List<DossierAffectationEnum> listeAffectations;

	/**
	 * disponibilité sélectionné du dossier.
	 */
	private DossierDisponibiliteEnum selectedDisponibilite;

	/**
	 * affectation sélectionné du dossier.
	 */
	private DossierAffectationEnum selectedAffectation;

	public void setup() {
		// Initialization
		this.listeDisponibilites = new ArrayList<DossierDisponibiliteEnum>();
		this.listeDisponibilites.addAll(DossierDisponibiliteEnum.getEnumAffichables());
		this.listeAffectations = new ArrayList<DossierAffectationEnum>();
		this.listeAffectations.addAll(DossierAffectationEnum.getEnumAffichables());

		// Supplier
	}

	protected void alimenterStatutDossier(final DossierModel selectedDossier) {
		final Optional<StatutDossierDTO> statutDossierDTO = this.statutDossierService
				.rechercherStatutDossier(selectedDossier.getRenoiRHMatricule(), selectedDossier.getPayLot());
		if (statutDossierDTO.isPresent()) {
			this.statutDossierInitial = statutDossierDTO.get();
			final StatutDossierModel statutDossier = StatutDossierModelFactory
					.createStatutDossierModel(this.statutDossierInitial);
			this.selectedDisponibilite = statutDossier.getDisponibilite();
			this.selectedAffectation = statutDossier.getAffectation();
		} else {
			this.statutDossierInitial = null;
			this.selectedDisponibilite = DossierDisponibiliteEnum.AUCUNE_INFORMATION;
			this.selectedAffectation = DossierAffectationEnum.AUCUNE_INFORMATION;
		}
	}

	public void modifierStatutDossier() {
		final DossierModel selectedDossier = getCurrentDossier();
		if (selectedDossier != null) {
			final StatutDossierDTO statutDossierDTO = StatutDossierModelFactory.createStatutDossierDTO(
					this.selectedDisponibilite, this.selectedAffectation, selectedDossier.getPayLot(),
					selectedDossier.getRenoiRHMatricule());
			this.statutDossierService.modifierStatutDossier(statutDossierDTO);

			this.logger.logAction(Level.INFO,
					computeLogActionDTO(IHMUserActionEnum.MODIFICATION, IHMUserResultEnum.SUCCESS,
							IHMPageNameEnum.STATUT_DOSSIER, null, this.statutDossierInitial, statutDossierDTO));

			alimenterStatutDossier(selectedDossier);

			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_INFO,
					"view.dossiers.statut_dossier.update.status.success", selectedDossier.getRenoiRHMatricule());
		} else {
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.statut_dossier.erreur.no.dossier.selected");
		}
	}

	public StatutDossierDTO getStatutDossierInitial() {
		return statutDossierInitial;
	}

	public void setStatutDossierInitial(final StatutDossierDTO statutDossierInitial) {
		this.statutDossierInitial = statutDossierInitial;
	}

	public List<DossierDisponibiliteEnum> getListeDisponibilites() {
		return listeDisponibilites;
	}

	public void setListeDisponibilites(final List<DossierDisponibiliteEnum> listeDisponibilites) {
		this.listeDisponibilites = listeDisponibilites;
	}

	public List<DossierAffectationEnum> getListeAffectations() {
		return listeAffectations;
	}

	public void setListeAffectations(final List<DossierAffectationEnum> listeAffectations) {
		this.listeAffectations = listeAffectations;
	}

	public DossierDisponibiliteEnum getSelectedDisponibilite() {
		return selectedDisponibilite;
	}

	public void setSelectedDisponibilite(final DossierDisponibiliteEnum selectedDisponibilite) {
		this.selectedDisponibilite = selectedDisponibilite;
	}

	public DossierAffectationEnum getSelectedAffectation() {
		return selectedAffectation;
	}

	public void setSelectedAffectation(final DossierAffectationEnum selectedAffectation) {
		this.selectedAffectation = selectedAffectation;
	}
}
