package com.thalesgroup.stif.bouchon.producteur.redis.tests;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public abstract class AbstractTestCase implements ITestCase {

	/**
	 * Nombre de notification envoyé la derniere seconde
	 */
	private int countNotificationSend = 0;

	/**
	 * Contient les données du fichier CSV
	 */
	private List<String[]> fileData;

	public abstract void init() throws IOException;

	public void initFileReader(final String filepath) throws IOException {

		Reader reader = new FileReader(filepath);
		CSVReader<String[]> csvParser = CSVReaderBuilder.newDefaultReader(reader);
		fileData = csvParser.readAll();

	}

	public void incrementeCountNotificationSend() {
		countNotificationSend++;
	}

	public int getCountNotificationSend() {
		return countNotificationSend;
	}

	/**
	 * @return the fileData
	 */
	public List<String[]> getFileData() {
		return fileData;
	}

}
