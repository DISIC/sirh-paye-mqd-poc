/**
 *
 */
package com.thalesgroup.stif.bouchon.producteur;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalesgroup.stif.bouchon.producteur.notification.NotificationUtils;
import com.thalesgroup.stif.bouchon.producteur.notification.StopMonitoringNotificationBuilder;
import com.thalesgroup.stif.commons.utils.DateUtils;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CapabilitiesRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CapabilitiesResponseStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CheckStatusRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CheckStatusResponseBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionLinksDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionLinksDiscoveryRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionMonitoringRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionTimetableRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConsumerRequestEndpointStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DataSupplyRequestBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.EstimatedTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.EstimatedTimetableRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.FacilityMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.FacilityMonitoringRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.GeneralMessageDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.GeneralMessageDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.GeneralMessageRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.LinesDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.LinesDiscoveryRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProducerResponseEndpointStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProductionTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProductionTimetableRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.RequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ResponseEndpointStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ServiceDeliveryBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ServiceDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ServiceRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SiriSubscriptionRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SituationExchangeDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SituationExchangeRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringMultipleRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopPointsDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopPointsDiscoveryRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopTimetableRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SubscriptionResponseBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.TerminateSubscriptionRequestBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.TerminateSubscriptionResponseStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.VehicleMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.VehicleMonitoringRequestStructure;
import com.thalesgroup.stif.siri.v24.ws.CheckStatusError;
import com.thalesgroup.stif.siri.v24.ws.ConnectionLinksDiscoveryError;
import com.thalesgroup.stif.siri.v24.ws.ConnectionMonitoringError;
import com.thalesgroup.stif.siri.v24.ws.ConnectionTimetableError;
import com.thalesgroup.stif.siri.v24.ws.DataSupplyError;
import com.thalesgroup.stif.siri.v24.ws.DeleteSubscriptionError;
import com.thalesgroup.stif.siri.v24.ws.EstimatedTimetableError;
import com.thalesgroup.stif.siri.v24.ws.FacilityMonitoringError;
import com.thalesgroup.stif.siri.v24.ws.GeneralMessageError;
import com.thalesgroup.stif.siri.v24.ws.GetCapabilitiesError;
import com.thalesgroup.stif.siri.v24.ws.LinesDiscoveryError;
import com.thalesgroup.stif.siri.v24.ws.ProductionTimetableError;
import com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort;
import com.thalesgroup.stif.siri.v24.ws.SituationExchangeError;
import com.thalesgroup.stif.siri.v24.ws.StopMonitoringError;
import com.thalesgroup.stif.siri.v24.ws.StopPointsDiscoveryError;
import com.thalesgroup.stif.siri.v24.ws.StopTimetableError;
import com.thalesgroup.stif.siri.v24.ws.SubscriptionError;
import com.thalesgroup.stif.siri.v24.ws.VehicleMonitoringError;
import com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure;
import com.thalesgroup.stif.siri.v24.ws.WsSubscriptionRequestInfoStructure;

/**
 * @author thales
 *
 */
public class SiriProducerRcpPortImpl implements SiriProducerRpcPort {

	/**
	 * Logger
	 */
	private static Logger LOG = LoggerFactory.getLogger(SiriProducerRcpPortImpl.class);

	@Autowired
	private final StopMonitoringNotificationBuilder smBuilder = new StopMonitoringNotificationBuilder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#stopPointsDiscovery (com.thalesgroup.stif.siri.v24.uk.org.siri.siri.
	 * StopPointsDiscoveryRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder,
	 * javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "StopPointsDiscovery", action = "StopPointsDiscovery")
	public void stopPointsDiscovery(@WebParam(partName = "Request", name = "Request") final StopPointsDiscoveryRequestStructure request,
			@WebParam(partName = "RequestExtension", name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<StopPointsDeliveryStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws StopPointsDiscoveryError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getSiriService(com
	 * .thalesgroup.stif.siri.v24.uk.org.siri.siri.ServiceRequestStructure)
	 */
	@Override
	@WebResult(name = "Answer", targetNamespace = "http://wsdl.siri.org.uk", partName = "Answer")
	@WebMethod(operationName = "GetSiriService", action = "GetSiriService")
	public ServiceDeliveryStructure getSiriService(@WebParam(partName = "Request", name = "Request") final ServiceRequestStructure request) {

		ServiceDeliveryStructure response = new ServiceDeliveryStructure();

		response.setResponseMessageIdentifier(NotificationUtils.createMessageQualifierStructure(UUID.randomUUID().toString()));
		response.setStatus(Boolean.TRUE);
		response.setAddress("localhost");
		response.setProducerRef(NotificationUtils.createParticipantRefStructure("SNCF"));
		response.setResponseTimestamp(DateUtils.convertDateToXmlGregorianCalendar(new Date()));
		response.setSrsName("srsName");
		response.setDelegatorAddress("address");
		response.setDelegatorRef(NotificationUtils.createParticipantRefStructure("delegatorRef"));
		response.setMoreData(Boolean.FALSE);
		response.setRequestMessageRef(NotificationUtils.createMessageRefStructure(request.getMessageIdentifier().getValue()));

		for (StopMonitoringRequestStructure req : request.getStopMonitoringRequest()) {

			response.getStopMonitoringDelivery().add(smBuilder.createStopMonitoringDelivery(req).getStopMonitoringDelivery().get(0));
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getConnectionMonitoring
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.ConnectionMonitoringRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetConnectionMonitoring", action = "GetConnectionMonitoring")
	public void getConnectionMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final ConnectionMonitoringRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<ConnectionMonitoringDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws ConnectionMonitoringError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#subscribe(com.
	 * thalesgroup.stif.siri.v24.ws.WsSubscriptionRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.SiriSubscriptionRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "Subscribe", action = "Subscribe")
	public void subscribe(
			@WebParam(partName = "SubscriptionRequestInfo", name = "SubscriptionRequestInfo") final WsSubscriptionRequestInfoStructure subscriptionRequestInfo,
			@WebParam(partName = "Request", name = "Request") final SiriSubscriptionRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "SubscriptionAnswerInfo",
					mode = Mode.OUT,
					name = "SubscriptionAnswerInfo") final Holder<ResponseEndpointStructure> subscriptionAnswerInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<SubscriptionResponseBodyStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws SubscriptionError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getProductionTimetable
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.ProductionTimetableRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetProductionTimetable", action = "GetProductionTimetable")
	public void getProductionTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final ProductionTimetableRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<ProductionTimetableDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws ProductionTimetableError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#linesDiscovery(com .thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.LinesDiscoveryRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "LinesDiscovery", action = "LinesDiscovery")
	public void linesDiscovery(@WebParam(partName = "Request", name = "Request") final LinesDiscoveryRequestStructure request, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
			partName = "Answer",
			mode = Mode.OUT,
			name = "Answer") final Holder<LinesDeliveryStructure> answer, @WebParam(
			partName = "AnswerExtension",
			mode = Mode.OUT,
			name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws LinesDiscoveryError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getStopTimetable
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.StopTimetableRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetStopTimetable", action = "GetStopTimetable")
	public void getStopTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final StopTimetableRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<StopTimetableDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws StopTimetableError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getSituationExchange
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.SituationExchangeRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetSituationExchange", action = "GetSituationExchange")
	public void getSituationExchange(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final SituationExchangeRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<SituationExchangeDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws SituationExchangeError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getConnectionTimetable
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.ConnectionTimetableRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetConnectionTimetable", action = "GetConnectionTimetable")
	public void getConnectionTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final ConnectionTimetableRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<ConnectionTimetableDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws ConnectionTimetableError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#dataSupply(com. thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.ConsumerRequestEndpointStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.DataSupplyRequestBodyStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "DataSupply", action = "DataSupply")
	public void dataSupply(
			@WebParam(partName = "DataSupplyRequestInfo", name = "DataSupplyRequestInfo") final ConsumerRequestEndpointStructure dataSupplyRequestInfo,
			@WebParam(partName = "Request", name = "Request") final DataSupplyRequestBodyStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "DataSupplyAnswerInfo",
					mode = Mode.OUT,
					name = "DataSupplyAnswerInfo") final Holder<ProducerResponseEndpointStructure> dataSupplyAnswerInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<ServiceDeliveryBodyStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws DataSupplyError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getStopMonitoring
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.StopMonitoringRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	/**
	 * Traite une requete pour le service StopMonitoring
	 */
	@Override
	@WebMethod(operationName = "GetStopMonitoring", action = "GetStopMonitoring")
	public void getStopMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final StopMonitoringRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<StopMonitoringDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws StopMonitoringError {

		//				LOG.info("GetStopMonitoring - SM {} - Id {} - Operator {}", request.getMonitoringRef().getValue(), request.getMessageIdentifier()
		//						.getValue(), request.getOperatorRef().getValue());

		LOG.info("GetStopMonitoring called");

		serviceDeliveryInfo.value = smBuilder.createProducerResponseEndpointStructure(serviceRequestInfo);
		answer.value = smBuilder.createStopMonitoringDelivery(request);
		answerExtension.value = smBuilder.createExtentionStructure();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getGeneralMessage
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.GeneralMessageRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetGeneralMessage", action = "GetGeneralMessage")
	public void getGeneralMessage(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final GeneralMessageRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<GeneralMessageDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws GeneralMessageError {

		LOG.info("GetGeneralMessage called");
		serviceDeliveryInfo.value = new ProducerResponseEndpointStructure();
		answerExtension.value = new ExtensionsStructure();
		GeneralMessageDeliveriesStructure generalMessageDeliveriesStructure = new GeneralMessageDeliveriesStructure();
		answer.value = generalMessageDeliveriesStructure;
		GeneralMessageDeliveryStructure e = new GeneralMessageDeliveryStructure();
		e.setDelegatorAddress("Defense");
		generalMessageDeliveriesStructure.getGeneralMessageDelivery().add(e);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#checkStatus(com.
	 * thalesgroup.stif.siri.v24.uk.org.siri.siri.RequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "CheckStatus", action = "CheckStatus")
	public void checkStatus(@WebParam(partName = "Request", name = "Request") final CheckStatusRequestStructure request, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
			partName = "CheckStatusAnswerInfo",
			mode = Mode.OUT,
			name = "CheckStatusAnswerInfo") final Holder<ProducerResponseEndpointStructure> checkStatusAnswerInfo, @WebParam(
			partName = "Answer",
			mode = Mode.OUT,
			name = "Answer") final Holder<CheckStatusResponseBodyStructure> answer, @WebParam(
			partName = "AnswerExtension",
			mode = Mode.OUT,
			name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws CheckStatusError {
		// TODO Auto-generated method stub

		ProducerResponseEndpointStructure producerResponseEndpointStructure = new ProducerResponseEndpointStructure();
		producerResponseEndpointStructure.setProducerRef(NotificationUtils.createParticipantRefStructure("SNCF"));
		producerResponseEndpointStructure.setResponseTimestamp(DateUtils.convertDateToXmlGregorianCalendar(new Date()));
		producerResponseEndpointStructure.setResponseMessageIdentifier(NotificationUtils.createMessageQualifierStructure(UUID.randomUUID()
				.toString()));
		producerResponseEndpointStructure.setRequestMessageRef(NotificationUtils.createMessageRefStructure(UUID.randomUUID().toString()));
		checkStatusAnswerInfo.value = producerResponseEndpointStructure;

		CheckStatusResponseBodyStructure checkStatusResponseBody = new CheckStatusResponseBodyStructure();

		Calendar start = Calendar.getInstance();
		start.set(2014, 8, 26, 13, 30);

		Calendar finish = Calendar.getInstance();
		finish.set(2018, 8, 29, 23, 30);

		System.out.println("Time : " + Calendar.getInstance().getTime());
		System.out.println("start " + start.getTime());
		System.out.println("finish " + finish.getTime());

		Calendar now = Calendar.getInstance();

		if (now.after(start) && now.before(finish)) {
			checkStatusResponseBody.setStatus(Boolean.TRUE);
		} else {
			checkStatusResponseBody.setStatus(Boolean.FALSE);
		}

		checkStatusResponseBody.setValidUntil(DateUtils.convertDateToXmlGregorianCalendar(new Date()));
		checkStatusResponseBody.setServiceStartedTime(DateUtils.convertDateToXmlGregorianCalendar(new Date()));

		answer.value = checkStatusResponseBody;

		answerExtension.value = new ExtensionsStructure();

		LOG.info("CheckStatus called");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getFacilityMonitoring
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.FacilityMonitoringRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetFacilityMonitoring", action = "GetFacilityMonitoring")
	public void getFacilityMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final FacilityMonitoringRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<FacilityMonitoringDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws FacilityMonitoringError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#deleteSubscription
	 * (com.thalesgroup.stif.siri.v24.uk.org.siri.siri.RequestStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.TerminateSubscriptionRequestBodyStructure ,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "DeleteSubscription", action = "DeleteSubscription")
	public void deleteSubscription(
			@WebParam(partName = "DeleteSubscriptionInfo", name = "DeleteSubscriptionInfo") final RequestStructure deleteSubscriptionInfo,
			@WebParam(partName = "Request", name = "Request") final TerminateSubscriptionRequestBodyStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "DeleteSubscriptionAnswerInfo",
					mode = Mode.OUT,
					name = "DeleteSubscriptionAnswerInfo") final Holder<ResponseEndpointStructure> deleteSubscriptionAnswerInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<TerminateSubscriptionResponseStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws DeleteSubscriptionError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getCapabilities( com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.CapabilitiesRequestStructure, com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure,
	 * javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetCapabilities", action = "GetCapabilities")
	public void getCapabilities(@WebParam(partName = "Request", name = "Request") final CapabilitiesRequestStructure request, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
			partName = "Answer",
			mode = Mode.OUT,
			name = "Answer") final Holder<CapabilitiesResponseStructure> answer, @WebParam(
			partName = "AnswerExtension",
			mode = Mode.OUT,
			name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws GetCapabilitiesError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getEstimatedTimetable
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.EstimatedTimetableRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetEstimatedTimetable", action = "GetEstimatedTimetable")
	public void getEstimatedTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final EstimatedTimetableRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<EstimatedTimetableDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws EstimatedTimetableError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort# getMultipleStopMonitoring
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.StopMonitoringMultipleRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetMultipleStopMonitoring", action = "GetMultipleStopMonitoring")
	public void getMultipleStopMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final StopMonitoringMultipleRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<StopMonitoringDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws StopMonitoringError {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thalesgroup.stif.siri.v24.ws.SiriProducerRpcPort#getVehicleMonitoring
	 * (com.thalesgroup.stif.siri.v24.ws.WsServiceRequestInfoStructure, com.thalesgroup
	 * .stif.siri.v24.uk.org.siri.siri.VehicleMonitoringRequestStructure,
	 * com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure, javax.xml.ws.Holder, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	@WebMethod(operationName = "GetVehicleMonitoring", action = "GetVehicleMonitoring")
	public void getVehicleMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure serviceRequestInfo,
			@WebParam(partName = "Request", name = "Request") final VehicleMonitoringRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "ServiceDeliveryInfo",
					mode = Mode.OUT,
					name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> serviceDeliveryInfo, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<VehicleMonitoringDeliveriesStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws VehicleMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "ConnectionLinksDiscovery", action = "ConnectionLinksDiscovery")
	public void connectionLinksDiscovery(
			@WebParam(partName = "Request", name = "Request") final ConnectionLinksDiscoveryRequestStructure request, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure requestExtension, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<ConnectionLinksDeliveryStructure> answer, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> answerExtension) throws ConnectionLinksDiscoveryError {
		// TODO Auto-generated method stub

	}

}
