package com.sirh.mqd.commons.storage.bc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IUserDAO;
import com.sirh.mqd.commons.storage.factory.UserEntityFactory;

/**
 * Implémentation du BusinessController permettant de gérer utilisateurs.
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.USER_BC)
public class UserBC {

	@Autowired
	@Qualifier(PersistenceConstantes.USER_DAO)
	private IUserDAO userDAO;

	public UserDTO rechercherUtilisateur(final String username) {
		return UserEntityFactory.createUserDTO(userDAO.selectUser(username));
	}

	public void ajouterUtilisateur(final UserDTO user) {
		userDAO.insertUser(UserEntityFactory.createUserEntity(user));
	}

	public void modifierDateDerniereConnexion(final String username, final Date lastConnection) {
		userDAO.updateUserAuthenticationDate(username, lastConnection);
	}
}
