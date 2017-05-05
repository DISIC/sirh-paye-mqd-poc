/**
 *
 */
package com.thalesgroup.stif.commons.traces.logs;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;

import com.sirh.mqd.commons.traces.dto.LogActionDTO;
import com.sirh.mqd.commons.traces.dto.LogPerformanceDTO;
import com.sirh.mqd.commons.traces.dto.LogTechniqueDTO;
import com.sirh.mqd.commons.traces.dto.LogWorkflowDTO;
import com.sirh.mqd.commons.traces.enums.EnumActionNature;
import com.sirh.mqd.commons.traces.enums.EnumActionType;
import com.sirh.mqd.commons.traces.enums.EnumEcranType;
import com.sirh.mqd.commons.traces.enums.LogType;
import com.sirh.mqd.commons.traces.enums.UserActionEnum;
import com.sirh.mqd.commons.traces.logs.FacadeLogs;
import com.sirh.mqd.commons.traces.logs.LogActionFactory;
import com.sirh.mqd.commons.traces.logs.LogPerformanceFactory;
import com.sirh.mqd.commons.traces.logs.LogTechniqueFactory;
import com.sirh.mqd.commons.traces.logs.LogWorkflowFactory;

/**
 * @author stephane
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Logger.class)
public class FacadeLogsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
	}

	@Test
	public void testLogPerformance() {
		FacadeLogs facadeLogs = Mockito.spy(new FacadeLogs(FacadeLogsTest.class));
		LogPerformanceDTO logPerf = LogPerformanceFactory.getLogPerformance(new Date(), "composant", "service", 600, 650, "identifiant");
		facadeLogs.logPerformance(logPerf);
	}

	@Test
	public void testLogTechniqueError() {
		FacadeLogs facadeLogs = Mockito.spy(new FacadeLogs(FacadeLogsTest.class));
		LogTechniqueDTO logTechnique = LogTechniqueFactory.getLogTechnique(new Date(), LogType.OTHER, this.getClass().getName(), null,
				"exception");
		facadeLogs.logTechniqueError(logTechnique);

	}

	@Test
	public void testLogTechniquewarn() {
		FacadeLogs facadeLogs = Mockito.spy(new FacadeLogs(FacadeLogsTest.class));
		LogTechniqueDTO logTechnique = LogTechniqueFactory.getLogTechnique(new Date(), LogType.OTHER, this.getClass().getName(),
				"Nom du message", "Contenu du message");
		facadeLogs.logTechniqueWarn(logTechnique);

	}

	@Test
	public void testLogAction() {
		FacadeLogs facadeLogs = Mockito.spy(new FacadeLogs(FacadeLogsTest.class));

		LogActionDTO logAction = LogActionFactory.getLogAction(UserActionEnum.ACTION_IHM, "email", "role", null,
				EnumActionType.MODIFICATION, EnumActionNature.AUTHENTIFICATION_FAILURE, EnumEcranType.ACCUEIL, "idMetier", "obj",
				"objOldValue");
		facadeLogs.logAction(logAction);
	}

	@Test
	public void testLogWorkflow() {
		FacadeLogs facadeLogs = Mockito.spy(new FacadeLogs(FacadeLogsTest.class));
		LogWorkflowDTO logWorkflow = LogWorkflowFactory.getLogWorkflow("content");
		facadeLogs.logWorkflowDebug(logWorkflow);
		facadeLogs.logWorkflowInfo(logWorkflow);
	}

}
