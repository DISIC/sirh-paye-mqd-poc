package com.thalesgroup.stif.bouchon.producteur.redis.notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.thalesgroup.stif.commons.tampon.bc.BatchSubscriptionBC;
import com.thalesgroup.stif.commons.tampon.bc.GeneralMessageBC;
import com.thalesgroup.stif.commons.tampon.bc.PassageBC;
import com.thalesgroup.stif.commons.tampon.constantes.PersistenceTamponConstantes;

public abstract class AbstractNotify<T> {

	@Autowired
	@Qualifier(PersistenceTamponConstantes.BATCH_SUBSCRIPTION_BC)
	protected BatchSubscriptionBC batchSubscriptionBC;

	@Autowired
	@Qualifier(PersistenceTamponConstantes.PASSAGE_BC)
	protected PassageBC passageBC;

	@Autowired
	@Qualifier(PersistenceTamponConstantes.GENERAL_MESSAGE_BC)
	protected GeneralMessageBC generalMessageBC;

	/**
	 * Envoi une notification T dans le tampon
	 * 
	 * @param notification
	 */
	public abstract void send(T notification);
}
