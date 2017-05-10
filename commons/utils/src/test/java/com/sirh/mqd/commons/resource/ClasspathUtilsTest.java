package com.sirh.mqd.commons.resource;

import org.junit.Test;

import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.commons.utils.resource.ClasspathUtils;

/**
 * Teste la classe ClasspathUtils
 */
public class ClasspathUtilsTest {

	/**
	 * Cste : nom du fichier choisi pour ce test
	 */
	private static final String FILE_NAME = "test.properties";

	/**
	 * Cste : nom du faux fichier choisi pour ce test
	 */
	private static final String FILE_NAME_KO = "pasdefichier";

	@Test
	public void testClasspathUtils() throws Exception {
		// ok, pas d'echec
		ClasspathUtils.getResourceFile(FILE_NAME);
		ClasspathUtils.getResourceAsStream(FILE_NAME);
	}

	@Test(expected = TechnicalException.class)
	public void testGetResourceFile() throws Exception {
		// ko, en echec
		ClasspathUtils.getResourceFile(FILE_NAME_KO);
	}

	@Test(expected = TechnicalException.class)
	public void testGetResourceAsStream() throws Exception {
		// ko, en echec
		ClasspathUtils.getResourceAsStream(FILE_NAME_KO);
	}
}
