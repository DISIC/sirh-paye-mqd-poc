package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;
import java.util.Date;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.constantes.Constantes;
import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.traces.enums.EnumActionType;
import com.sirh.mqd.commons.traces.enums.EnumEcranType;
import com.sirh.mqd.commons.traces.enums.UserActionEnum;
import com.sirh.mqd.commons.utils.DateUtils;

public class LogActionDTO implements Serializable {

	private static final long serialVersionUID = -6391762164835901573L;

	private UserActionEnum actionIhm;

	private String email;

	private String role;

	private Date dateAuthentification;

	private EnumActionType actionType;

	private EnumActionNature actionNature;

	private EnumEcranType ecranType;

	private String idMetier;

	private Object obj;

	private Object objOldValue;

	public LogActionDTO() {
		super();
	}

	public LogActionDTO(final UserActionEnum actionIhm, final String email, final String role, final Date dateAuthentification,
			final EnumActionType actionType, final EnumActionNature actionNature, final EnumEcranType ecranType, final String idMetier,
			final Object obj, final Object objOldValue) {
		this();
		this.actionIhm = actionIhm;
		this.email = email;
		this.role = role;
		this.dateAuthentification = dateAuthentification;
		this.actionType = actionType;
		this.actionNature = actionNature;
		this.ecranType = ecranType;
		this.idMetier = idMetier;
		this.obj = obj;
		this.objOldValue = objOldValue;
	}

	public UserActionEnum getActionIhm() {
		return actionIhm;
	}

	public void setActionIhm(final UserActionEnum actionIhm) {
		this.actionIhm = actionIhm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public Date getDateAuthentification() {
		return dateAuthentification;
	}

	public void setDateAuthentification(final Date dateAuthentification) {
		this.dateAuthentification = dateAuthentification;
	}

	public EnumActionType getActionType() {
		return actionType;
	}

	public void setActionType(final EnumActionType actionType) {
		this.actionType = actionType;
	}

	public EnumActionNature getActionNature() {
		return actionNature;
	}

	public void setActionNature(final EnumActionNature actionNature) {
		this.actionNature = actionNature;
	}

	public EnumEcranType getEcranType() {
		return ecranType;
	}

	public void setEcranType(final EnumEcranType ecranType) {
		this.ecranType = ecranType;
	}

	public String getIdMetier() {
		return idMetier;
	}

	public void setIdMetier(final String idMetier) {
		this.idMetier = idMetier;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(final Object obj) {
		this.obj = obj;
	}

	public Object getObjOldValue() {
		return objOldValue;
	}

	public void setObjOldValue(final Object objOldValue) {
		this.objOldValue = objOldValue;
	}

	@Override
	public String toString() {
		String dateAuthentificationFormat = DateUtils.formateDateJJMMAAAAhhmmssSSS(this.dateAuthentification);
		StringBuilder logText = new StringBuilder();
		logText.append(Constantes.SPACE).append(Constantes.OPEN_SQUARE_BRACKET);
		Joiner joiner = Joiner.on(Constantes.CLOSE_SQUARE_BRACKET + Constantes.OPEN_SQUARE_BRACKET).useForNull(Constantes.DASH);
		joiner.appendTo(logText, this.actionIhm, this.email, this.role, dateAuthentificationFormat, this.actionType, this.actionNature,
				this.ecranType, this.idMetier, this.obj, this.objOldValue);
		logText.append(Constantes.CLOSE_SQUARE_BRACKET);
		return logText.toString();
	}

}
