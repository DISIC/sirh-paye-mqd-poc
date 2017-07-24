package com.sirh.mqd.supplier.routing.calendrier;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.sirh.mqd.supplier.core.calendrier.CalendrierGestionService;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.routing.constantes.RoutingConstantes;

/**
 * Bean permettant de réceptionner et de manipuler des fichiers CSV déposés sur
 * le serveur SFTP
 */
@Component(RoutingConstantes.CALENDRIER_GESTION_BEAN)
public class CalendrierGestionBean {

	@Autowired
	@Qualifier(CoreConstantes.CALENDRIER_GESTION_SERVICE)
	private CalendrierGestionService calendrierGestionService;

	/**
	 * Méthode permettant de manipuler un fichier CSV des calendriers de gestion
	 * du DGAC en entrée et de stocker les informations en base de données.
	 *
	 * @param message
	 *            le message contenant le fichier
	 */
	public void manageCSVFileFromDGAC(final Message<File> message) throws MessagingException {
		this.calendrierGestionService.storeCSVDataFromDGAC(message.getHeaders().getTimestamp(), message.getPayload());
		message.getPayload().delete();
	}

	/**
	 * Méthode permettant de manipuler un fichier CSV des calendriers de gestion
	 * du MSO en entrée et de stocker les informations en base de données.
	 *
	 * @param message
	 *            le message contenant le fichier
	 */
	public void manageCSVFileFromMSO(final Message<File> message) throws MessagingException {
		// TODO Attente achèvement du calendrier de gestion du MSO
		message.getPayload().delete();
	}
}
