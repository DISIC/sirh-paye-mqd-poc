package com.thalesgroup.stif.bouchon.producteur.notification;

import java.util.Date;
import java.util.UUID;

import com.thalesgroup.stif.commons.utils.DateUtils;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProducerResponseEndpointStructure;
import com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure;

/**
 * Crée la structure global utilisée pour les notifications
 * 
 * @author thales
 * 
 */
public abstract class GeneralNotification {

	/**
	 * Crée une ExtensionStructure
	 * 
	 * @return
	 */
	public ExtensionsStructure createExtentionStructure() {
		ExtensionsStructure ext = new ExtensionsStructure();
		return ext;

	}

	/**
	 * Crée un ProducerResponseEndpointStructure qui est global pour chaque notification (sauf GetSiriService)
	 * 
	 * @param serviceRequestInfo
	 * @return
	 */
	public ProducerResponseEndpointStructure createProducerResponseEndpointStructure(final WsServiceRequestInfoStructure serviceRequestInfo) {
		ProducerResponseEndpointStructure producerResponse = new ProducerResponseEndpointStructure();
		producerResponse.setAddress("adresse");
		producerResponse.setProducerRef(NotificationUtils.createParticipantRefStructure("SNCF"));
		producerResponse.setRequestMessageRef(NotificationUtils.createMessageRefStructure(serviceRequestInfo.getMessageIdentifier()
				.getValue()));
		producerResponse.setResponseMessageIdentifier(NotificationUtils.createMessageQualifierStructure(UUID.randomUUID().toString()));
		producerResponse.setResponseTimestamp(DateUtils.convertDateToXmlGregorianCalendar(new Date()));

		return producerResponse;

	}
}
