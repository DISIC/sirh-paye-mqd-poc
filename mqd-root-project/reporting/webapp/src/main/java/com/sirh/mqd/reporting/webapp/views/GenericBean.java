package com.sirh.mqd.reporting.webapp.views;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.traces.factory.LogActionFactory;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
import com.sirh.mqd.reporting.webapp.utils.JsfUtils;
import com.sirh.mqd.reporting.webapp.utils.LoginUtils;
import com.sirh.mqd.reporting.webapp.views.dossier.DossierBean;

/**
 * Classe générique de gestion de l'affichage des fenêtres
 *
 * @author alexandre
 */
public class GenericBean implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -4332614099091216883L;

	/**
	 * Selection de tous les tenant name
	 */
	public static final String KEY_ALL_VALUE_SELECTED = "TOUS";

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Inject
	@Qualifier(ContextConstantes.JSF_UTILS)
	protected JsfUtils jsfUtils;

	/**
	 * Service de gestion de l'affichage des messages dans l'IHM
	 */
	@Inject
	@Qualifier(ContextConstantes.LOGIN_UTILS)
	private LoginUtils loginUtils;

	/**
	 * Constructeur par défaut.
	 */
	public GenericBean() {
		super();
	}

	public JsfUtils getJsfUtils() {
		return jsfUtils;
	}

	public void setJsfUtils(final JsfUtils jsfUtils) {
		this.jsfUtils = jsfUtils;
	}

	public LoginUtils getLoginUtils() {
		return loginUtils;
	}

	public void setLoginUtils(final LoginUtils loginUtils) {
		this.loginUtils = loginUtils;
	}

	public String getCurrentUsername() {
		return this.loginUtils.getCurrentUsername();
	}

	public String getCurrentUserLastname() {
		return this.loginUtils.getCurrentUserLastname();
	}

	public String getCurrentUserFirstname() {
		return this.loginUtils.getCurrentUserFirstname();
	}

	public String getCurrentUserPayLot() {
		return this.loginUtils.getCurrentUserPayLot();
	}

	public String getCurrentUserCorpsCode() {
		return this.loginUtils.getCurrentUserCorpsCode();
	}

	public String getCurrentUserAffectationCode() {
		return this.loginUtils.getCurrentUserAffectationCode();
	}

	public String getCurrentUserGestionnaireCode() {
		return this.loginUtils.getCurrentUserGestionnaireCode();
	}

	public String getCurrentUserService() {
		return this.loginUtils.getCurrentUserService();
	}

	public DossierModel getCurrentDossier() {
		DossierModel dossier = null;
		final DossierBean dossierBean = this.jsfUtils.getBean(ViewConstantes.DOSSIER_BEAN, DossierBean.class);
		if (dossierBean != null) {
			dossier = dossierBean.getSelectedDossier();
		}
		return dossier;
	}

	public String getKeyAllValueSelected() {
		return KEY_ALL_VALUE_SELECTED;
	}

	public LogActionDTO computeLogActionDTO(final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult,
			final IHMPageNameEnum pageName, final String businessID, final Object businessObjetInitial,
			final Object businessObjetModified) {
		final String login = this.loginUtils.getCurrentUsername();
		final String role = this.loginUtils.getRolesAsString();
		final String authenticationDate = this.loginUtils.getDateConnexion();
		return LogActionFactory.createLogAction(login, role, authenticationDate, actionType, actionResult, pageName,
				businessID, businessObjetInitial, businessObjetModified);
	}
}
