package com.sirh.mqd.supplier.routing.sirh;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.sirh.SirhMsoService;
import com.sirh.mqd.supplier.routing.constantes.RoutingConstantes;

/**
 * Bean permettant de réceptionner et de manipuler des fichiers CSV déposés sur
 * le serveur SFTP
 */
@Component(RoutingConstantes.MSO_BEAN)
public class MsoBean {

	/**
	 * Service de gestion des inputs du SIRH MSO
	 */
	@Autowired
	@Qualifier(CoreConstantes.SIRH_MSO_SERVICE)
	private SirhMsoService sirhMsoService;

	@Autowired
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Méthode permettant de manipuler un fichier CSV GA-PAY du MSO en entrée et
	 * de stocker les informations en base de données.
	 *
	 * @param message
	 *            le message contenant le fichier
	 *
	 */
	public void manageCSVFile(final Message<File> message) throws MessagingException {
		this.sirhMsoService.storeCSVData(message.getHeaders().getTimestamp(), message.getPayload());
	}
}
