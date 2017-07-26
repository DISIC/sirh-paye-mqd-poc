package com.sirh.mqd.reporting.webapp.utils;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.model.UserModel;

/**
 * Classe générique de gestion des informations d'authentification de
 * l'utilisateur connecté
 *
 * @author alexandre
 */
@ManagedBean(name = ContextConstantes.LOGIN_UTILS)
@SessionScoped
public class LoginUtils implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6484931640884340862L;

	public String getCurrentUsername() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getUsername();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserLastname() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getNom();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserFirstname() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getPrenom();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserPayLot() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getPayLot();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserCorpsCode() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getCorpsCode();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserAffectationCode() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getAffectationCode();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserGestionnaireCode() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getGestionnaireCode();
		}
		return "USER UNDEFINED";
	}

	public String getCurrentUserService() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getService();
		}
		return "USER UNDEFINED";
	}

	public InteractionSirhEnum getCurrentUserMinistere() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getMinistere();
		}
		return null;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getAuthorities();
		}
		return null;
	}

	public String getRolesAsString() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getRolesAsString();
		}
		return "USER UNDEFINED";
	}

	public String getDateConnexion() {
		final UserModel authenticatedUser = (UserModel) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null && authenticatedUser.getDateAuthentification() != null) {
			return DateUtils.formateDateJJMMAAAAhhmmss(authenticatedUser.getDateAuthentification());
		}
		return "USER UNDEFINED";
	}
}
