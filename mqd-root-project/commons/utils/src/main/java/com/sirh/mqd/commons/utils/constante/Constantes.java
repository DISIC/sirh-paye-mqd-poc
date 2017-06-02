package com.sirh.mqd.commons.utils.constante;

/**
 * Constantes génériques au projet.
 *
 * @author alexandre
 */
public class Constantes {

	/**
	 * Constante du dièse
	 */
	public static final String DIESE = "#";

	/**
	 * Constante du guillemet
	 */
	public static final String QUOTE = "\"";

	/**
	 * Constante d'ouverture d'un crochet
	 */
	public static final String OPEN_SQUARE_BRACKET = "[";

	/**
	 * Constante de fermeture d'un crochet
	 */
	public static final String CLOSE_SQUARE_BRACKET = "]";

	/**
	 * Constante d'ouverture d'une parenthèse
	 */
	public static final String OPEN_BRACKET = "(";

	/**
	 * Constante de fermeture d'une parenthèse
	 */
	public static final String CLOSE_BRACKET = ")";

	/**
	 * Constante d'ajout d'un espace
	 */
	public static final String SPACE = " ";

	/**
	 * Constante d'ajout d'un caractère virgule
	 */
	public static final String COMMA = ",";

	/**
	 * Constante d'ajout d'un caractère point
	 */
	public static final String DOT = ".";

	/**
	 * Constante d'ajout d'un caractère tiret (trait d'union : hyphen)
	 */
	public static final String DASH = "-";

	/**
	 * Constante d'ajout d'un caractère slash entouré d'espaces
	 */
	public static final String SLASH_SPACED = " / ";

	/**
	 * Constante d'ajout d'un caractère slash
	 */
	public static final String SLASH = "/";

	/**
	 * Constante d'ajout d'un caractère deux points
	 */
	public static final String COLON = ":";

	/**
	 * Chevron ouvrant <code>&lt;</code>.
	 */
	public static final String OPEN_CHEVRON = "<";

	/**
	 * Chevron fermant <code>&gt;</code>.
	 */
	public static final String CLOSE_CHEVRON = ">";

	/**
	 * Fin de ligne, UNIX.
	 */
	public static final String FIN_LIGNE = "\n";

	/**
	 * En-tête protocole SMTP.
	 */
	public static final String SMTP_PROTOCOL = "smtp://";

	/**
	 * En-tête protocole HTTP.
	 */
	public static final String HTTP_PROTOCOL = "http://";

	/**
	 * Séparateur de fin du protocole.
	 */
	public static final String SEPARATEUR_FIN_PROTOCOLE = "://";

	/**
	 * Constante d'ajout d'un caractère de retour à la ligne selon
	 * l'environnement
	 */
	public static final String LINE_BREAK = System.getProperty("line.separator");

	/**
	 * Séparateur de champs dans les fichiers CSV
	 */
	public static final Character SEPARATOR_CSV = '|';

	/**
	 * Constantes coefficients UnitMeasurement
	 */
	public static final Long KB = 1000L;

	public static final Long MB = 1000000L;

	public static final Long GB = 1000000000L;

	public static final Long TB = 1000000000000L;

	/**
	 * Regex séparateur de caractère speciale
	 */
	public static final String SEPARATEUR_REGEX = "\\";

	/**
	 * Regex entier
	 */
	public static final String REGEX_INTEGER = "d+";

	/**
	 * Valeur null
	 */
	public static final String NULL = "null";

	/**
	 * Extension de fichier CSV
	 */
	public static final String CSV_EXTENSION = ".csv";

	/**
	 * Regex fin de pattern
	 */
	public static final String END_REGEX = "$";

	/**
	 * Regex de format des heures (hh:mm) <br>
	 *
	 * pour les heures : <br>
	 * de 0-23 avec possibilité de mettre un chiffre ou deux. <br>
	 * exemple: 9h00 ou 09h00 <br>
	 *
	 * pour les minutes : <br>
	 * 2 chiffres obligatoires, de 0 à 59.
	 *
	 */
	public static final String FORMAT_HEURES_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private Constantes() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + Constantes.class.getName());
	}
}
