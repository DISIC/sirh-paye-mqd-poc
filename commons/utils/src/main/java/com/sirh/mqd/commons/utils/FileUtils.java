package com.sirh.mqd.commons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Utilitaire pour les fichiers
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
	public static String lectureFichier(final String nomFichier) throws IOException {
		StringBuilder res = new StringBuilder();

		//Lecture du fichier
		InputStream ips = new FileInputStream(nomFichier);

		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		try {
			String ligne = br.readLine();

			while (ligne != null) {
				if (res.length() > 0) {
					res.append("\n");
				}
				res.append(ligne);
				ligne = br.readLine();
			}
		} finally {
			br.close();
		}

		return res.toString();
	}

	/**
	 * Lire un inputstream
	 *
	 * @param ips
	 *            : l'input stream
	 * @return la string représentant l'inputstream
	 * @throws IOException
	 */
	public static String lireInputStream(final InputStream ips) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(ips));
		try {
			StringBuilder retour = new StringBuilder();
			String ligne = br.readLine();
			while (ligne != null) {
				retour.append(ligne);
				ligne = br.readLine();
			}

			return retour.toString();
		} finally {
			br.close();
		}
	}

	/**
	 * Ecrire dans fichier une string
	 *
	 * @param contenu
	 *            : la string à écrire
	 * @param nomFichier
	 *            : le nom du fichier
	 * @throws IOException
	 */
	public static void ecrireStringDansFichier(final String contenu, final String nomFichier) throws IOException {
		FileWriter writer = new FileWriter(nomFichier);
		try {
			writer.write(contenu);

		} finally {
			writer.close();
		}
	}

	/**
	 * Récupère un fichier à partir de son chemin pour en faire un InputStream. Lève une exception si le fichier n'existe pas.
	 *
	 * @param resource
	 *            Chemin du fichier
	 *
	 * @return L'inputStream correspondant au fichier chargé
	 *
	 * @throws FileNotFoundException
	 *
	 */
	public static InputStream getInputStream(final String resource) throws FileNotFoundException {
		final InputStream is = GenericUtils.class.getResourceAsStream(resource);
		if (is == null) {
			throw new FileNotFoundException(resource);
		}
		return is;
	}

	/**
	 * Liste les noms de fichiers dans un répertoire correspondant au préfixe et suffixe
	 *
	 * @param pathname
	 *            Répertoire dans lequel chercher les fichiers
	 * @param filenamePrefix
	 *            Préfixe que le fichier doit avoir pour faire partie de la liste retournée
	 * @param filenameSuffix
	 *            Suffixe que le fichier doit avoir pour faire partie de la liste retournée
	 * @return List<String> une liste de noms de fichiers correspondant au préfixe et suffixe dans le répertoire donné en paramètre
	 */
	public static List<String> getFilenamesInPath(final String pathname, final String filenamePrefix, final String filenameSuffix) {
		List<String> filenames = new ArrayList<String>();
		File path;
		if (StringUtils.isEmpty(pathname)) {
			path = new File(".");
		} else {
			path = new File(pathname);
		}
		File[] files = path.listFiles();
		for (File file : files) {
			String filename = file.getName();
			if (file.isFile() && filename.toLowerCase().startsWith(filenamePrefix) && filename.toLowerCase().endsWith(filenameSuffix)) {
				filenames.add(filename);
			}
		}
		return filenames;
	}

	/**
	 * Retourne le dernier nom après un classement par ordre alphabétique sans tenir compte de la casse.
	 *
	 * @param filenames
	 *            La liste de noms en entrée
	 * @return String Le dernier nom après un classement par ordre alphabétique sans tenir compte de la casse
	 */
	public static String getLastDatedFilename(final List<String> filenames) {
		String lastDatedFilename = "";
		if (!CollectionUtils.isEmpty(filenames)) {
			lastDatedFilename = filenames.get(0);
		}
		for (String filename : filenames) {
			if (!StringUtils.isEmpty(filename) && lastDatedFilename.compareToIgnoreCase(filename) < 0) {
				lastDatedFilename = filename;
			}
		}
		return lastDatedFilename;
	}

	/**
	 * suppression d'un fichier
	 *
	 * @param nomFichier
	 *            le nom du fichier
	 */
	public static void suppressionFichier(final String nomFichier) throws IOException {
		File fileToDelete = new File(nomFichier);
		fileToDelete.delete();
	}

	/**
	 * Supprime le contenu du dossier sauf le fichier en parametre
	 *
	 * @param nomDossier
	 * @param nomFichier
	 * @throws IOException
	 */
	public static void suppressionDossier(final String nomFichier) throws IOException {
		File fileToDelete = new File(nomFichier);
		File dir = new File(fileToDelete.getParent());

		for (File file : dir.listFiles()) {

			if (!file.getAbsolutePath().equals(nomFichier)) {
				file.delete();
			}

		}

	}

}
