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

	public static UserDTO createUserDTO(final String email, final String password, final String prenom,
			final String nom, final String payLot, final String renoiRHCorpsCode, final String renoiRHAffectationCode,
			final String payGestionnaireCode, final List<RoleEnum> roles) {
		final UserDTO user = new UserDTO();
		user.setUsername(email);
		user.setPassword(PASSWORD_ENCODER.encode(password));
		user.setPrenom(prenom);
		user.setNom(nom);

		if (StringUtils.isNotBlank(payLot)) {
			user.setPayLot(payLot);
		}
		if (StringUtils.isNotBlank(renoiRHCorpsCode)) {
			user.setCorpsCode(renoiRHCorpsCode);
		}
		if (StringUtils.isNotBlank(renoiRHAffectationCode)) {
			user.setAffectationCode(renoiRHAffectationCode);
		}
		if (StringUtils.isNotBlank(payGestionnaireCode)) {
			user.setPayGestionnaireCode(payGestionnaireCode);
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
