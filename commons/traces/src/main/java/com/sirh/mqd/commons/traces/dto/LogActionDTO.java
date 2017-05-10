package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * DTO correspondant à un log d'une action IHM réalisée par un utilisateur sur
 * une page web
 *
 * @author alexandre
 */
public class LogActionDTO implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6391762164835901573L;

	/**
	 * Login de l'utilisateur
	 */
	private String login;

	/**
	 * Rôle de l'utilisateur
	 */
	private String role;

	/**
	 * Date d'authentification
	 */
	private Date authenticationDate;

	/**
	 * Type d'action IHM réalisée par l'utilisateur
	 */
	private IHMUserActionEnum actionType;

	/**
	 * Résultat de l'action IHM réalisée par l'utilisateur
	 */
	private IHMUserResultEnum actionResult;

	/**
	 * Nom de la page sur laquelle a eu lieu l'action utilisateur
	 */
	private IHMPageNameEnum pageName;

	/**
	 * Identifiant métier de l'objet manipulé
	 */
	private String businessID;

	/**
	 * Objet métier initial avant modifications au format JSON
	 */
	private String businessObjectInitial;

	/**
	 * Objet métier après modifications au format JSON
	 */
	private String businessObjectModified;

	public LogActionDTO() {
		super();
	}

	public LogActionDTO(final String login, final String role, final Date authenticationDate,
			final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult, final IHMPageNameEnum pageName,
			final String businessID, final String businessObjectInitial, final String businessObjetModified) {
		super();
		this.login = login;
		this.role = role;
		this.authenticationDate = authenticationDate;
		this.actionType = actionType;
		this.actionResult = actionResult;
		this.pageName = pageName;
		this.businessID = businessID;
		this.businessObjectInitial = businessObjectInitial;
		this.businessObjectModified = businessObjetModified;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public Date getAuthenticationDate() {
		return authenticationDate;
	}

	public void setAuthenticationDate(final Date authenticationDate) {
		this.authenticationDate = DateUtils.clonerDate(authenticationDate);
	}

	public IHMUserActionEnum getActionType() {
		return actionType;
	}

	public void setActionType(final IHMUserActionEnum actionType) {
		this.actionType = actionType;
	}

	public IHMUserResultEnum getActionResult() {
		return actionResult;
	}

	public void setActionResult(final IHMUserResultEnum actionResult) {
		this.actionResult = actionResult;
	}

	public IHMPageNameEnum getPageName() {
		return pageName;
	}

	public void setPageName(final IHMPageNameEnum pageName) {
		this.pageName = pageName;
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(final String businessID) {
		this.businessID = businessID;
	}

	public String getBusinessObjectInitial() {
		return businessObjectInitial;
	}

	public void setBusinessObjectInitial(final String businessObjectInitial) {
		this.businessObjectInitial = businessObjectInitial;
	}

	public String getBusinessObjectModified() {
		return businessObjectModified;
	}

	public void setBusinessObjectModified(final String businessObjectModified) {
		this.businessObjectModified = businessObjectModified;
	}

	@Override
	public String toString() {
		String authenticationDate = StringUtils.EMPTY;
		if (this.authenticationDate != null) {
			authenticationDate = DateUtils.formateDateJJMMAAAAhhmmssSSS(this.authenticationDate);
		}
		final StringBuilder logBuilder = new StringBuilder();
		logBuilder.append(Constantes.OPEN_SQUARE_BRACKET);
		final Joiner joiner = Joiner
				.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.DASH + Constantes.OPEN_SQUARE_BRACKET)
				.useForNull(Constantes.DASH);
		joiner.appendTo(logBuilder, this.login, this.role, authenticationDate, this.pageName, this.actionType,
				this.actionResult, this.businessID, this.businessObjectInitial, this.businessObjectModified);
		logBuilder.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logBuilder.toString();
	}

}
