package com.thalesgroup.stif.bouchon.producteur.redis.notify;

import org.springframework.stereotype.Component;

import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.commons.echange.dto.pivot.GeneralMessage;
import com.thalesgroup.stif.commons.echange.dto.reception.NotifyGeneralMessageDTO;

@Component(ServicesConstantes.NOTIFICATION_GM)
public class GeneralMessageNotify extends AbstractNotify<NotifyGeneralMessageDTO> {

	@Override
	public void send(final NotifyGeneralMessageDTO notification) {

		batchSubscriptionBC.deposerNotifyGeneralMessage(notification);

		for (GeneralMessage generalMessage : notification.getGeneralMessage()) {

			generalMessageBC.ajouterGeneralMessage(generalMessage, 3600000);

		}

	}

}