package com.sirh.mqd.reporting.persistence.bc;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.dao.IMailDAO;

/**
 * Implémentation du BusinessController permettant la manipulation des données provenant d'elasticsearch
 *
 * @author adile
 *
 */
@Service(PersistenceReportingConstantes.MAIL_BC)
public class MailBC {

	@Autowired
	private IMailDAO mailDao;

	public void envoyerMails(final String listeDiffusion, final String referenceRapport, final Date startDate, final Date endDate)
			throws MessagingException {
		mailDao.envoiMails(listeDiffusion, referenceRapport, startDate, endDate);
	}
}
