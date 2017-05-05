package com.thalesgroup.stif.commons.tampon.dao.impl;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.impl.BatchRequestDAO;
import com.sirh.mqd.commons.storage.dao.impl.NotificationArretSouhaiteDAO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.thalesgroup.stif.commons.echange.dto.ArretSouhaiteDTO;
import com.thalesgroup.stif.commons.echange.dto.reception.CheckStatusRequestDTO;
import com.thalesgroup.stif.commons.echange.dto.reception.GeneralMessageRequestDTO;
import com.thalesgroup.stif.commons.echange.dto.reception.GetSiriRequestDTO;
import com.thalesgroup.stif.commons.echange.dto.reception.StopMonitoringRequestDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistence-datasource.xml")
@Ignore
public class BatchRequestDAOTest {

	@Autowired
	@Qualifier(PersistenceConstantes.BATCH_REQUEST_DAO)
	private BatchRequestDAO batchRequestDAO;

	@Inject
	private NotificationArretSouhaiteDAO notificationArretSouhaiteDAO;

	@Test
	public void testDeposerCheckStatus() throws Exception {

		CheckStatusRequestDTO checkStatusRequestDTO = new CheckStatusRequestDTO() {

			{
				setAddress("http://localhost:8080/test15Participant");
			}
		};
		batchRequestDAO.deposerRequeteCheckStatus(checkStatusRequestDTO);

	}

	@Test
	public void testDeposerRequeteStopMonitoring() throws Exception {

		StopMonitoringRequestDTO stopMonitoringRequestDTO = new StopMonitoringRequestDTO() {

			{
				setVersion("2.4");
				setAddress("http://localhost:8088/bouchon-producteur");
				setMonitoringRef("id1");
			}
		};
		batchRequestDAO.deposerRequeteStopMonitoring(stopMonitoringRequestDTO);

	}

	@Test
	public void testDeposerRequeteGeneralMessage() throws Exception {

		GeneralMessageRequestDTO GeneralMessageRequestDTO = new GeneralMessageRequestDTO() {

			{
				setVersion("2.4");
				setAddress("http://localhost:8088/bouchon-producteur");
			}
		};
		batchRequestDAO.deposerRequeteGeneralMessage(GeneralMessageRequestDTO);

	}

	@Test
	public void testDeposerRequeteGetSiri() throws Exception {

		GetSiriRequestDTO getSiriDTO = new GetSiriRequestDTO() {

			{
				setVersion("2.4");
				setAddress("http://localhost:8080/test31ParticipantConnu");
				setListIdentifiantArrets(Arrays.asList("id1", "id2"));
			}
		};
		batchRequestDAO.deposerRequeteGetSiri(getSiriDTO);

	}

	@Test
	public void testDeposerNotificationArretSouhaite() throws Exception {

		ArretSouhaiteDTO arretSouhaiteDTO = new ArretSouhaiteDTO("ZDE_Chatillon", DateUtils.getCalendarInstance().getTime());
		notificationArretSouhaiteDAO.deposerNotification(arretSouhaiteDTO);

	}
}
