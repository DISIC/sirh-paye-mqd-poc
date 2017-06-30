package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.commons.storage.entity.UserEntity;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Factory de création des entités des utilisateurs.
 *
 * @author alexandre
 */
public class UserEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private UserEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + UserEntityFactory.class.getName());
	}

	public static UserDTO createUserDTO(final UserEntity entity) {
		UserDTO user = null;
		if (entity != null) {
			user = new UserDTO();
			user.setUsername(entity.getUsername());
			user.setPassword(entity.getPassword());

			user.setPrenom(entity.getPrenom());
			user.setNom(entity.getNom());
			user.setEmail(entity.getEmail());

			user.setMinistere(entity.getMinistere());
			user.setService(entity.getService());

			user.setAuthenticationDate(DateUtils.clonerDate(entity.getAuthenticationDate()));

			user.setPayLot(entity.getPayLot());
			user.setAffectationCode(entity.getAffectationCode());
			user.setCorpsCode(entity.getCorpsCode());
			user.setGestionnaireCode(entity.getGestionnaireCode());

			user.setAccountNonExpired(entity.isAccountNonExpired());
			user.setAccountNonLocked(entity.isAccountNonLocked());
			user.setCredentialsNonExpired(entity.isCredentialsNonExpired());
			user.setEnabled(entity.isEnabled());
			user.setRoles(entity.getRoles());
		}
		return user;
	}

	public static UserEntity createUserEntity(final UserDTO user) {
		UserEntity entity = null;
		if (user != null) {
			entity = new UserEntity();
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());

			entity.setPrenom(user.getPrenom());
			entity.setNom(user.getNom());
			entity.setEmail(user.getEmail());

			entity.setMinistere(user.getMinistere());
			entity.setService(user.getService());

			entity.setAuthenticationDate(DateUtils.clonerDate(user.getAuthenticationDate()));

			entity.setPayLot(user.getPayLot());
			entity.setAffectationCode(user.getAffectationCode());
			entity.setCorpsCode(user.getCorpsCode());
			entity.setGestionnaireCode(user.getGestionnaireCode());

			entity.setAccountNonExpired(user.isAccountNonExpired());
			entity.setAccountNonLocked(user.isAccountNonLocked());
			entity.setCredentialsNonExpired(user.isCredentialsNonExpired());
			entity.setEnabled(user.isEnabled());
			entity.setRoles(user.getRoles());
		}
		return entity;
	}
}
