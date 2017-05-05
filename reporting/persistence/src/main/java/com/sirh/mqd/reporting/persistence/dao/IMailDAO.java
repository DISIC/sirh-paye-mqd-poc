package com.sirh.mqd.reporting.persistence.dao;

import java.util.Date;

import javax.mail.MessagingException;

public interface IMailDAO {

	/**
	 * envoiMails
	 *
	 * @param listeDiffusion
	 * @param rapportName
	 * @throws MessagingException
	 */
	void envoiMails(String listeDiffusion, String rapportName, final Date startDate, final Date endDate) throws MessagingException;

}
