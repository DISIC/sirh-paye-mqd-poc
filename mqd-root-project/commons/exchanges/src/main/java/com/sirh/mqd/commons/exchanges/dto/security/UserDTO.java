package com.sirh.mqd.commons.exchanges.dto.security;

import java.util.Date;
import java.util.List;

import com.sirh.mqd.commons.exchanges.enums.RoleEnum;

/**
 * DTO permettant d'avoir les informations d'authentification d'un utilisateur.
 *
 * @author alexandre
 */
public class UserDTO {

	private String username;

	private String password;

	private String prenom;

	private String nom;

	private String payLot;

	private String corpsCode;

	private String affectationCode;

	private String payGestionnaireCode;

	private Date authenticationDate;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	private List<RoleEnum> roles;

	public UserDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getAuthenticationDate() {
		return authenticationDate;
	}

	public void setAuthenticationDate(final Date authenticationDate) {
		this.authenticationDate = authenticationDate;
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

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPayGestionnaireCode() {
		return payGestionnaireCode;
	}

	public void setPayGestionnaireCode(final String payGestionnaireCode) {
		this.payGestionnaireCode = payGestionnaireCode;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(final boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(final boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(final boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public List<RoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(final List<RoleEnum> roles) {
		this.roles = roles;
	}

	public String[] getRolesAsStrings() {
		return this.roles.stream().map(role -> role.name()).toArray(String[]::new);
	}
}
