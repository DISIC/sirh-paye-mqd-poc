package com.sirh.mqd.commons.storage.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.sirh.mqd.commons.exchanges.enums.RoleEnum;
import com.sirh.mqd.commons.storage.constantes.UserConstantes;

/**
 * Entité des utilisateurs
 *
 * @author alexandre
 */
@Document(collection = UserConstantes.COLLECTION_NAME)
public class UserEntity {

	@Id
	@Field(UserConstantes.COLONNE_USERNAME)
	private String username;

	@Field(UserConstantes.COLONNE_PASSWORD)
	private String password;

	@Field(UserConstantes.COLONNE_PRENOM)
	private String prenom;

	@Field(UserConstantes.COLONNE_NOM)
	private String nom;

	@Field(UserConstantes.COLONNE_DATE_AUTHENTIFICATION)
	private Date authenticationDate;

	@Field(UserConstantes.COLONNE_PAY_LOT)
	private String payLot;

	@Field(UserConstantes.COLONNE_CORPS_CODE)
	private String corpsCode;

	@Field(UserConstantes.COLONNE_AFFECTATION_CODE)
	private String affectationCode;

	@Field(UserConstantes.COLONNE_PAY_GESTIONNAIRE_CODE)
	private String payGestionnaireCode;

	@Field(UserConstantes.COLONNE_ACCOUNT_ENABLED)
	private boolean enabled;

	@Field(UserConstantes.COLONNE_ACCOUNT_NON_EXPIRED)
	private boolean accountNonExpired;

	@Field(UserConstantes.COLONNE_ACCOUNT_NON_LOCKED)
	private boolean accountNonLocked;

	@Field(UserConstantes.COLONNE_CREDENTIALS_NON_EXPIRED)
	private boolean credentialsNonExpired;

	@Field(UserConstantes.COLONNE_ROLES)
	private List<RoleEnum> roles;

	public UserEntity() {
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

	public List<RoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(final List<RoleEnum> roles) {
		this.roles = roles;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
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
}
