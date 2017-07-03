package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
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

	@Inject
	@Qualifier(ViewConstantes.ANOMALIE_BEAN)
	private AnomalieBean anomalieBean;

	@Inject
	@Qualifier(ViewConstantes.HISTORIQUE_BEAN)
	private HistoriqueBean historiqueBean;

	@Inject
	@Qualifier(ViewConstantes.COMMENTAIRE_BEAN)
	private CommentaireBean commentaireBean;

	@Inject
	@Qualifier(ViewConstantes.STATUT_DOSSIER_BEAN)
	private StatutDossierBean statutDossierBean;

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
		final List<DossierDTO> dossiers = this.dossierService.listerDossiers(getCurrentUserPayLot(),
				getCurrentUserCorpsCode(), getCurrentUserAffectationCode(), getCurrentUserGestionnaireCode());
		for (final DossierDTO dossier : dossiers) {
			this.dossiers.add(DossierModelFactory.createDossier(dossier));
		}

		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.selectedDossier = null;
		}
	}

	public void afficherInformationsDossier(final SelectEvent event) {
		final AnomalieBean anomalieBean = this.jsfUtils.getBean(ViewConstantes.ANOMALIE_BEAN, AnomalieBean.class);
		if (anomalieBean != null) {
			anomalieBean.alimenterAnomalies(this.selectedDossier);
		}
		final CommentaireBean commentaireBean = this.jsfUtils.getBean(ViewConstantes.COMMENTAIRE_BEAN,
				CommentaireBean.class);
		if (commentaireBean != null) {
			commentaireBean.alimenterCommentaires(this.selectedDossier);
		}
		final StatutDossierBean statutDossierBean = this.jsfUtils.getBean(ViewConstantes.STATUT_DOSSIER_BEAN,
				StatutDossierBean.class);
		if (statutDossierBean != null) {
			statutDossierBean.alimenterStatutDossier(this.selectedDossier);
		}
		final SyntheseBean syntheseBean = this.jsfUtils.getBean(ViewConstantes.SYNTHESE_BEAN, SyntheseBean.class);
		if (syntheseBean != null) {
			syntheseBean.alimenterSyntheseDossier(this.selectedDossier);
		}
	}

	public boolean isTabsDisplayable() {
		return this.selectedDossier != null;
	}

	public String getSelectedDossierMatricule() {
		return this.selectedDossier.getRenoiRHMatricule();
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}

	public AnomalieBean getAnomalieBean() {
		return anomalieBean;
	}

	public void setAnomalieBean(final AnomalieBean anomalieBean) {
		this.anomalieBean = anomalieBean;
	}

	public HistoriqueBean getHistoriqueBean() {
		return historiqueBean;
	}

	public void setHistoriqueBean(final HistoriqueBean historiqueBean) {
		this.historiqueBean = historiqueBean;
	}

	public CommentaireBean getCommentaireBean() {
		return commentaireBean;
	}

	public void setCommentaireBean(final CommentaireBean commentaireBean) {
		this.commentaireBean = commentaireBean;
	}

	public StatutDossierBean getStatutDossierBean() {
		return statutDossierBean;
	}

	public void setStatutDossierBean(final StatutDossierBean statutDossierBean) {
		this.statutDossierBean = statutDossierBean;
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
