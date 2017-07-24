package com.sirh.mqd.supplier.routing.user;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.user.UserService;
import com.sirh.mqd.supplier.routing.constantes.RoutingConstantes;

/**
 * Bean permettant de réceptionner et de manipuler des fichiers CSV déposés sur
 * le serveur SFTP
 */
@Component(RoutingConstantes.USER_BEAN)
public class UserBean {

	@Autowired
	@Qualifier(CoreConstantes.USER_SERVICE)
	private UserService userService;

	/**
	 * Méthode permettant de manipuler un fichier CSV contenant les informations
	 * sur les comptes utilisateurs en entrée et de stocker les informations en
	 * base de données.
	 *
	 * @param message
	 *            le message contenant le fichier
	 */
	public void manageCSVFile(final Message<File> message) throws MessagingException {
		this.userService.storeCSVData(message.getHeaders().getTimestamp(), message.getPayload());
		message.getPayload().delete();
	}
}
