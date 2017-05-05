package com.thalesgroup.stif.bouchon.producteur.notification;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CoordinatesStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DataFrameRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DestinationRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.DirectionRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.JourneyPartInfoStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.JourneyPatternRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.JourneyPlaceRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.LineRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.LocationStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MessageQualifierStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MessageRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoredVehicleJourneyStructure.JourneyParts;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoredVehicleJourneyStructure.TrainNumbers;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.MonitoringRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.NaturalLanguagePlaceNameStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.NaturalLanguageStringStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.OperatorRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ParticipantRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ProductCategoryRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.RouteRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ServiceFeatureRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopAssignmentStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.StopPointRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.SubscriptionFilterRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.TrainNumberRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.VehicleFeatureRefStructure;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.ViaNameStructure;

/**
 * Class utilitaire permettant de créer les structures de données SIRI
 * 
 * @author thales
 * 
 */
public class NotificationUtils {

	public static String REGEX = "#";

	public static Random rand = new Random();

	public static String randomSplittedValue(final String regex, final String value) {

		String[] tab = value.split(regex);

		int max = tab.length;
		int randomNum = rand.nextInt(max);

		return tab[randomNum];

	}

	public static String randomSplittedValue(final String value) {
		return randomSplittedValue(REGEX, value);
	}

	/**
	 * Crée un ParticipantRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static ParticipantRefStructure createParticipantRefStructure(final String value) {

		ParticipantRefStructure res = new ParticipantRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un MessageRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static MessageRefStructure createMessageRefStructure(final String value) {

		MessageRefStructure res = new MessageRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un MessageQualifierStructure
	 * 
	 * @param value
	 * @return
	 */
	public static MessageQualifierStructure createMessageQualifierStructure(final String value) {

		MessageQualifierStructure res = new MessageQualifierStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un SubscriptionFilterRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static SubscriptionFilterRefStructure createSubscriptionFilterRefStructure(final String value) {

		SubscriptionFilterRefStructure res = new SubscriptionFilterRefStructure();
		res.setValue(value);
		return res;
	}

	/**
	 * Crée un MonitoringRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static MonitoringRefStructure createMonitoringRefStructure(final String value) {

		MonitoringRefStructure res = new MonitoringRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un DataFrameRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static DataFrameRefStructure createDataFrameRefStructure(final String value) {

		DataFrameRefStructure res = new DataFrameRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un JourneyPatternRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static JourneyPatternRefStructure createJourneyPatternRefStructure(final String value) {

		JourneyPatternRefStructure res = new JourneyPatternRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un NaturalLanguageStringStructure
	 * 
	 * @param value
	 * @return
	 */
	public static NaturalLanguageStringStructure createNaturalLanguageStringStructure(final String value) {

		NaturalLanguageStringStructure res = new NaturalLanguageStringStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un RouteRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static RouteRefStructure createRouteRefStructure(final String value) {

		RouteRefStructure res = new RouteRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un OperatorRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static OperatorRefStructure createOperatorRefStructure(final String value) {

		OperatorRefStructure res = new OperatorRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un ProductCategoryRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static ProductCategoryRefStructure createProductCategoryRefStructure(final String value) {

		ProductCategoryRefStructure res = new ProductCategoryRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un ServiceFeatureRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static ServiceFeatureRefStructure createServiceFeartureRefStructure(final String value) {

		ServiceFeatureRefStructure res = new ServiceFeatureRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un VehicleFeatureRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static VehicleFeatureRefStructure createVehicleFeatureRefStructure(final String value) {

		VehicleFeatureRefStructure res = new VehicleFeatureRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un JourneyPlaceRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static JourneyPlaceRefStructure createJourneyPlaceRefStructure(final String value) {

		JourneyPlaceRefStructure res = new JourneyPlaceRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un NaturalLanguagePlaceNameStructure
	 * 
	 * @param value
	 * @return
	 */
	public static NaturalLanguagePlaceNameStructure createNaturalLanguagePlaceNameStructure(final String value) {

		NaturalLanguagePlaceNameStructure res = new NaturalLanguagePlaceNameStructure();
		res.setLang(value);
		return res;
	}

	/**
	 * Crée un ViaNameStructure
	 * 
	 * @param placeName
	 * @param placeRef
	 * @return
	 */
	public static ViaNameStructure createViaNameStructure(final String placeName, final String placeRef) {

		ViaNameStructure res = new ViaNameStructure();
		res.getPlaceName().add(createNaturalLanguagePlaceNameStructure(placeName));
		res.setPlaceRef(createJourneyPlaceRefStructure(placeRef));

		return res;
	}

	/**
	 * Crée un DirectionRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static DirectionRefStructure createDirectionRefStructure(final String value) {

		DirectionRefStructure res = new DirectionRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un LocationStructure
	 * 
	 * @param longitude
	 * @param latitude
	 * @param coordinates
	 * @param precision
	 * @return
	 */
	public static LocationStructure createVehicleLocation(final BigDecimal longitude, final BigDecimal latitude, final String coordinates,
			final BigInteger precision) {

		LocationStructure res = new LocationStructure();
		res.setLongitude(longitude);
		res.setLatitude(latitude);
		res.setCoordinates(createCoordinatesStructure(coordinates));
		res.setPrecision(precision);

		return res;
	}

	/**
	 * Crée un CoordinatesStructure
	 * 
	 * @param coordinates
	 * @return
	 */
	public static CoordinatesStructure createCoordinatesStructure(final String coordinates) {

		CoordinatesStructure res = new CoordinatesStructure();
		res.getValue().add(coordinates);

		return res;
	}

	/**
	 * Crée un TrainNumbers
	 * 
	 * @param value
	 * @return
	 */
	public static TrainNumbers createTrainNumbers(final String value) {

		TrainNumbers res = new TrainNumbers();
		TrainNumberRefStructure e = new TrainNumberRefStructure();
		e.setValue(value);
		res.getTrainNumberRef().add(e);

		return res;
	}

	/**
	 * Crée un JourneyParts
	 * 
	 * @param journeyPartRef
	 * @param trainNumberRef
	 * @param operatorRef
	 * @return
	 */
	public static JourneyParts createJourneyParts(final String journeyPartRef, final String trainNumberRef, final String operatorRef) {

		JourneyParts res = new JourneyParts();
		JourneyPartInfoStructure e = new JourneyPartInfoStructure();
		e.setJourneyPartRef(journeyPartRef);
		e.setTrainNumberRef(createTrainNumberRefStructure(trainNumberRef));
		e.setOperatorRef(createOperatorRefStructure(operatorRef));
		res.getJourneyPartInfo().add(e);

		return null;
	}

	/**
	 * Crée un TrainNumberRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static TrainNumberRefStructure createTrainNumberRefStructure(final String value) {

		TrainNumberRefStructure res = new TrainNumberRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un StopPointRefStructure
	 * 
	 * @param value
	 * @return
	 */
	public static StopPointRefStructure createStopPointRefStructure(final String value) {

		StopPointRefStructure res = new StopPointRefStructure();
		res.setValue(value);

		return res;
	}

	/**
	 * Crée un StopAssignmentStructure
	 * 
	 * @param value
	 * @return
	 */
	public static StopAssignmentStructure createStopAssignmentStructure(final String value) {

		StopAssignmentStructure res = new StopAssignmentStructure();
		res.getAimedQuayName().add(createNaturalLanguageStringStructure(value));

		return res;
	}

	public static int randInt(final int min, final int max) {

		// Usually this should be a field rather than a method variable so
		// that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	public static DestinationRefStructure createDestinationRefStructure(final String value) {

		DestinationRefStructure res = new DestinationRefStructure();
		res.setValue(value);

		return res;
	}

	public static LineRefStructure createLineRefStructure(final String value) {

		LineRefStructure res = new LineRefStructure();
		res.setValue(value);

		return res;
	}

}
