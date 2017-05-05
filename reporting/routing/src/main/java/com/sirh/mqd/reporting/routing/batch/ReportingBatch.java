package com.sirh.mqd.reporting.routing.batch;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.reporting.routing.constantes.RoutingConstantes;

/**
 * Bean permettant de réception des demandes de requete Siri déposées par les
 * batchs d'administration
 */
@Component(RoutingConstantes.REPORTING_BATCH)
public class ReportingBatch {

	/*
	 * @Autowired
	 * 
	 * @Qualifier(CoreConstantes.RAPPORT_SERVICE) private RapportService
	 * subscriptionService;
	 */

	/*
	 * @Autowired
	 * 
	 * @Qualifier(RoutingConstantes.SEND_FILE_CHANNEL) private MessageChannel
	 * sendFile;
	 */

	@Value("#{application}")
	private Properties applicationProperties;

	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(ReportingBatch.class);
}
