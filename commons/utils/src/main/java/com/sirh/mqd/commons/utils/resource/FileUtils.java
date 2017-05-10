package com.sirh.mqd.commons.utils.resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.sirh.mqd.commons.utils.GenericUtils;
import com.sirh.mqd.commons.utils.constante.Constantes;
import com.sirh.mqd.commons.utils.exception.TechnicalException;

/**
 * Classe utilitaire pour la gestion des fichiers et des répertoires
 *
 * @author alexandre
 */
public final class FileUtils {

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public FileUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + FileUtils.class.getName());
	}

	/**
	 * Lecture d'un fichier image
	 *
	 * @param nomFichier
	 *            le nom du fichier image
	 * @return un tableau de byte
	 */
	public static String lectureFichier(final String nomFichier) throws TechnicalException {
		final StringBuilder res = new StringBuilder();
		// Lecture du fichier
		BufferedReader br = null;
		try {
			final InputStream ips = new FileInputStream(nomFichier);
			final InputStreamReader ipsr = new InputStreamReader(ips);
			br = new BufferedReader(ipsr);
			String ligne = br.readLine();
			while (ligne != null) {
				if (res.length() > 0) {
					res.append(Constantes.FIN_LIGNE);
				}
				res.append(ligne);
				ligne = br.readLine();
			}
		} catch (final IOException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(br);
		}
		return res.toString();
	}

	/**
	 * Lire un inputstream
	 *
	 * @param ips
	 *            : l'input stream
	 * @return la string représentant l'inputstream
	 * @throws TechnicalException
	 */
	public static String lireInputStream(final InputStream ips) throws TechnicalException {
		final BufferedReader br = new BufferedReader(new InputStreamReader(ips));
		try {
			final StringBuilder retour = new StringBuilder();
			String ligne = br.readLine();
			while (ligne != null) {
				retour.append(ligne);
				ligne = br.readLine();
			}
			return retour.toString();
		} catch (final IOException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(br);
		}
	}

	/**
	 * Ecrire dans fichier une string
	 *
	 * @param contenu
	 *            la string à écrire
	 * @param nomFichier
	 *            le nom du fichier
	 * @throws TechnicalException
	 */
	public static void ecrireStringDansFichier(final String contenu, final String nomFichier)
			throws TechnicalException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(nomFichier);
			writer.write(contenu);
		} catch (final IOException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	/**
	 * Récupère un fichier à partir de son chemin pour en faire un InputStream.
	 * Lève une exception si le fichier n'existe pas.
	 *
	 * @param resource
	 *            Chemin du fichier
	 *
	 * @return L'inputStream correspondant au fichier chargé
	 *
	 * @throws TechnicalException
	 *
	 */
	public static InputStream getInputStream(final String resource) throws TechnicalException {
		final InputStream is = GenericUtils.class.getResourceAsStream(resource);
		if (is == null) {
			throw new TechnicalException("Missing ressource : " + resource);
		}
		return is;
	}

	/**
	 * Liste les noms de fichiers dans un répertoire correspondant au préfixe et
	 * suffixe
	 *
	 * @param pathname
	 *            Répertoire dans lequel chercher les fichiers
	 * @param filenamePrefix
	 *            Préfixe que le fichier doit avoir pour faire partie de la
	 *            liste retournée
	 * @param filenameSuffix
	 *            Suffixe que le fichier doit avoir pour faire partie de la
	 *            liste retournée
	 * @return List<String> une liste de noms de fichiers correspondant au
	 *         préfixe et suffixe dans le répertoire donné en paramètre
	 */
	public static List<String> getFilenamesInPath(final String pathname, final String filenamePrefix,
			final String filenameSuffix) {
		final List<String> filenames = new ArrayList<String>();
		File path;
		if (StringUtils.isEmpty(pathname)) {
			path = new File(Constantes.DOT);
		} else {
			path = new File(pathname);
		}
		final File[] files = path.listFiles();
		for (final File file : files) {
			final String filename = file.getName();
			if (file.isFile() && filename.toLowerCase().startsWith(filenamePrefix)
					&& filename.toLowerCase().endsWith(filenameSuffix)) {
				filenames.add(filename);
			}
		}
		return filenames;
	}

	/**
	 * Retourne le dernier nom après un classement par ordre alphabétique sans
	 * tenir compte de la casse.
	 *
	 * @param filenames
	 *            La liste de noms en entrée
	 * @return String Le dernier nom après un classement par ordre alphabétique
	 *         sans tenir compte de la casse
	 */
	public static String getLastDatedFilename(final List<String> filenames) {
		String lastDatedFilename = StringUtils.EMPTY;
		if (!CollectionUtils.isEmpty(filenames)) {
			lastDatedFilename = filenames.get(0);
		}
		for (final String filename : filenames) {
			if (!StringUtils.isEmpty(filename) && lastDatedFilename.compareToIgnoreCase(filename) < 0) {
				lastDatedFilename = filename;
			}
		}
		return lastDatedFilename;
	}

	/**
	 * Suppression d'un fichier
	 *
	 * @param nomFichier
	 *            le nom du fichier
	 */
	public static void suppressionFichier(final String nomFichier) {
		final File fileToDelete = new File(nomFichier);
		fileToDelete.delete();
	}

	/**
	 * Supprime le contenu du dossier sauf le fichier en parametre
	 *
	 * @param nomDossier
	 * @param nomFichier
	 */
	public static void suppressionDossier(final String nomFichier) {
		final File fileToDelete = new File(nomFichier);
		final File dir = new File(fileToDelete.getParent());
		for (final File file : dir.listFiles()) {
			if (!file.getAbsolutePath().equals(nomFichier)) {
				file.delete();
			}
		}
	}
}
