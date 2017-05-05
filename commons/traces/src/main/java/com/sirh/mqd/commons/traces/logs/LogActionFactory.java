package com.sirh.mqd.commons.traces.logs;

import java.io.IOException;
import java.util.Date;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.traces.enums.EnumActionType;
import com.sirh.mqd.commons.traces.enums.EnumEcranType;
import com.sirh.mqd.commons.traces.enums.UserActionEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.JsonUtils;

public class LogActionFactory {

	public static LogActionDTO getLogAction(final UserActionEnum actionIhm, final String email, final String role,
			final String dateAuthentification, final EnumActionType actionType, final EnumActionNature actionNature,
			final EnumEcranType ecranType, final String idMetier, final Object obj, final Object objOldValue) {

		LogActionDTO logAction = new LogActionDTO();
		logAction.setActionIhm(actionIhm);
		if (actionNature == null) {
			logAction.setActionNature(EnumActionNature.SUCCESS);
		}
		String json = null;
		String jsonOldValue = null;
		if (obj != null) {
			try {
				json = JsonUtils.serializerJSON(obj);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (objOldValue != null) {
			try {
				jsonOldValue = JsonUtils.serializerJSON(objOldValue);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (dateAuthentification == null) {
			logAction.setDateAuthentification(new Date());
		}

		logAction.setDateAuthentification(DateUtils.parseeDateJJMMAAAAhhmmss(dateAuthentification));
		logAction.setRole(role);
		logAction.setEmail(email);
		logAction.setActionType(actionType);
		logAction.setActionNature(actionNature);
		logAction.setEcranType(ecranType);
		logAction.setIdMetier(idMetier);
		logAction.setObj(json);
		logAction.setObjOldValue(jsonOldValue);

		return logAction;
	}

	public static LogActionDTO getLogAction(final UserActionEnum actionIhm, final String email, final String role,
			final String dateAuthentification, final EnumActionType actionType, final EnumActionNature actionNature,
			final EnumEcranType ecranType, final String idMetier, final Object obj) {

		LogActionDTO logAction = new LogActionDTO();
		logAction.setActionIhm(actionIhm);
		if (actionNature == null) {
			logAction.setActionNature(EnumActionNature.SUCCESS);
		}
		String json = null;
		if (obj != null) {
			try {
				json = JsonUtils.serializerJSON(obj);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (dateAuthentification == null) {
			logAction.setDateAuthentification(new Date());
		}

		logAction.setDateAuthentification(DateUtils.parseeDateJJMMAAAAhhmmss(dateAuthentification));
		logAction.setRole(role);
		logAction.setEmail(email);
		logAction.setActionType(actionType);
		logAction.setActionNature(actionNature);
		logAction.setEcranType(ecranType);
		logAction.setIdMetier(idMetier);
		logAction.setObj(json);
		logAction.setObjOldValue(null);

		return logAction;
	}

}
