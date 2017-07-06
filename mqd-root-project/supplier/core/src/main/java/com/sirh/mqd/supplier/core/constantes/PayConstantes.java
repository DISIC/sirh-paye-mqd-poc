package com.sirh.mqd.supplier.core.constantes;

/**
 * Constantes propres à PAY.
 *
 * @author alexandre
 */
public final class PayConstantes {

	public static final String CSV_FILE_MSO_ABSENCE = "Absence.csv";

	public static final String CSV_FILE_MSO_ADRESSE = "Adresse.csv";

	public static final String CSV_FILE_MSO_CARRIERE = "Carriere.csv";

	public static final String CSV_FILE_MSO_COOR_BANC = "Coor banc.csv";

	public static final String CSV_FILE_MSO_ENFANTS = "Enfants.csv";

	public static final String CSV_FILE_MSO_ETAT_CIVIL = "Etat Civil.csv";

	public static final String CSV_FILE_MSO_GLOBAL = "Global.csv";

	public static final String CSV_FILE_MSO_NBI = "NBI.csv";

	public static final String CSV_FILE_MSO_PARAM_RAPPORT = "Parametres du rapport.csv";

	public static final String CSV_FILE_MSO_POSITION = "Position.csv";

	public static final String CSV_FILE_MSO_TEMPS_TRAVAIL = "Temps de travail.csv";

	public static final String CSV_FILE_DGAC = "MQD_DONNEES_PAY.txt";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private PayConstantes() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + PayConstantes.class.getName());
	}
}
