package com.sirh.mqd.reporting.webapp.views;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.utils.constante.Constantes;
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
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericBean.class);

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
	protected LoginUtils loginUtils;

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

	/**
	 * @return the currentUsername
	 */
	public String getCurrentUsername() {
		return this.loginUtils.getCurrentUsername();
	}

	public DossierModel getCurrentDossier() {
		DossierModel dossier = null;
		final DossierBean dossierBean = this.jsfUtils.getBean(ViewConstantes.DOSSIER_BEAN, DossierBean.class);
		if (dossierBean != null) {
			dossier = dossierBean.getSelectedDossier();
		}
		return dossier;
	}

	/**
	 * @return the keyAllValueSelected
	 */
	public String getKeyAllValueSelected() {
		return KEY_ALL_VALUE_SELECTED;
	}

	/**
	 * Show a message with the missing mandatories fields.
	 *
	 * @param attributsManquants
	 */
	public void showMessageForMissingFields(final List<String> attributsManquants) {
		final StringBuilder attributsManquantsMsg = new StringBuilder();
		final StringBuilder logErrorMsg = new StringBuilder();
		boolean notFirst = false;
		for (final String attribut : attributsManquants) {
			if (notFirst) {
				attributsManquantsMsg.append(", ");
			} else {
				notFirst = true;
			}
			attributsManquantsMsg.append(attribut);
		}
		logErrorMsg.append("[Recherche] Erreur, les attributs suivants sont obligatoires : ");
		logErrorMsg.append(attributsManquants);
		logErrorMsg.append(Constantes.COMMA);
		LOGGER.error(logErrorMsg.toString());

		final String[] attributsManquantsMsgArray = { attributsManquantsMsg.toString() };
		this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR, "error.functionnal.mandatoryFields",
				attributsManquantsMsgArray);
	}
}
