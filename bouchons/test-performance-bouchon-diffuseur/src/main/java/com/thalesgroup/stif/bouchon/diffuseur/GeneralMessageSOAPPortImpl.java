package com.thalesgroup.stif.bouchon.diffuseur;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CheckStatusResponseBodyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ConnectionTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DataReadyRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.EstimatedTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ExtensionsStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.FacilityMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.GeneralMessageDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProducerRequestEndpointStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProducerResponseEndpointStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProductionTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SituationExchangeDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopTimetableDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SubscriptionTerminatedNotificationStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.VehicleMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.ws.SiriConsumerRpcPort;

/**
 * @author kaouini
 *
 */
@javax.jws.WebService(serviceName = "SiriConsumerRpcPort", portName = "SiriWSPort", targetNamespace = "http://wsdl.siri.org.uk")
public class GeneralMessageSOAPPortImpl implements SiriConsumerRpcPort {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GeneralMessageSOAPPortImpl.class);

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyStopTimetable", action = "GetStopTimetable")
	public void notifyStopTimetable(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final StopTimetableDeliveriesStructure notification, @WebParam(
					partName = "NotifyExtension",
					name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyConnectionMonitoring", action = "GetConnectionMonitoring")
	public void notifyConnectionMonitoring(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final ConnectionMonitoringDeliveriesStructure notification,
			@WebParam(partName = "NotifyExtension", name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyEstimatedTimetable", action = "GetEstimatedTimetable")
	public void notifyEstimatedTimetable(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final EstimatedTimetableDeliveriesStructure notification,
			@WebParam(partName = "NotifyExtension", name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyFacilityMonitoring", action = "GetFacilityMonitoring")
	public void notifyFacilityMonitoring(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final FacilityMonitoringDeliveriesStructure notification,
			@WebParam(partName = "NotifyExtension", name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyGeneralMessage", action = "GetGeneralMessage")
	public void notifyGeneralMessage(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final GeneralMessageDeliveriesStructure notification, @WebParam(
					partName = "NotifyExtension",
					name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		String uuid = null;
		if (notifyExtension != null && notifyExtension.getAny() != null) {
			uuid = ((Node) notifyExtension.getAny()).getChildNodes().item(0).getNodeValue();
		}
		LOG.info("Sending Request - " + uuid + " - Reception notification StopMonitoring");
	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyVehicleMonitoring", action = "GetVehicleMonitoring")
	public void notifyVehicleMonitoring(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final VehicleMonitoringDeliveriesStructure notification, @WebParam(
					partName = "NotifyExtension",
					name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifySituationExchange", action = "GetSituationExchange")
	public void notifySituationExchange(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final SituationExchangeDeliveriesStructure notification, @WebParam(
					partName = "NotifyExtension",
					name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyHeartbeat", action = "NotifyHeartbeat")
	public void notifyHeartbeat(
			@WebParam(partName = "HeartbeatNotifyInfo", name = "HeartbeatNotifyInfo") final ProducerRequestEndpointStructure heartbeatNotifyInfo,
			@WebParam(partName = "Notification", name = "Notification") final CheckStatusResponseBodyStructure notification, @WebParam(
					partName = "SiriExtension",
					name = "SiriExtension") final ExtensionsStructure siriExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyProductionTimetable", action = "GetProductionTimetable")
	public void notifyProductionTimetable(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final ProductionTimetableDeliveriesStructure notification,
			@WebParam(partName = "NotifyExtension", name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyStopMonitoring", action = "GetStopMonitoring")
	public void notifyStopMonitoring(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final StopMonitoringDeliveriesStructure notification, @WebParam(
					partName = "NotifyExtension",
					name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		String uuid = null;
		if (notifyExtension != null && notifyExtension.getAny() != null) {
			uuid = ((Node) notifyExtension.getAny()).getChildNodes().item(0).getNodeValue();
		}
		LOG.info("Sending Request - " + uuid + " - Reception notification StopMonitoring");
	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyDataReady", action = "NotifyDataReady")
	public void notifyDataReady(@WebParam(partName = "Notification", name = "Notification") final DataReadyRequestStructure notification,
			@WebParam(partName = "SiriExtension", name = "SiriExtension") final ExtensionsStructure siriExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifyConnectionTimetable", action = "GetConnectionTimetable")
	public void notifyConnectionTimetable(
			@WebParam(partName = "ServiceDeliveryInfo", name = "ServiceDeliveryInfo") final ProducerResponseEndpointStructure serviceDeliveryInfo,
			@WebParam(partName = "Notification", name = "Notification") final ConnectionTimetableDeliveriesStructure notification,
			@WebParam(partName = "NotifyExtension", name = "NotifyExtension") final ExtensionsStructure notifyExtension) {
		// TODO Auto-generated method stub

	}

	@Override
	@Oneway
	@WebMethod(operationName = "NotifySubscriptionTerminated", action = "NotifySubscriptionTerminate")
	public void notifySubscriptionTerminated(
			@WebParam(partName = "Notification", name = "Notification") final SubscriptionTerminatedNotificationStructure notification,
			@WebParam(partName = "SiriExtension", name = "SiriExtension") final ExtensionsStructure siriExtension) {
		// TODO Auto-generated method stub

	}

}
