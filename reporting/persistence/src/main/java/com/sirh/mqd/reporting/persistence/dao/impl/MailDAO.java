package com.sirh.mqd.reporting.persistence.dao.impl;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.dao.IMailDAO;

@Qualifier("mailDAO")
@Component
public class MailDAO implements IMailDAO {

	@Value("#{rapportMail}")
	private Properties rapportProperties;

	@Value("#{application}")
	private Properties applicationProperties;

	private JavaMailSender mailSender;

	public void setMailSender(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void envoiMails(final String listeDiffusion, final String rapportName, final Date startDate, final Date endDate)
			throws MessagingException {

		String sujet = rapportProperties.getProperty("sujet_mail");
		String corps = rapportProperties.getProperty("corps_mail");

		if (rapportName != null && startDate != null && endDate != null) {
			sujet = sujet.replace("[nrapport]", rapportName.replace("RAPPORT_", ""));
			sujet = sujet.replace("[plage temporelle rapport]",
					DateUtils.formateDateJJMMAAAAhhmm(startDate) + "-" + DateUtils.formateDateJJMMAAAAhhmm(endDate));
			corps = corps.replace("[nrapport]", rapportName.replace("RAPPORT_", ""));
			corps = corps.replace("[plage temporelle rapport]",
					DateUtils.formateDateJJMMAAAAhhmm(startDate) + "-" + DateUtils.formateDateJJMMAAAAhhmm(endDate));
		}

		final String repertoire = applicationProperties.getProperty("repertoire.rapports");
		final JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setDefaultEncoding(applicationProperties.getProperty("mail.default.encoding"));
		sender.setUsername(applicationProperties.getProperty("mail.smtp.user"));
		sender.setPassword(applicationProperties.getProperty("mail.smtp.password"));
		sender.setHost(applicationProperties.getProperty("mail.smtp.host"));
		sender.setPort(Integer.parseInt(applicationProperties.getProperty("mail.smtp.port")));
		sender.setProtocol(applicationProperties.getProperty("mail.smtp.protocol"));
		sender.setJavaMailProperties(applicationProperties);
		final MimeMessage message = mailSender.createMimeMessage();

		final MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setCc(listeDiffusion.split(PersistenceReportingConstantes.SEPARATOR_PATTERN));
		helper.setSubject(sujet);
		helper.setText(corps);
		helper.setFrom(new InternetAddress(applicationProperties.getProperty("mail.from")));

		final FileSystemResource file = new FileSystemResource(new File(repertoire + "/" + rapportName + ".pdf"));
		helper.addAttachment(rapportName + ".pdf", file);

		try {
			mailSender.send(message);
		} catch (final MailException mailNotSent) {
			throw new SendFailedException(mailNotSent.getMessage());
		}

	}

}
