package com.sirh.mqd.commons.traces.enums;

/**
 * Enumération listant les technologies associées au projet
 *
 * @author alexandre
 */
public enum InteractionToolEnum {
	/**
	 * Serveur SFTP
	 */
	SFTP,
	/**
	 * Web services REST
	 */
	WS_REST,
	/**
	 * Lecture de fichier présent dans un répertoire local
	 */
	FILE_READER,
	/**
	 * Chargement de properties présent dans un fichier
	 */
	PROPERTIES_LOADER,
	/**
	 * Conversion de fichier CSV
	 */
	CSV,
	/**
	 * Base de données MongoDB
	 */
	MONGODB,
	/**
	 * Interface graphique
	 */
	IHM,
	/**
	 * Tout le reste. Mais, tant qu'on peut, il faut définir un enum pour chaque
	 * technologie.
	 */
	OTHER;
}
