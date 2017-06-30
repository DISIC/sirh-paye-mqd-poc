package com.sirh.mqd.commons.exchanges.factory.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.commons.exchanges.enums.RoleEnum;

/**
 * Factory de création des utilisateurs à partir des fichiers reçus en entrée du
 * système.
 *
 * @author alexandre
 */
public final class UserDTOFactory {

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private UserDTOFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + UserDTOFactory.class.getName());
	}

	public static UserDTO createUserDTO(final String login, final String password, final String email,
			final String prenom, final String nom, final String payLot, final String renoiRHCorpsCode,
			final String renoiRHAffectationCode, final String gestionnaireCode, final String ministere,
			final String service, final List<RoleEnum> roles) {
		final UserDTO user = new UserDTO();
		user.setUsername(StringUtils.normalizeSpace(login));
		user.setPassword(StringUtils.isNotBlank(password)
				? PASSWORD_ENCODER.encode(StringUtils.normalizeSpace(password)) : null);
		user.setPrenom(StringUtils.normalizeSpace(prenom));
		user.setNom(StringUtils.normalizeSpace(nom));

		user.setMinistere(StringUtils.normalizeSpace(ministere));
		user.setService(StringUtils.normalizeSpace(service));
		user.setEmail(StringUtils.normalizeSpace(email));

		if (StringUtils.isNotBlank(payLot)) {
			user.setPayLot(StringUtils.normalizeSpace(payLot));
		}
		if (StringUtils.isNotBlank(renoiRHCorpsCode)) {
			user.setCorpsCode(StringUtils.normalizeSpace(renoiRHCorpsCode));
		}
		if (StringUtils.isNotBlank(renoiRHAffectationCode)) {
			user.setAffectationCode(StringUtils.normalizeSpace(renoiRHAffectationCode));
		}
		if (StringUtils.isNotBlank(gestionnaireCode)) {
			user.setGestionnaireCode(StringUtils.normalizeSpace(gestionnaireCode));
		}
		user.setAuthenticationDate(null);

		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);

		user.setRoles(roles);

		return user;
	}
}
