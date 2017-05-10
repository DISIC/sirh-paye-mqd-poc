package com.sirh.mqd.commons.utils.resource;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.sirh.mqd.commons.utils.exception.TechnicalException;

/**
 * Lecture de fichiers dans le classpath
 *
 * @author alexandre
 */
public final class ClasspathUtils {

	/**
	 * @param resourcePath
	 *            le path de la resource
	 * @return une handle sur le fichier dont le path est en paramètre
	 * @throws TechnicalException
	 *             si le fichier n'existe pas
	 */
	public static File getResourceFile(final String resourcePath) throws TechnicalException {
		final URL resource = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
		try {
			return new File(resource.toURI());
		} catch (final NullPointerException | IllegalArgumentException | URISyntaxException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * InputStream. Ne pas oublier d'appeller
	 * {@link IOUtils#closeQuietly(InputStream)} en fin de lecture dans un bloc
	 * finally, ou d'utiliser un try-with-resource Java 7
	 *
	 * @param resourceName
	 * @return un inputStream portant sur le fichier dont le nom est passé en
	 *         paramètre
	 * @throws TechnicalException
	 */
	public static InputStream getResourceAsStream(final String resourceName) throws TechnicalException {
		final InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new TechnicalException("Missing resource : " + resourceName);
		}
		return inputStream;
	}
}
