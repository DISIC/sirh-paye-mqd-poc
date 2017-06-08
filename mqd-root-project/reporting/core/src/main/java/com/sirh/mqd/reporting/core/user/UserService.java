package com.sirh.mqd.reporting.core.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.commons.storage.bc.UserBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.USER_SERVICE)
public class UserService implements IUserService {

	@Autowired
	@Qualifier(PersistenceConstantes.USER_BC)
	private UserBC userBC;

	@Override
	public UserDTO rechercherUtilisateur(final String username) {
		return userBC.rechercherUtilisateur(username);
	}

	@Override
	public void ajouterUtilisateur(final UserDTO user) {
		userBC.ajouterUtilisateur(user);
	}

	@Override
	public void modifierDateDerniereConnexion(final String username, final Date lastConnection) {
		userBC.modifierDateDerniereConnexion(username, lastConnection);
	}
}
