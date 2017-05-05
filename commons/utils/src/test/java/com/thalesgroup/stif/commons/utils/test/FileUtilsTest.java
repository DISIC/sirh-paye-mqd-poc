package com.thalesgroup.stif.commons.utils.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.FileUtils;

public class FileUtilsTest {

	@Test(expected = InstantiationException.class)
	public void testConstructeur() throws InstantiationException {
		new FileUtils();
	}

	@Test
	public void testLectureFichier() throws IOException {
		String fichier = FileUtils.lectureFichier("./target/test-classes/lecture.txt");

		Assertions.assertThat(fichier).isNotNull();
		Assertions.assertThat(fichier).isEqualTo("Le test\nDe lecture");
	}

	@Test
	public void testEcritureFichier() throws IOException {
		Date d = DateUtils.getCalendarInstance().getTime();
		String nomFichier = "./target/test-classes/ecriture.txt";
		StringBuilder nom = new StringBuilder();
		nom.append("La date du jour est : ").append(DateUtils.formateDateJJMMAAAAhhmmss(d));

		FileUtils.ecrireStringDansFichier(nom.toString(), nomFichier);

		Assertions.assertThat(FileUtils.lectureFichier(nomFichier)).isEqualTo(nom.toString());
	}

	@Test
	public void testGetInputStream00CasNominal() throws FileNotFoundException {
		final InputStream ips = FileUtils.getInputStream("/lecture.txt");
		Assertions.assertThat(ips).isNotNull();
	}

	@Test(expected = FileNotFoundException.class)
	public void testGetInputStream00FichierInexistant() throws FileNotFoundException {
		FileUtils.getInputStream("/lect.txt");
	}

	@Test
	public void testGetLastDatedFilename() {
		List<String> filenames = Arrays.asList("fichier_201401011830.csv", "fichier_201401011630.csv", "fichier_201401011730.csv");
		String lastDatedFileName = FileUtils.getLastDatedFilename(filenames);
		Assertions.assertThat(lastDatedFileName).isEqualTo("fichier_201401011830.csv");

		filenames = Arrays.asList("a", "c", "b");
		lastDatedFileName = FileUtils.getLastDatedFilename(filenames);
		Assertions.assertThat(lastDatedFileName).isEqualTo("c");

		filenames = Arrays.asList("reflex_201401010000.csv", "reflex_201502010000.csv", "reflex_201501010000.csv");
		lastDatedFileName = FileUtils.getLastDatedFilename(filenames);
		Assertions.assertThat(lastDatedFileName).isEqualTo("reflex_201502010000.csv");

	}

	@Test
	public void testSuppressionFichier() throws IOException {
		String nomFichier = "./target/test-classes/suppression.txt";
		File file = new File(nomFichier);
		FileUtils.suppressionFichier(nomFichier);
		Assertions.assertThat(file.exists()).isEqualTo(false);

	}
}
