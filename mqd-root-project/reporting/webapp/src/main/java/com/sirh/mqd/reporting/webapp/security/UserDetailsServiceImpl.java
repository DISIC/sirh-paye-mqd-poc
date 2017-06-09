package com.sirh.mqd.reporting.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;
import com.sirh.mqd.reporting.webapp.factory.UserModelFactory;

@Service(ContextConstantes.USER_DETAILS_SERVICE)
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier(CoreConstantes.USER_SERVICE)
	private IUserService userService;

	@Autowired
	@Qualifier(ContextConstantes.MESSAGE)
	private IMessageSourceBundle messagesBundle;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final UserDTO user = userService.rechercherUtilisateur(username);
		if (user != null) {
			return UserModelFactory.createUserModel(user);
		}
		throw new UsernameNotFoundException(
				messagesBundle.getMessage("security.error.functional.no.username.found", new Object[] { username }));
	}

}
