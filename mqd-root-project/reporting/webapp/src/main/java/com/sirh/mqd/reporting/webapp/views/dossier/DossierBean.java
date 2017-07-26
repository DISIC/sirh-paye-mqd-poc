package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.sirh.mqd.commons.exchanges.constante.AlerteCouleurSeuilsConstantes;
import com.sirh.mqd.commons.exchanges.constante.AnomalieCouleurSeuilsConstantes;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.DossierModelFactory;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.model.comparator.DossierModelAlerteComparator;
import com.sirh.mqd.reporting.webapp.model.comparator.DossierModelAnomalieComparator;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion des dossiers rattachés à un utilisateur
 *
 * @author alexandre
 */
@ManagedBean(name = ViewConstantes.DOSSIER_BEAN)
@SessionScoped
public class DossierBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 555762255490874333L;

	@ManagedProperty("#{" + CoreConstantes.DOSSIER_SERVICE + "}")
	private IDossierService dossierService;

	/**
	 * Identifiant unique du dossier sélectionnée en amont.
	 */
	private DossierModel selectedDossier;

	/**
	 * Liste des dossiers existantes.
	 */
	private List<DossierModel> dossiers;

	/**
	 * Seuil rouge d'Alertes. Si le nombre d'alertes est supérieur ou égale à ce
	 * seuil alors cellule aura un code couleur rouge
	 */
	private int seuilAlerteRouge;

	/**
	 * Seuil rouge d'Anomalies. Si le nombre d'anomalies est supérieur ou égale
	 * à ce seuil alors la cellule aura un code couleur rouge
	 */
	private int seuilAnomalieRouge;

	/**
	 * Seuil orange d'Alertes. Si le nombre d'alertes est supérieur ou égale à
	 * ce seuil mais reste inférieur au seuil rouge alors la cellule aura un
	 * code couleur orange
	 */
	private int seuilAlerteOrange;

	/**
	 * Seuil orange d'Anomalies. Si le nombre d'anomalies est supérieur ou égale
	 * à ce seuil mais reste inférieur au seuil rouge alors la cellule aura un
	 * code couleur orange
	 */
	private int seuilAnomalieOrange;

	/**
	 * Seuil jaune d'Alertes. Si le nombre d'alertes est supérieur ou égale à ce
	 * seuil mais reste inférieur au seuil orange alors la cellule aura un code
	 * couleur jaune
	 */
	private int seuilAlerteJaune;

	/**
	 * Seuil jaune d'Anomalies. Si le nombre d'anomalies est supérieur ou égale
	 * à ce seuil mais reste inférieur au seuil orange alors la cellule aura un
	 * code couleur jaune
	 */
	private int seuilAnomalieJaune;

	/**
	 * Seuil vert d'Anomalies. Si le nombre d'anomalies est inférieur ou égale à
	 * ce seuil alors la cellule aura un code couleur vert
	 */
	private int seuilAnomalieVert;

	/**
	 * Seuil jaune d'Alertes. Si le nombre d'alertes est inférieur ou égale à ce
	 * seuil alors la cellule aura un code couleur vert
	 */
	private int seuilAlerteVert;

	public void setup(final ComponentSystemEvent event) {
		// Initialization
		initialisationConfig();

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.dossiers = new ArrayList<DossierModel>();
			final List<DossierDTO> dossiers = this.dossierService.listerDossiers(getCurrentUserPayLot(),
					getCurrentUserCorpsCode(), getCurrentUserAffectationCode(), getCurrentUserGestionnaireCode());
			for (final DossierDTO dossier : dossiers) {
				this.dossiers.add(DossierModelFactory.createDossier(dossier));
			}
			Collections.sort(this.dossiers, new DossierModelAnomalieComparator());
			Collections.sort(this.dossiers, new DossierModelAlerteComparator());
			this.selectedDossier = null;
		}
	}

	public void affecterSeuilsAlertes() {
		final Map<String, Integer> mapSeuilsAlertes = this.dossierService.rechercherCouleurSeuilsAlerte();
		this.seuilAlerteRouge = mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ROUGE);
		this.seuilAlerteOrange = mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ORANGE);
		this.seuilAlerteJaune = mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.JAUNE);
		this.seuilAlerteVert = mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.VERT);
	}

	public void affecterSeuilsAnomalies() {
		final Map<String, Integer> mapSeuilsAnomalies = this.dossierService.rechercherCouleurSeuilsAnomalie();
		this.seuilAnomalieRouge = mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ROUGE);
		this.seuilAnomalieOrange = mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ORANGE);
		this.seuilAnomalieJaune = mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.JAUNE);
		this.seuilAnomalieVert = mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.VERT);
	}

	public void initialisationConfig() {
		affecterSeuilsAlertes();
		affecterSeuilsAnomalies();
	}

	public boolean isTabsDisplayable() {
		return this.selectedDossier != null;
	}

	public String getSelectedDossierMatricule() {
		return this.selectedDossier.getRenoiRHMatricule();
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

	public int getSeuilAlerteRouge() {
		return seuilAlerteRouge;
	}

	public void setSeuilAlerteRouge(final int seuilAlerteRouge) {
		this.seuilAlerteRouge = seuilAlerteRouge;
	}

	public int getSeuilAnomalieRouge() {
		return seuilAnomalieRouge;
	}

	public void setSeuilAnomalieRouge(final int seuilAnomalieRouge) {
		this.seuilAnomalieRouge = seuilAnomalieRouge;
	}

	public int getSeuilAlerteOrange() {
		return seuilAlerteOrange;
	}

	public void setSeuilAlerteOrange(final int seuilAlerteOrange) {
		this.seuilAlerteOrange = seuilAlerteOrange;
	}

	public int getSeuilAnomalieOrange() {
		return seuilAnomalieOrange;
	}

	public void setSeuilAnomalieOrange(final int seuilAnomalieOrange) {
		this.seuilAnomalieOrange = seuilAnomalieOrange;
	}

	public int getSeuilAlerteJaune() {
		return seuilAlerteJaune;
	}

	public void setSeuilAlerteJaune(final int seuilAlerteJaune) {
		this.seuilAlerteJaune = seuilAlerteJaune;
	}

	public int getSeuilAnomalieJaune() {
		return seuilAnomalieJaune;
	}

	public void setSeuilAnomalieJaune(final int seuilAnomalieJaune) {
		this.seuilAnomalieJaune = seuilAnomalieJaune;
	}

	public int getSeuilAnomalieVert() {
		return seuilAnomalieVert;
	}

	public void setSeuilAnomalieVert(final int seuilAnomalieVert) {
		this.seuilAnomalieVert = seuilAnomalieVert;
	}

	public int getSeuilAlerteVert() {
		return seuilAlerteVert;
	}

	public void setSeuilAlerteVert(final int seuilAlerteVert) {
		this.seuilAlerteVert = seuilAlerteVert;
	}

	public IDossierService getDossierService() {
		return dossierService;
	}

	public void setDossierService(final IDossierService dossierService) {
		this.dossierService = dossierService;
	}
}
