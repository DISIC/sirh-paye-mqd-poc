package com.sirh.mqd.supplier.routing.pay;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.pay.PayService;
import com.sirh.mqd.supplier.routing.constantes.RoutingConstantes;

/**
 * Bean permettant de réceptionner et de manipuler des fichiers CSV déposés sur
 * le serveur SFTP
 */
@Component(RoutingConstantes.PAY_BEAN)
public class PayBean {

	@Autowired
	@Qualifier(CoreConstantes.PAY_SERVICE)
	private PayService payService;

	/*
	 * @Autowired
	 *
	 * @Qualifier(RoutingConstantes.SEND_FILE_CHANNEL) private MessageChannel
	 * sendFile;
	 */

	@Value("#{application}")
	private Properties applicationProperties;

	@Autowired
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Méthode permettant de manipuler un fichier CSV GA-PAY du MSO ou un
	 * fichier TXT GA-PAY de la DGAC en entrée et de stocker les informations en
	 * base de données.
	 *
	 * @param message
	 *            le message contenant le fichier
	 */
	public void manageFiles(final Message<File> message) throws MessagingException {
		this.payService.storeData(message.getHeaders().getTimestamp(), message.getPayload());
		message.getPayload().delete();
	}
}
