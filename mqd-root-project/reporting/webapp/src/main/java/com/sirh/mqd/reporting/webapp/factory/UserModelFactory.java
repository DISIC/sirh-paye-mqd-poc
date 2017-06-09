package com.sirh.mqd.reporting.webapp.factory;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.reporting.webapp.model.UserModel;

/**
 * Factory de création des utilisateurs à manipuler côté IHM.
 *
 * @author alexandre
 */
public final class UserModelFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private UserModelFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + UserModelFactory.class.getName());
	}

	public static UserModel createUserModel(final UserDTO user) {
		if (user != null) {
			final String username = user.getUsername();
			final String encryptPassword = user.getPassword();
			final String nom = user.getNom();
			final String prenom = user.getPrenom();
			final String payLot = user.getPayLot();
			final String corpsCode = user.getCorpsCode();
			final String affectationCode = user.getAffectationCode();
			final String payGestionnaireCode = user.getPayGestionnaireCode();
			final Date dateAuthentification = user.getAuthenticationDate();
			final boolean enabled = user.isEnabled();
			final boolean accountNonExpired = user.isAccountNonExpired();
			final boolean credentialsNonExpired = user.isCredentialsNonExpired();
			final boolean accountNonLocked = user.isAccountNonLocked();
			final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRolesAsStrings());
			return new UserModel(username, encryptPassword, prenom, nom, payLot, corpsCode, affectationCode,
					payGestionnaireCode, dateAuthentification, enabled, accountNonExpired, credentialsNonExpired,
					accountNonLocked, authorities);
		}
		return null;
	}
}
