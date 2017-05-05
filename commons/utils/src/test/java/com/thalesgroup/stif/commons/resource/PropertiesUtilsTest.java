package com.thalesgroup.stif.commons.resource;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.sirh.mqd.commons.resource.ClasspathUtils;
import com.sirh.mqd.commons.resource.PropertiesUtils;

/**
 * Teste la classe PropertiesUtils
 */
public class PropertiesUtilsTest {

	/**
	 * Cste : nom du fichier choisi pour ce test
	 */
	private static final String FILE_NAME = "test.properties";

	@Test
	public void testAbstractClass() {
		PropertiesUtils classPathUtils = new PropertiesUtils() {
		};
		Assert.assertNotNull(classPathUtils);
	}

	@Test
	public void testChargementFichier() throws Exception {
		// fichier choisi pour ce test
		File file = ClasspathUtils.getResourceFile(FILE_NAME);

		PropertiesUtils.load(file);

		String filename = file.toString();
		// ok, pas d'echec
		PropertiesUtils.load(filename);
		// ok, pas d'echec
		PropertiesUtils.loadFromClasspath(FILE_NAME);
	}

}
