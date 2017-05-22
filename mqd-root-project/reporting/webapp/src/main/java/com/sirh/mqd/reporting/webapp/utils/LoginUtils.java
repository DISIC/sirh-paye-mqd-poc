package com.sirh.mqd.reporting.webapp.utils;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;

/**
 * Classe générique de gestion des informations d'authentification de
 * l'utilisateur connecté
 *
 * @author alexandre
 */
@Named(ContextConstantes.LOGIN_UTILS)
@SessionScoped
public class LoginUtils implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 6484931640884340862L;

	/**
	 * @return the currentUsername
	 */
	public String getCurrentUsername() {
		final UserDetails authenticatedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (authenticatedUser != null) {
			return authenticatedUser.getUsername();
		} else {
			return "USER UNDEFINED";
		}
	}
}
