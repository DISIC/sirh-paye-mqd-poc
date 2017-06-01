package com.sirh.mqd.supplier.core.constantes;

/**
 * Constantes propres à PAY.
 *
 * @author alexandre
 */
public final class PayConstantes {

	public static final String CSV_FILE_ABSENCE = "Absence.csv";

	public static final String CSV_FILE_ADRESSE = "Adresse.csv";

	public static final String CSV_FILE_CARRIERE = "Carriere.csv";

	public static final String CSV_FILE_COOR_BANC = "Coor banc.csv";

	public static final String CSV_FILE_ENFANTS = "Enfants.csv";

	public static final String CSV_FILE_ETAT_CIVIL = "Etat Civil.csv";

	public static final String CSV_FILE_GLOBAL = "Global.csv";

	public static final String CSV_FILE_NBI = "NBI.csv";

	public static final String CSV_FILE_PARAM_RAPPORT = "Parametres du rapport.csv";

	public static final String CSV_FILE_POSITION = "Position.csv";

	public static final String CSV_FILE_TEMPS_TRAVAIL = "Temps de travail.csv";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private PayConstantes() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + PayConstantes.class.getName());
	}
}
