package com.sirh.mqd.commons.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * Lecture de fichiers dans le classpath
 * 
 */
public final class ClasspathUtils {

	/**
	 * 
	 * @param resourcePath
	 *            le path de la resource
	 * @return une handle sur le fichier dont le path est en paramètre
	 * @throws FileNotFoundException
	 *             si le fichier n'existe pas
	 */
	public static File getResourceFile(final String resourcePath) throws FileNotFoundException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource(resourcePath);
		if (resource == null) {
			throw new FileNotFoundException(resourcePath);
		}
		try {
			return new File(resource.toURI());
		}
		// java.lang.IllegalArgumentException: URI is not hierarchical
		catch (IllegalArgumentException e) // CATCH OK
		{
			throw new FileNotFoundException(resourcePath + " " + e.getMessage());// NOPMD
		} catch (URISyntaxException e) // CATCH OK
		{
			throw new FileNotFoundException("URISyntaxException " + resourcePath + " " + e.getMessage());// NOPMD
		}
	}

	/**
	 * InputStream. Ne pas oublier d'appeller {@link IOUtils#closeQuietly(InputStream)} en fin de lecture dans un bloc finally, ou
	 * d'utiliser un try-with-resource Java 7
	 * 
	 * @param resourceName
	 * @return un inputStream portant sur le fichier dont le nom est passé en paramètre
	 * @throws FileNotFoundException
	 */
	public static InputStream getResourceAsStream(final String resourceName) throws FileNotFoundException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new FileNotFoundException(resourceName);
		}
		return inputStream;
	}
}
