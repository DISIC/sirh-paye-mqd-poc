package com.sirh.mqd.supplier.core.constantes;

/**
 * Constantes propres à PAY.
 *
 * @author alexandre
 */
public final class DgacConstantes {

	// public static final String TXT_FILE_ABSENCE =
	// "Absence.txt";

	// public static final String TXT_FILE_ENFANTS =
	// "Enfants.txt";

	public static final String TXT_FILE_ADRESSE = "MQD_DONNEES_GA_ADRESSE.txt";

	public static final String TXT_FILE_BANQUE = "MQD_DONNEES_GA_BANQUE.txt";

	public static final String TXT_FILE_CARRIERE = "MQD_DONNEES_GA_CARRIERE.txt";

	public static final String TXT_FILE_PENSION = "MQD_DONNEES_GA_PENSION.txt";

	// public static final String TXT_FILE_PROCHAIN =
	// "MQD_DONNEES_GA_PROCHAIN.txt";

	// public static final String TXT_FILE_RETRAITE =
	// "MQD_DONNEES_GA_RETRAITE.txt";

	public static final String TXT_FILE_ETAT_CIVIL = "MQD_DONNEES_GA_CIVIL.txt";

	public static final String TXT_FILE_NBI = "MQD_DONNEES_GA_NBI.txt";

	public static final String TXT_FILE_POSITION = "MQD_DONNEES_GA_POSITION.txt";

	public static final String TXT_FILE_TEMPS_TRAVAIL = "MQD_DONNEES_GA_TEMPS.txt";

	public static final String TXT_FILE_TG = "MQD_DONNEES_GA_TG.txt";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private DgacConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + DgacConstantes.class.getName());
	}
}
