package com.sirh.mqd.reporting.webapp.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.common.base.Joiner;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Model d'utilisateur connecté à manipuler dans la partie Vue
 *
 * @author alexandre
 */
public class UserModel extends User {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -4609340482509932498L;

	private final String prenom;

	private final String nom;

	private String payLot;

	private String corpsCode;

	private String affectationCode;

	private String gestionnaireCode;

	private String service;

	private Date dateAuthentification;

	public UserModel(final String username, final String password, final String prenom, final String nom,
			final String payLot, final String corpsCode, final String affectationCode, final String gestionnaireCode,
			final String service, final Date dateAuthentification, final boolean enabled,
			final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked,
			final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.prenom = prenom;
		this.nom = nom;
		this.payLot = payLot;
		this.corpsCode = corpsCode;
		this.affectationCode = affectationCode;
		this.gestionnaireCode = gestionnaireCode;
		this.service = service;
		this.dateAuthentification = DateUtils.clonerDate(dateAuthentification);
	}

	public String getPayLot() {
		return payLot;
	}

	public void setPayLot(final String payLot) {
		this.payLot = payLot;
	}

	public String getCorpsCode() {
		return corpsCode;
	}

	public void setCorpsCode(final String corpsCode) {
		this.corpsCode = corpsCode;
	}

	public String getAffectationCode() {
		return affectationCode;
	}

	public void setAffectationCode(final String affectationCode) {
		this.affectationCode = affectationCode;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getGestionnaireCode() {
		return gestionnaireCode;
	}

	public void setGestionnaireCode(final String gestionnaireCode) {
		this.gestionnaireCode = gestionnaireCode;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public Date getDateAuthentification() {
		return dateAuthentification;
	}

	public void setDateAuthentification(final Date dateAuthentification) {
		this.dateAuthentification = DateUtils.clonerDate(dateAuthentification);
	}

	/**
	 * Méthode permettant de remonter la liste des roles associée à
	 * l'utilisateur au format "ROLE_1:ROLE_2:ROLE_3"
	 *
	 * @return la représentation en chaîne de caractères de la liste des roles
	 */
	public String getRolesAsString() {
		final StringBuilder authorities = new StringBuilder();
		final Joiner joiner = Joiner.on(Constantes.COLON).useForNull("ROLE UNDEFINED");
		this.getAuthorities().forEach((authority) -> {
			joiner.appendTo(authorities, new Object[] { authority.getAuthority() });
		});
		return authorities.toString();
	}
}
