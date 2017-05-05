package com.thalesgroup.stif.bouchon.producteur.redis.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.bouchon.producteur.redis.notify.GeneralMessageNotify;
import com.thalesgroup.stif.bouchon.producteur.redis.util.BouchonUtils;
import com.thalesgroup.stif.commons.echange.dto.pivot.GeneralMessage;
import com.thalesgroup.stif.commons.echange.dto.pivot.Message;
import com.thalesgroup.stif.commons.echange.dto.pivot.MessageText;
import com.thalesgroup.stif.commons.echange.dto.pivot.MessageType;
import com.thalesgroup.stif.commons.echange.dto.reception.NotifyGeneralMessageDTO;

@Component(ServicesConstantes.TEST_GM)
public class GeneralMessageTestCase extends AbstractTestCase implements ITestCase {

	@Autowired
	@Qualifier(ServicesConstantes.NOTIFICATION_GM)
	private GeneralMessageNotify gmNotify;

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application}")
	private Properties properties;

	/**
	 * Données du fichier CSV
	 */
	private List<String[]> data;

	/**
	 * Index des éléments du fichier CSV
	 *
	 * @author adile
	 *
	 */
	private enum CSV_INDEX {
		PARTICIPANT_REF, ITEM_IDENTIFIER, MONITORING_REF, VEHICLE_AT_STOP, STOP_POINTREF, LINE_REF, ROUTE_REF, DATA_FRAMEREF, DATA_VEHICLE_JOURNEYREF, DESTINATION_REF, DELAY, JOURNEY_PATTERN_REF, JOURNEY_PATTERN_NAME, INFO_MESSAGE_IDENTIFIER, INFO_CHANNEL_REF
	};

	@PostConstruct
	@Override
	public void init() throws IOException {

		//	on lit le contenu du fichier csv
		initFileReader(properties.getProperty("gmFilePath"));
		data = getFileData();
	}

	public void sendNotification() {

		NotifyGeneralMessageDTO notification = new NotifyGeneralMessageDTO();

		List<GeneralMessage> messageList = creerMessage();
		List<GeneralMessage> messageCancellationList = creerMessageCancellation();

		notification.getGeneralMessage().addAll(messageList);
		notification.getGeneralMessageCancellation().addAll(messageCancellationList);

		String adresse = properties.getProperty("gmUrlDiffuseur");
		List<String> addressNotify = new ArrayList<String>();
		addressNotify.add(adresse);

		gmNotify.send(notification);
		incrementeCountNotificationSend();

	}

	private List<GeneralMessage> creerMessage() {

		List<GeneralMessage> messageList = new ArrayList<GeneralMessage>();

		int min = Integer.valueOf(properties.getProperty("gmMessageMin"));
		int max = Integer.valueOf(properties.getProperty("gmMessageMax"));

		int nbMessage = BouchonUtils.randInt(min, max);

		for (int i = 0; i < nbMessage; i++) {

			int rdm = BouchonUtils.randInt(1, data.size() - 1);
			String[] element = data.get(rdm);

			GeneralMessage message = new GeneralMessage();

			message.setRecorderAtDate(new Date());
			message.setItemIdentifier(element[CSV_INDEX.ITEM_IDENTIFIER.ordinal()]);
			message.setInfoMessageIdentifier(element[CSV_INDEX.INFO_MESSAGE_IDENTIFIER.ordinal()]);
			message.setInfoMessageVersion(String.valueOf(BouchonUtils.randInt(1, 4)));
			message.setInfoChannelRef(element[CSV_INDEX.INFO_CHANNEL_REF.ordinal()]);
			message.setValidUntilDate(new Date(2016, 05, 12));

			List<String> destinationRef = new ArrayList<String>();
			destinationRef.add(element[CSV_INDEX.DESTINATION_REF.ordinal()]);
			message.setDestinationRef(destinationRef);

			List<String> lineRef = new ArrayList<String>();
			lineRef.add(element[CSV_INDEX.LINE_REF.ordinal()]);
			message.setLineref(lineRef);

			List<Message> messageContent = new ArrayList<Message>();
			Message e = new Message();
			e.setMessageText(new MessageText("Trafic normal sur l'ensemble du réseau.", "fr"));
			e.setMessageType(MessageType.TEXT_ONLY);
			messageContent.add(e);
			message.setMessage(messageContent);

			messageList.add(message);
		}

		return messageList;
	}

	private List<GeneralMessage> creerMessageCancellation() {

		List<GeneralMessage> messageList = new ArrayList<GeneralMessage>();

		int min = Integer.valueOf(properties.getProperty("gmMessageCancellationMin"));
		int max = Integer.valueOf(properties.getProperty("gmMessageCancellationMax"));

		int nbMessage = BouchonUtils.randInt(min, max);

		for (int i = 0; i < nbMessage; i++) {

			int rdm = BouchonUtils.randInt(1, data.size() - 1);
			String[] element = data.get(rdm);

			GeneralMessage message = new GeneralMessage();

			message.setRecorderAtDate(new Date());
			message.setItemIdentifier(element[CSV_INDEX.ITEM_IDENTIFIER.ordinal()]);
			message.setInfoMessageIdentifier(element[CSV_INDEX.INFO_MESSAGE_IDENTIFIER.ordinal()]);
			message.setInfoChannelRef(element[CSV_INDEX.INFO_CHANNEL_REF.ordinal()]);

			messageList.add(message);
		}

		return messageList;
	}

}
