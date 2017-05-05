package com.thalesgroup.stif.bouchon.producteur;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebParam.Mode;
import javax.jws.WebResult;
import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * @author kaouini
 *
 */

@javax.jws.WebService(
		serviceName = "SiriProducerRpcPort",
		name = "SiriProducerRpcPort",
		portName = "SiriWSPort",
		targetNamespace = "http://wsdl.siri.org.uk")
public class GeneralMessageSOAPPortImpl implements SiriProducerRpcPort {

	/**
	 * Logger
	 */
	private static Logger LOG = LoggerFactory.getLogger(GeneralMessageSOAPPortImpl.class);

	@Override
	@WebMethod(operationName = "CheckStatus", action = "CheckStatus")
	public void checkStatus(@WebParam(partName = "Request", name = "Request") final CheckStatusRequestStructure arg0, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure arg1, @WebParam(
			partName = "CheckStatusAnswerInfo",
			mode = Mode.OUT,
			name = "CheckStatusAnswerInfo") final Holder<ProducerResponseEndpointStructure> arg2, @WebParam(
			partName = "Answer",
			mode = Mode.OUT,
			name = "Answer") final Holder<CheckStatusResponseBodyStructure> arg3, @WebParam(
			partName = "AnswerExtension",
			mode = Mode.OUT,
			name = "AnswerExtension") final Holder<ExtensionsStructure> arg4) throws CheckStatusError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "DataSupply", action = "DataSupply")
	public void dataSupply(
			@WebParam(partName = "DataSupplyRequestInfo", name = "DataSupplyRequestInfo") final ConsumerRequestEndpointStructure arg0,
			@WebParam(partName = "Request", name = "Request") final DataSupplyRequestBodyStructure arg1, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
							partName = "DataSupplyAnswerInfo",
							mode = Mode.OUT,
							name = "DataSupplyAnswerInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
									partName = "Answer",
									mode = Mode.OUT,
									name = "Answer") final Holder<ServiceDeliveryBodyStructure> arg4, @WebParam(
											partName = "AnswerExtension",
											mode = Mode.OUT,
											name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws DataSupplyError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "DeleteSubscription", action = "DeleteSubscription")
	public void deleteSubscription(
			@WebParam(partName = "DeleteSubscriptionInfo", name = "DeleteSubscriptionInfo") final RequestStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final TerminateSubscriptionRequestBodyStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "DeleteSubscriptionAnswerInfo",
									mode = Mode.OUT,
									name = "DeleteSubscriptionAnswerInfo") final Holder<ResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<TerminateSubscriptionResponseStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws DeleteSubscriptionError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetCapabilities", action = "GetCapabilities")
	public void getCapabilities(@WebParam(partName = "Request", name = "Request") final CapabilitiesRequestStructure arg0, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure arg1,
			@WebParam(partName = "Answer", mode = Mode.OUT, name = "Answer") final Holder<CapabilitiesResponseStructure> arg2, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> arg3) throws GetCapabilitiesError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetConnectionMonitoring", action = "GetConnectionMonitoring")
	public void getConnectionMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final ConnectionMonitoringRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<ConnectionMonitoringDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws ConnectionMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetConnectionTimetable", action = "GetConnectionTimetable")
	public void getConnectionTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final ConnectionTimetableRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<ConnectionTimetableDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws ConnectionTimetableError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetEstimatedTimetable", action = "GetEstimatedTimetable")
	public void getEstimatedTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final EstimatedTimetableRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<EstimatedTimetableDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws EstimatedTimetableError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetFacilityMonitoring", action = "GetFacilityMonitoring")
	public void getFacilityMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final FacilityMonitoringRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<FacilityMonitoringDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws FacilityMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetGeneralMessage", action = "GetGeneralMessage")
	public void getGeneralMessage(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final GeneralMessageRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<GeneralMessageDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws GeneralMessageError {

		LOG.info("Je suis le producteur ratp");
		arg3.value = new ProducerResponseEndpointStructure();
		arg5.value = new ExtensionsStructure();
		GeneralMessageDeliveriesStructure generalMessageDeliveriesStructure = new GeneralMessageDeliveriesStructure();
		arg4.value = generalMessageDeliveriesStructure;
		GeneralMessageDeliveryStructure e = new GeneralMessageDeliveryStructure();
		e.setDelegatorAddress("Defense");
		generalMessageDeliveriesStructure.getGeneralMessageDelivery().add(e);

	}

	@Override
	@WebMethod(operationName = "GetMultipleStopMonitoring", action = "GetMultipleStopMonitoring")
	public void getMultipleStopMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final StopMonitoringMultipleRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<StopMonitoringDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws StopMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetProductionTimetable", action = "GetProductionTimetable")
	public void getProductionTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final ProductionTimetableRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<ProductionTimetableDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws ProductionTimetableError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebResult(name = "Answer", targetNamespace = "http://wsdl.siri.org.uk", partName = "Answer")
	@WebMethod(operationName = "GetSiriService", action = "GetSiriService")
	public ServiceDeliveryStructure getSiriService(@WebParam(partName = "Request", name = "Request") final ServiceRequestStructure arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(operationName = "GetSituationExchange", action = "GetSituationExchange")
	public void getSituationExchange(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final SituationExchangeRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<SituationExchangeDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws SituationExchangeError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetStopMonitoring", action = "GetStopMonitoring")
	public void getStopMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final StopMonitoringRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<StopMonitoringDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws StopMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetStopTimetable", action = "GetStopTimetable")
	public void getStopTimetable(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final StopTimetableRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<StopTimetableDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws StopTimetableError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "GetVehicleMonitoring", action = "GetVehicleMonitoring")
	public void getVehicleMonitoring(
			@WebParam(partName = "ServiceRequestInfo", name = "ServiceRequestInfo") final WsServiceRequestInfoStructure arg0, @WebParam(
					partName = "Request",
					name = "Request") final VehicleMonitoringRequestStructure arg1, @WebParam(
							partName = "RequestExtension",
							name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
									partName = "ServiceDeliveryInfo",
									mode = Mode.OUT,
									name = "ServiceDeliveryInfo") final Holder<ProducerResponseEndpointStructure> arg3, @WebParam(
											partName = "Answer",
											mode = Mode.OUT,
											name = "Answer") final Holder<VehicleMonitoringDeliveriesStructure> arg4, @WebParam(
													partName = "AnswerExtension",
													mode = Mode.OUT,
													name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws VehicleMonitoringError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "LinesDiscovery", action = "LinesDiscovery")
	public void linesDiscovery(@WebParam(partName = "Request", name = "Request") final LinesDiscoveryRequestStructure arg0, @WebParam(
			partName = "RequestExtension",
			name = "RequestExtension") final ExtensionsStructure arg1,
			@WebParam(partName = "Answer", mode = Mode.OUT, name = "Answer") final Holder<LinesDeliveryStructure> arg2, @WebParam(
					partName = "AnswerExtension",
					mode = Mode.OUT,
					name = "AnswerExtension") final Holder<ExtensionsStructure> arg3) throws LinesDiscoveryError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "StopPointsDiscovery", action = "StopPointsDiscovery")
	public void stopPointsDiscovery(@WebParam(partName = "Request", name = "Request") final StopPointsDiscoveryRequestStructure arg0,
			@WebParam(partName = "RequestExtension", name = "RequestExtension") final ExtensionsStructure arg1, @WebParam(
					partName = "Answer",
					mode = Mode.OUT,
					name = "Answer") final Holder<StopPointsDeliveryStructure> arg2, @WebParam(
							partName = "AnswerExtension",
							mode = Mode.OUT,
							name = "AnswerExtension") final Holder<ExtensionsStructure> arg3) throws StopPointsDiscoveryError {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(operationName = "Subscribe", action = "Subscribe")
	public void subscribe(
			@WebParam(partName = "SubscriptionRequestInfo", name = "SubscriptionRequestInfo") final WsSubscriptionRequestInfoStructure arg0,
			@WebParam(partName = "Request", name = "Request") final SiriSubscriptionRequestStructure arg1, @WebParam(
					partName = "RequestExtension",
					name = "RequestExtension") final ExtensionsStructure arg2, @WebParam(
							partName = "SubscriptionAnswerInfo",
							mode = Mode.OUT,
							name = "SubscriptionAnswerInfo") final Holder<ResponseEndpointStructure> arg3, @WebParam(
									partName = "Answer",
									mode = Mode.OUT,
									name = "Answer") final Holder<SubscriptionResponseBodyStructure> arg4, @WebParam(
											partName = "AnswerExtension",
											mode = Mode.OUT,
											name = "AnswerExtension") final Holder<ExtensionsStructure> arg5) throws SubscriptionError {
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
