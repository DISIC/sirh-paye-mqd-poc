package com.thalesgroup.stif.testlogsrolling;

import java.io.FileWriter;
import java.io.IOException;

import ch.qos.logback.classic.Level;

import com.thalesgroup.stif.commons.traces.api.IFacadeLogs;
import com.thalesgroup.stif.commons.traces.logs.FacadeLogs;

/**
 * @author stephane
 */
public class TestLogsRolling {

	private static FileWriter outputStream;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		IFacadeLogs facadeLogs = new FacadeLogs(TestLogsRolling.class);
		facadeLogs.logSiri("TestLogsRolling");
		facadeLogs.logError("TestLogsRolling", new Exception("Exception"));
		facadeLogs.logTechnique(Level.INFO, "logTechnique");
		facadeLogs.logDebug(Level.INFO, "logDebug");

		int nFiles = 4;
		int nLignes = 1000000;

		for (int iFiles = 0; iFiles < 4; iFiles++) {
			for (int i = iFiles * nLignes; i < (iFiles + 1) * nLignes; i++) {
				facadeLogs.logSiri("TestLogsRolling" + i);
			}
		}
	}
}