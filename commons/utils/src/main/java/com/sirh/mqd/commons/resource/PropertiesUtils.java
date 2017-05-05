package com.sirh.mqd.commons.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class contenant des méthodes permettant de charger un fichier de proprietes
 */
public abstract class PropertiesUtils {

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 * 
	 * @param filename
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws IOException
	 *             erreur
	 */
	public static Properties load(final String filename) throws IOException {
		Properties properties = new Properties();

		try (FileInputStream fileInputStream = new FileInputStream(filename);) {
			properties.load(fileInputStream);
			return properties;
		}

	}

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 * 
	 * @param file
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws IOException
	 *             erreur
	 */
	public static Properties load(final File file) throws IOException {
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		Properties properties = new Properties();

		try (FileInputStream fileInputStream = new FileInputStream(file);) {
			properties.load(fileInputStream);
			return properties;
		}

	}

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 * 
	 * @param filename
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws IOException
	 *             erreur
	 */
	public static Properties loadFromClasspath(final String filename) throws IOException {
		Properties properties = new Properties();

		try (InputStream inputStream = ClasspathUtils.getResourceAsStream(filename);) {
			properties.load(inputStream);
			return properties;
		}
	}
}