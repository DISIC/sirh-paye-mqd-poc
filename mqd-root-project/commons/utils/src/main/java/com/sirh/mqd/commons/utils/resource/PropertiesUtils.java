package com.sirh.mqd.commons.utils.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sirh.mqd.commons.utils.exception.TechnicalException;

/**
 * Class contenant des méthodes permettant de charger un fichier de proprietes
 *
 * @author alexandre
 */
public abstract class PropertiesUtils {

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 *
	 * @param filename
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws TechnicalException
	 *             erreur
	 */
	public static Properties load(final String filename) throws TechnicalException {
		final Properties properties = new Properties();
		try {
			final FileInputStream fileInputStream = new FileInputStream(filename);
			properties.load(fileInputStream);
			return properties;
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 *
	 * @param file
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws TechnicalException
	 *             erreur
	 */
	public static Properties load(final File file) throws TechnicalException {

		if (!file.exists()) {
			throw new TechnicalException("Missing file : " + file.getAbsolutePath());
		}
		final Properties properties = new Properties();
		try {
			final FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
			return properties;
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 *
	 * @param filename
	 *            le fichier contenant les propriétés
	 * @return un objet Properties contenant les propriétés du fichier
	 * @throws TechnicalException
	 *             erreur
	 */
	public static Properties loadFromClasspath(final String filename) throws TechnicalException {
		final Properties properties = new Properties();
		try {
			final InputStream inputStream = ClasspathUtils.getResourceAsStream(filename);
			properties.load(inputStream);
			return properties;
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}
}