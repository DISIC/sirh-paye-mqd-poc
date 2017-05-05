package com.sirh.mqd.reporting.core;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.enums.ExceptionType;
import com.sirh.mqd.commons.traces.enums.LogType;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.FacadeLogs;
import com.sirh.mqd.commons.traces.logs.LogExceptionFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.core.generation.AbstractRapport;
import com.sirh.mqd.reporting.core.generation.Rapport10;
import com.sirh.mqd.reporting.core.generation.Rapport2;
import com.sirh.mqd.reporting.core.generation.Rapport3;
import com.sirh.mqd.reporting.core.generation.Rapport4;
import com.sirh.mqd.reporting.core.generation.Rapport5;
import com.sirh.mqd.reporting.core.generation.Rapport6;
import com.sirh.mqd.reporting.core.generation.Rapport7;
import com.sirh.mqd.reporting.core.generation.Rapport8;
import com.sirh.mqd.reporting.core.generation.Rapport9;
import com.sirh.mqd.reporting.persistence.bc.MailBC;

/**
 * Classe gérant la création des rappors
 *
 * @see doc.story.ref1157
 *
 */
@Service(CoreConstantes.RAPPORT_SERVICE)
public class RapportService {

	@Autowired
	private AbstractRapport rapport1;

	@Autowired
	private Rapport2 rapport2;

	@Autowired
	private Rapport3 rapport3;

	@Autowired
	private Rapport4 rapport4;

	@Autowired
	private Rapport5 rapport5;

	@Autowired
	private Rapport9 rapport9;

	@Autowired
	private Rapport6 rapport6;

	@Autowired
	private Rapport8 rapport8;

	@Autowired
	private Rapport10 rapport10;

	@Autowired
	private Rapport7 rapport7;

	@Autowired
	private MailBC mailBC;

	/**
	 *
	 * La facade de log de l'application
	 */
	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(RapportService.class);

	public void creerRapport(final ReferenceRapport reference, final Date startDate, final Date endDate) {

		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(" Selection du rapport " + reference.name()));
		switch (reference) {
			case RAPPORT_1:
				rapport1.build(startDate, endDate);
				break;
			case RAPPORT_2:
				rapport2.build(startDate, endDate);
				break;
			case RAPPORT_3:
				rapport3.build(startDate, endDate);
				break;
			case RAPPORT_4:
				rapport4.build(startDate, endDate);
				break;
			case RAPPORT_5:
				rapport5.build(startDate, endDate);
				break;
			case RAPPORT_6:
				rapport6.build(startDate, endDate);
				break;
			case RAPPORT_7:
				rapport7.build(startDate, endDate);
				break;
			case RAPPORT_8:
				rapport8.build(startDate, endDate);
				break;
			case RAPPORT_9:
				rapport9.build(startDate, endDate);
				break;
			case RAPPORT_10:
				rapport10.build(startDate, endDate);
				break;

			default:
				break;
		}

	}

	/**
	 * @param listeDiffusion
	 * @param referenceRapport
	 */
	public void envoyerMails(final String listeDiffusion, final String referenceRapport, final Date startDate, final Date endDate) {
		try {
			LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow(" Envoi du rapport " + referenceRapport));
			mailBC.envoyerMails(listeDiffusion, referenceRapport, startDate, endDate);
		} catch (final MessagingException e) {

			LOG.logException(LogExceptionFactory.getLogException(LogType.OTHER, this.getClass().getName(),
					ExceptionType.MESSAGING_EXCEPTION, FacadeLogs.getTraceString(e)));
		}
	}

}
