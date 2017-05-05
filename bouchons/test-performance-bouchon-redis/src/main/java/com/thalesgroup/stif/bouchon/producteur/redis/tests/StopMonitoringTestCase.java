package com.thalesgroup.stif.bouchon.producteur.redis.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.thalesgroup.stif.bouchon.producteur.redis.constantes.ServicesConstantes;
import com.thalesgroup.stif.bouchon.producteur.redis.notify.StopMonitoringNotify;
import com.thalesgroup.stif.bouchon.producteur.redis.util.BouchonUtils;
import com.thalesgroup.stif.commons.echange.dto.pivot.Course;
import com.thalesgroup.stif.commons.echange.dto.pivot.LangueStructure;
import com.thalesgroup.stif.commons.echange.dto.pivot.Passage;
import com.thalesgroup.stif.commons.echange.dto.reception.NotifyStopMonitoringDTO;
import com.thalesgroup.stif.siri.v24.uk.org.siri.siri.CallStatusEnumeration;

@Component(ServicesConstantes.TEST_SM)
public class StopMonitoringTestCase extends AbstractTestCase implements ITestCase {

	@Autowired
	@Qualifier(ServicesConstantes.NOTIFICATION_SM)
	private StopMonitoringNotify smNotify;

	/**
	 * Injection des properties du fichier application.properties
	 */
	@Value("#{application}")
	private Properties properties;

	/**
	 * Données du fichier CSV
	 */
	private List<String[]> data;

	private enum CSV_INDEX {
		PARTICIPANT_REF, ITEM_IDENTIFIER, MONITORING_REF, VEHICLE_AT_STOP, STOP_POINTREF, LINE_REF, ROUTE_REF, DATA_FRAMEREF, DATA_VEHICLE_JOURNEYREF, DESTINATION_REF, DELAY, JOURNEY_PATTERN_REF, JOURNEY_PATTERN_NAME
	};

	@PostConstruct
	@Override
	public void init() throws IOException {

		//	on lit le contenu du fichier csv
		initFileReader(properties.getProperty("smFilePath"));
		data = getFileData();
	}

	public void sendNotification() {

		NotifyStopMonitoringDTO notification = new NotifyStopMonitoringDTO();

		// Creation des passages
		List<Passage> passageAMaj = createPassage();
		notification.getPassageAMaj().addAll(passageAMaj);

		// Creation des passages
		List<Passage> passageASupr = createPassageToDelete();
		notification.getPassageASupr().addAll(passageASupr);

		smNotify.send(notification);
		incrementeCountNotificationSend();

	}

	/**
	 * Créer une liste de passage à supprimer
	 *
	 * @return
	 */
	private List<Passage> createPassageToDelete() {

		int min = Integer.valueOf(properties.getProperty("smPassageCancellationMin"));
		int max = Integer.valueOf(properties.getProperty("smPassageCancellationMax"));

		int nbPassage = BouchonUtils.randInt(min, max);

		List<Passage> passageAMaj = new ArrayList<Passage>();

		for (int i = 0; i < nbPassage; i++) {

			int rdm = BouchonUtils.randInt(0, data.size() - 1);
			String[] element = data.get(rdm);

			Passage passage = new Passage();
			passage.setRecordedAtTime(new Date());
			passage.setItemIdentifier(element[CSV_INDEX.ITEM_IDENTIFIER.ordinal()]);
			passage.setMonitoringRef(element[CSV_INDEX.MONITORING_REF.ordinal()]);

			Course course = new Course();
			course.setLineRef(element[CSV_INDEX.LINE_REF.ordinal()]);

			passage.setCourse(course);

			passageAMaj.add(passage);
		}

		return passageAMaj;
	}

	/**
	 * Créer une liste de passage aleatoire
	 *
	 * @return
	 */
	private List<Passage> createPassage() {

		int min = Integer.valueOf(properties.getProperty("smPassageMin"));
		int max = Integer.valueOf(properties.getProperty("smPassageMax"));

		int nbPassage = BouchonUtils.randInt(min, max);

		List<Passage> passageAMaj = new ArrayList<Passage>();

		for (int i = 0; i < nbPassage; i++) {

			int rdm = BouchonUtils.randInt(1, data.size() - 1);
			String[] element = data.get(rdm);

			Calendar calendarDeparture = Calendar.getInstance();
			//			calendarDeparture.add(Calendar.DAY_OF_MONTH, 4);

			Calendar calendarArrival = Calendar.getInstance();
			calendarArrival.add(Calendar.HOUR_OF_DAY, 24);

			Passage passage = new Passage();
			passage.setParticipantRef(element[CSV_INDEX.PARTICIPANT_REF.ordinal()]);

			passage.setActualArrivalTime(calendarArrival.getTime());
			passage.setActualDepartureTime(calendarDeparture.getTime());
			passage.setAimedArrivalTime(calendarArrival.getTime());
			passage.setAimedDepartureTime(calendarDeparture.getTime());
			passage.setExpectedArrivalTime(calendarArrival.getTime());
			passage.setExpectedDepartureTime(calendarDeparture.getTime());

			passage.setArrivalStatus(CallStatusEnumeration.ON_TIME.name());
			passage.setDepartureStatus(CallStatusEnumeration.ON_TIME.name());

			passage.setRecordedAtTime(new Date());
			passage.setItemIdentifier(element[CSV_INDEX.ITEM_IDENTIFIER.ordinal()]);
			passage.setMonitoringRef(element[CSV_INDEX.MONITORING_REF.ordinal()]);

			passage.setStopPointRef(element[CSV_INDEX.STOP_POINTREF.ordinal()]);
			passage.setOrder(i);
			passage.setVehicleAtStop(Boolean.valueOf(element[CSV_INDEX.VEHICLE_AT_STOP.ordinal()]));

			Course course = new Course();
			course.setLineRef(element[CSV_INDEX.LINE_REF.ordinal()]);
			course.setRouteRef(element[CSV_INDEX.ROUTE_REF.ordinal()]);
			course.setJourneyPatternRef(element[CSV_INDEX.JOURNEY_PATTERN_REF.ordinal()]);
			LangueStructure journeyPatternName = new LangueStructure("fr", element[CSV_INDEX.JOURNEY_PATTERN_NAME.ordinal()]);
			course.setJourneyPatternName(journeyPatternName);

			course.setDataFrameRef(element[CSV_INDEX.DATA_FRAMEREF.ordinal()]);
			course.setDatedVehicleJourneyRef(element[CSV_INDEX.DATA_VEHICLE_JOURNEYREF.ordinal()]);
			course.setDestinationRef(element[CSV_INDEX.DESTINATION_REF.ordinal()]);
			course.setDelay(element[CSV_INDEX.DELAY.ordinal()]);

			passage.setCourse(course);

			passageAMaj.add(passage);
		}

		return passageAMaj;
	}
}
