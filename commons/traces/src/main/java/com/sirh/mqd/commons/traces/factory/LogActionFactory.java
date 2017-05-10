package com.sirh.mqd.commons.traces.factory;

import java.io.IOException;
import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.JsonUtils;

/**
 * Factory de création des messages des actions IHM.
 *
 * @author alexandre
 */
public final class LogActionFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private LogActionFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + LogActionFactory.class.getName());
	}

	/**
	 * Méthode renvoyant le log d'action IHM avec les informations obligatoires.
	 *
	 * @param login
	 *            le login de l'utilisateur connecté
	 * @param role
	 *            le rôle de l'utilisateur
	 * @param pageName
	 *            la page web de l'action utilisateur
	 * @param actionType
	 *            l'action réalisée dans l'IHM (e.g. modification, etc...)
	 * @param actionResult
	 *            le résultat de l'action réalisée dans l'IHM (e.g. succès,
	 *            etc...)
	 * @return {@link LogMeLogActionDTOtierDTO} l'objet DTO gérant les logs
	 *         d'actions IHM
	 */
	private static LogActionDTO createDefaultLogAction(final String login, final String role,
			final IHMPageNameEnum pageName, final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult) {
		final LogActionDTO logAction = new LogActionDTO();
		logAction.setLogin(login);
		logAction.setRole(role);
		logAction.setPageName(pageName);

		if (actionType == null) {
			logAction.setActionType(IHMUserActionEnum.NONE);
		} else {
			logAction.setActionType(actionType);
		}
		if (actionResult == null) {
			logAction.setActionResult(IHMUserResultEnum.NONE);
		} else {
			logAction.setActionResult(actionResult);
		}

		return logAction;
	}

	public static LogActionDTO createLogAction(final String login, final String role, final String authenticationDate,
			final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult, final IHMPageNameEnum pageName,
			final String businessID, final Object businessObjetInitial, final Object businessObjetModified)
			throws IOException {
		final LogActionDTO logAction = createDefaultLogAction(login, role, pageName, actionType, actionResult);

		if (authenticationDate != null) {
			logAction.setAuthenticationDate(DateUtils.parseeDateJJMMAAAAhhmmss(authenticationDate));
		}

		String businessJsonInitial = null;
		String businessJsonModified = null;
		if (businessObjetInitial != null) {
			businessJsonInitial = JsonUtils.serializerJSON(businessObjetInitial);
		}
		if (businessObjetModified != null) {
			businessJsonModified = JsonUtils.serializerJSON(businessObjetModified);
		}

		logAction.setBusinessID(businessID);
		logAction.setBusinessObjectInitial(businessJsonInitial);
		logAction.setBusinessObjectModified(businessJsonModified);
		return logAction;
	}

	public static LogActionDTO createLogAction(final String login, final String role, final Date authenticationDate,
			final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult, final IHMPageNameEnum pageName,
			final String businessID, final Object businessObjetInitial, final Object businessObjetModified)
			throws IOException {
		final LogActionDTO logAction = createDefaultLogAction(login, role, pageName, actionType, actionResult);

		if (authenticationDate != null) {
			logAction.setAuthenticationDate(authenticationDate);
		}

		String businessJsonInitial = null;
		String businessJsonModified = null;
		if (businessObjetInitial != null) {
			businessJsonInitial = JsonUtils.serializerJSON(businessObjetInitial);
		}
		if (businessObjetModified != null) {
			businessJsonModified = JsonUtils.serializerJSON(businessObjetModified);
		}

		logAction.setBusinessID(businessID);
		logAction.setBusinessObjectInitial(businessJsonInitial);
		logAction.setBusinessObjectModified(businessJsonModified);
		return logAction;
	}

	public static LogActionDTO createLogAction(final String login, final String role, final String authenticationDate,
			final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult, final IHMPageNameEnum pageName,
			final String businessID, final Object businessObjetInitial) throws IOException {
		return createLogAction(login, role, authenticationDate, actionType, actionResult, pageName, businessID,
				businessObjetInitial, null);
	}

	public static LogActionDTO createLogAction(final String login, final String role, final Date authenticationDate,
			final IHMUserActionEnum actionType, final IHMUserResultEnum actionResult, final IHMPageNameEnum pageName,
			final String businessID, final Object businessObjetInitial) throws IOException {
		return createLogAction(login, role, authenticationDate, actionType, actionResult, pageName, businessID,
				businessObjetInitial, null);
	}
}
