package com.sirh.mqd.reporting.routing.batch;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.RapportService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.routing.constantes.RoutingConstantes;
import com.thalesgroup.stif.commons.echange.dto.reception.RapportTriggerDTO;

/**
 * Bean permettant de réception des demandes de requete Siri déposées par les batchs d'administration
 */
@Component(RoutingConstantes.REPORTING_BATCH)
public class ReportingBatch {

	@Autowired
	@Qualifier(CoreConstantes.RAPPORT_SERVICE)
	private RapportService subscriptionService;

	@Autowired
	@Qualifier(RoutingConstantes.SEND_FILE_CHANNEL)
	private MessageChannel sendFile;

	@Value("#{application}")
	private Properties applicationProperties;

	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(ReportingBatch.class);

	public void getReporting(final Message<RapportTriggerDTO> message) {
		final RapportTriggerDTO requestDTO = message.getPayload();
		final ReferenceRapport referenceRapport = ReferenceRapport.valueOf(requestDTO.getReferenceRapport());
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(" Reception d'une demande de rapport " + referenceRapport.name()));
		final String listeDiffusion = requestDTO.getListeDiffusion();
		final Date endDate = DateUtils.getCalendarInstance().getTime();
		final Long startTime = endDate.getTime() - requestDTO.getPlage();
		final Date startDate = new Date(startTime);
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(" Lancement du processus de création du rapport. Plage horaire de "
				+ startDate.toString() + " à " + endDate.toString()));
		subscriptionService.creerRapport(referenceRapport, startDate, endDate);
		final Date now = DateUtils.getCalendarInstance().getTime();
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final String rapportName = referenceRapport.name() + "-" + sdf.format(now);
		//STIFSCRUM-2549
		if (isReportValid(rapportName)) {
			subscriptionService.envoyerMails(listeDiffusion, rapportName, startDate, endDate);
			LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Rapport " + referenceRapport.name() + "-" + sdf.format(now)
					+ " envoyé par mail aux abonnés."));
		} else {
			LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Rapport " + referenceRapport.name() + "-" + sdf.format(now)
					+ " non envoyé par mail aux abonnés car non généré."));
		}

	}

	/**
	 * Channel d'envoie du fichier vers un serveur distant.
	 * 
	 * @param rapportName
	 *            nom du fichier du rapport
	 */
	//STIFSCRUM-2549
	private boolean isReportValid(final String rapportName) {

		boolean valid = false;
		final String repertoire = applicationProperties.getProperty("repertoire.rapports");
		final File rapport = new File(repertoire + "/" + rapportName + ".pdf");

		if (rapport.exists() == true) {
			sendFile.send(MessageBuilder.withPayload(rapport).build());
			valid = true;
		}

		return valid;
	}
}
