package com.thalesgroup.stif.bouchon.producteur.redis.notify;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.commons.echange.dto.reception.NotifyStopMonitoringDTO;

@Component(ServicesConstantes.NOTIFICATION_SM)
public class StopMonitoringNotify extends AbstractNotify<NotifyStopMonitoringDTO> {

	@Override
	public void send(final NotifyStopMonitoringDTO notification) {

		batchSubscriptionBC.deposerNotifyStopMonitoring(notification, "SM");

		passageBC.ajouterPassageAbonnement(notification.getPassageAMaj(), (long) 28, TimeUnit.HOURS);

	}

}