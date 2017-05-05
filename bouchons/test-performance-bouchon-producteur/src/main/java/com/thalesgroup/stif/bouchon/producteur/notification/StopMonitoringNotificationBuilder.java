package com.thalesgroup.stif.bouchon.producteur.notification;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.thalesgroup.stif.commons.utils.DateUtils;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CallStatusEnumeration;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DepartureBoardingActivityEnumeration;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.FirstOrLastJourneyEnumeration;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.FramedVehicleJourneyRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoredCallStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoredStopVisitStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoredVehicleJourneyStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.OccupancyEnumeration;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringDeliveriesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringDeliveryStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopMonitoringRequestStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.VehicleModesEnumeration;

/**
 * Crée la structure d'une notification StopMonitoring
 * 
 * @author thales
 * 
 */
@Component
public class StopMonitoringNotificationBuilder extends GeneralNotification {

	/**
	 * Logger
	 */
	private static Logger LOG = LoggerFactory.getLogger(StopMonitoringNotificationBuilder.class);

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application}")
	private Properties properties;

	private static Calendar calendar;

	public StopMonitoringNotificationBuilder() {
		super();

		calendar = Calendar.getInstance();
	}

	public String getProperty(final String key) {
		return NotificationUtils.randomSplittedValue(properties.getProperty(key));
	}

	/**
	 * Créer une date et ajoute les valeurs passé en parametre s'ils sont positif
	 * 
	 * @param hour
	 *            Nombre d'heure à ajouter
	 * @param minute
	 *            Nombre de minutes à ajouter
	 * @return une date
	 */
	public Date createDateFromNow(final int hour, final int minute) {
		calendar.setTime(new Date());

		if (hour > 0) {
			calendar.add(Calendar.HOUR_OF_DAY, hour);
		}

		if (minute > 0) {
			calendar.add(Calendar.MINUTE, minute);
		}

		return calendar.getTime();

	}

	/**
	 * Crée une notification StopMonitoring
	 * 
	 * @param serviceRequestInfo
	 * @param request
	 * @param requestExtension
	 * @return
	 */
	public StopMonitoringDeliveriesStructure createStopMonitoringDelivery(final StopMonitoringRequestStructure request) {

		StopMonitoringDeliveriesStructure struct = new StopMonitoringDeliveriesStructure();
		StopMonitoringDeliveryStructure stopMonitoringDelivery = new StopMonitoringDeliveryStructure();
		stopMonitoringDelivery.setVersion(getProperty("stopMonitoringDelivery.version"));
		stopMonitoringDelivery.setSubscriptionFilterRef(NotificationUtils
				.createSubscriptionFilterRefStructure(getProperty("stopMonitoringDelivery.subscriptionFilterRef")));
		stopMonitoringDelivery.setResponseTimestamp(DateUtils.convertDateToXmlGregorianCalendar(new Date()));
		stopMonitoringDelivery.setRequestMessageRef(NotificationUtils.createMessageQualifierStructure(request.getMessageIdentifier()
				.getValue()));
		stopMonitoringDelivery.setStatus(Boolean.TRUE);
		stopMonitoringDelivery.getMonitoringRef()
				.add(NotificationUtils.createMonitoringRefStructure(request.getMonitoringRef().getValue()));

		// On ajoute un MonitoredStopVisit

		int nbMonitoredStopVisit = NotificationUtils.randInt(1, 4);

		for (int i = 0; i < nbMonitoredStopVisit; i++) {

			stopMonitoringDelivery.getMonitoredStopVisit().add(this.createMonitoredStopVisitStructure(request));

		}

		struct.getStopMonitoringDelivery().add(stopMonitoringDelivery);

		return struct;
	}

	/**
	 * Crée un objet MonitoredStopVisit
	 * 
	 * @param request
	 * @return
	 */
	public MonitoredStopVisitStructure createMonitoredStopVisitStructure(final StopMonitoringRequestStructure request) {
		MonitoredStopVisitStructure monitored = new MonitoredStopVisitStructure();
		monitored.setRecordedAtTime(DateUtils.convertDateToXmlGregorianCalendar(new Date()));
		monitored.setItemIdentifier(UUID.randomUUID().toString());
		monitored.setMonitoringRef(request.getMonitoringRef());
		monitored.setMonitoredVehicleJourney(this.createMonitoredVehicleJourneyStructure(request));

		return monitored;
	}

	/**
	 * Crée un objet MonitoredVehicleJourneyStructure
	 * 
	 * @param request
	 * @return
	 */
	public MonitoredVehicleJourneyStructure createMonitoredVehicleJourneyStructure(final StopMonitoringRequestStructure request) {

		MonitoredVehicleJourneyStructure res = new MonitoredVehicleJourneyStructure();

		try {

			UUID uuid = UUID.randomUUID();

			res.setLineRef(NotificationUtils.createLineRefStructure(getProperty("stopMonitoringDelivery.lineRef")));
			res.setFramedVehicleJourneyRef(this.createFramedVehicleJourneyRef());
			res.setJourneyPatternRef(NotificationUtils
					.createJourneyPatternRefStructure(getProperty("stopMonitoringDelivery.journeyPatternRef")));
			res.setJourneyPatternName(NotificationUtils.createNaturalLanguageStringStructure("MISSION-"
					+ getProperty("stopMonitoringDelivery.journeyPatternRef")));
			res.getVehicleMode().add(VehicleModesEnumeration.RAIL);
			res.setRouteRef(NotificationUtils.createRouteRefStructure(getProperty("stopMonitoringDelivery.routeRef")));
			res.getPublishedLineName().add(
					NotificationUtils.createNaturalLanguageStringStructure(getProperty("stopMonitoringDelivery.publishedLineName")));
			res.getDirectionName().add(
					NotificationUtils.createNaturalLanguageStringStructure(getProperty("stopMonitoringDelivery.directionName")));

			res.setOperatorRef(NotificationUtils.createOperatorRefStructure(getProperty("stopMonitoringDelivery.operatorRef")));

			res.setProductCategoryRef(NotificationUtils
					.createProductCategoryRefStructure(getProperty("stopMonitoringDelivery.productCategorie")));
			res.getServiceFeatureRef().add(NotificationUtils.createServiceFeartureRefStructure("train voyageur"));
			res.getVehicleFeatureRef().add(NotificationUtils.createVehicleFeatureRefStructure("longTrain"));
			res.setOriginRef(NotificationUtils.createJourneyPlaceRefStructure(getProperty("stopMonitoringDelivery.originRef")));
			res.getOriginName().add(
					NotificationUtils.createNaturalLanguagePlaceNameStructure(getProperty("stopMonitoringDelivery.originName")));
			res.getVia().add(
					NotificationUtils.createViaNameStructure(getProperty("stopMonitoringDelivery.placeName"),
							getProperty("stopMonitoringDelivery.placeRef")));

			if (request.getDirectionRef() != null) {
				res.setDirectionRef(NotificationUtils.createDirectionRefStructure(request.getDirectionRef().getValue()));
			}

			res.setDestinationRef(NotificationUtils.createDestinationRefStructure(getProperty("stopMonitoringDelivery.destinationRef")));

			res.getVehicleJourneyName().add(
					NotificationUtils.createNaturalLanguageStringStructure(getProperty("stopMonitoringDelivery.vehicleJourneyName")));
			res.getJourneyNote().add(
					NotificationUtils.createNaturalLanguageStringStructure(getProperty("stopMonitoringDelivery.journeyNote")));
			res.setHeadwayService(Boolean.TRUE);
			res.setOriginAimedDepartureTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 5)));
			res.setDestinationAimedArrivalTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(1, 15)));
			res.setFirstOrLastJourney(FirstOrLastJourneyEnumeration.UNSPECIFIED);
			res.setMonitored(Boolean.TRUE);
			res.setInCongestion(Boolean.FALSE);
			res.setInPanic(Boolean.FALSE);
			res.setVehicleLocation(NotificationUtils.createVehicleLocation(new BigDecimal("2.227435"), new BigDecimal("48.785067"),
					"N 48 47.107, E 2 13.662", new BigInteger("1")));
			res.setBearing(new Float(1));
			res.setOccupancy(OccupancyEnumeration.FULL);

			Duration duration = DatatypeFactory.newInstance().newDuration(true, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));

			res.setDelay(duration);
			String trainNumber = String.valueOf(UUID.randomUUID().getMostSignificantBits());
			res.setTrainNumbers(NotificationUtils.createTrainNumbers(trainNumber));
			res.setJourneyParts(NotificationUtils.createJourneyParts(getProperty("stopMonitoringDelivery.journeyPartRef"), trainNumber
					+ "-ref", getProperty("stopMonitoringDelivery.operatorRef")));
			res.setMonitoredCall(this.createMonitoredCallStructure(request));

		} catch (DatatypeConfigurationException e) {
			LOG.error("Erreur lors de la creation de la duration {}", e);
		}

		return res;
	}

	/**
	 * Crée un MonitoredCallStructure
	 * 
	 * @param request
	 * @return
	 */
	public MonitoredCallStructure createMonitoredCallStructure(final StopMonitoringRequestStructure request) {

		MonitoredCallStructure res = new MonitoredCallStructure();

		try {

			res.setStopPointRef(NotificationUtils.createStopPointRefStructure(getProperty("stopMonitoringDelivery.stopPointRef")));
			res.setOrder(new BigInteger("1"));
			res.getStopPointName().add(
					NotificationUtils.createNaturalLanguageStringStructure(getProperty("stopMonitoringDelivery.stopPointName")));
			res.setVehicleAtStop(Boolean.TRUE);
			res.setPlatformTraversal(Boolean.TRUE);
			if (request.getDestinationRef() != null) {
				res.getDestinationDisplay().add(
						NotificationUtils.createNaturalLanguageStringStructure(request.getDestinationRef().getValue()));
			}
			res.setAimedArrivalTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 1)));
			res.setActualArrivalTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 1)));
			res.setExpectedArrivalTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 1)));
			res.setArrivalStatus(CallStatusEnumeration.values()[(int) (Math.random() * CallStatusEnumeration.values().length)]);
			res.setArrivalProximityText(NotificationUtils.createNaturalLanguageStringStructure("a l'approche"));
			res.setArrivalPlatformName(NotificationUtils.createNaturalLanguageStringStructure("quai 1"));
			res.setArrivalStopAssignment(NotificationUtils.createStopAssignmentStructure("voie 2"));
			res.setAimedDepartureTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 3)));
			res.setActualDepartureTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 4)));
			res.setExpectedDepartureTime(DateUtils.convertDateToXmlGregorianCalendar(this.createDateFromNow(0, 4)));
			res.setDepartureStatus(CallStatusEnumeration.values()[(int) (Math.random() * CallStatusEnumeration.values().length)]);
			res.setDeparturePlatformName(NotificationUtils.createNaturalLanguageStringStructure("quai 1"));
			res.setDepartureBoardingActivity(DepartureBoardingActivityEnumeration.values()[(int) (Math.random() * DepartureBoardingActivityEnumeration
					.values().length)]);

			Duration duration = DatatypeFactory.newInstance().newDuration(true, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));

			res.setAimedHeadwayInterval(duration);
			res.setExpectedHeadwayInterval(duration);

			res.setDistanceFromStop(new BigInteger("2300"));
			res.setNumberOfStopsAway(new BigInteger("3"));

		} catch (DatatypeConfigurationException e) {
			LOG.error("Erreur lors de la creation de la duration {}", e);
		}

		return res;
	}

	/**
	 * Crée un FramedVehicleJourneyRefStructure
	 * 
	 * @return
	 */
	public FramedVehicleJourneyRefStructure createFramedVehicleJourneyRef() {

		FramedVehicleJourneyRefStructure res = new FramedVehicleJourneyRefStructure();
		res.setDataFrameRef(NotificationUtils.createDataFrameRefStructure(getProperty("stopMonitoringDelivery.dataFrameRef")));
		res.setDatedVehicleJourneyRef(getProperty("stopMonitoringDelivery.datedVehicleJourney"));

		return res;

	}

}
