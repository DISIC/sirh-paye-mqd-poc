package com.sirh.mqd.commons.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.sirh.mqd.commons.utils.constante.Constantes;
import com.sirh.mqd.commons.utils.exception.TechnicalException;

/**
 * Classe utilitaire.
 *
 * @author alexandre
 */
public final class GenericUtils {

	/**
	 * Séparateur du chemin dans l'URL.
	 */
	public static final String SEPARATEUR_URL = Constantes.SLASH;

	/**
	 * Code indiquant que deux composantes d'une même URL ont un séparateur
	 * commun.
	 */
	private static final int SEPARATEUR_COMMUN = 3;

	/**
	 * Non constructeur.
	 *
	 * @throws InstantiationException
	 *             si tentative d'appel de ce constructeur.
	 */
	public GenericUtils() throws InstantiationException {
		throw new InstantiationException("Création non autorisée d'une instance de : " + GenericUtils.class.getName());
	}

	/**
	 * Méthode permettant de concaténer proprement des objets entre eux. Si
	 * aucun objet n'est passé en paramètre, la méthode renvoie "".
	 *
	 * @param objets
	 *            Tableau d'objets à concaténer
	 *
	 * @return Une chaîne de caractères concaténée.
	 *
	 */
	public static String concat(final Object... objets) {
		if (objets.length == 0) {
			return "";
		}
		final StringBuilder strB = new StringBuilder();
		for (final Object objet : objets) {
			strB.append(objet);
		}
		return strB.toString();
	}

	/**
	 * <p>
	 * Renvoie le suffixe à partir d'un nom de fichier se terminant par un
	 * suffixe. Le séparateur est le point.
	 * </p>
	 *
	 * <p>
	 * Par exemple, à partir du nom de fichier <code>MonArchive.tar.gz</code>,
	 * la méthode renvoie <code>gz</code>.
	 * </p>
	 *
	 * @param nomAvecSuffixe
	 *            Le nom de fichier. Doit se terminer par un suffixe. Par
	 *            exemple, <code>MonArchive.tar.gz</code>.
	 *
	 * @return Le suffixe. Par exemple, <code>gz</code>.
	 *
	 */
	public static String suffixe(final String nomAvecSuffixe) {
		final String separateur = ".";
		return StringUtils.substringAfterLast(nomAvecSuffixe, separateur);
	}

	/**
	 * <p>
	 * Remplace les espaces — y compris les espaces insécables — par des
	 * underscores (<em>_</em>).
	 * </p>
	 *
	 * @param chaineSource
	 *            La chaîne en entrée.
	 *
	 * @return La chaîne après remplacement.
	 *
	 */
	public static String remplacerEspaces(final String chaineSource) {
		final String underscore = "_";
		final String espace = " ";
		final String espaceInsec = " ";
		final String resultat = chaineSource.replaceAll(espace, underscore).replaceAll(espaceInsec, underscore);
		return resultat;
	}

	/**
	 * Méthode permettant de cloner un tableau d'objets
	 *
	 * @param tabACloner
	 *            Tableau à cloner.
	 * @return tableau cloné.
	 */
	public static <T> T[] clonerObjTableau(final T[] tabACloner) {
		if (tabACloner == null) {
			return null;
		} else {
			return tabACloner.clone();
		}
	}

	/**
	 * Méthode permettant de cloner un tableau de bytes
	 *
	 * @param tabACloner
	 *            Tableau à cloner.
	 * @return tableau cloné.
	 */
	public static byte[] clonerByteTableau(final byte[] tabACloner) {
		if (tabACloner == null) {
			return new byte[0];
		} else {
			return tabACloner.clone();
		}
	}

	/**
	 * Créer une URL HTTP pour les paramètres fournis.
	 *
	 * @param protocole
	 *            protocole de communication (http, https, etc...)
	 * @param serveur
	 *            nom d'hôte
	 * @param port
	 *            port
	 * @param appli
	 *            nom de l'application
	 * @param chemins
	 *            chemins de l'URL
	 * @return URL finie.
	 * @throws TechnicalException
	 */
	public static String creerHttpUrl(final String protocole, final String serveur, final Integer port,
			final String appli, final String... chemins) throws TechnicalException {
		try {
			if (null == serveur) {
				return null;
			}

			final StringBuilder url = new StringBuilder();

			if (StringUtils.isNotBlank(protocole)) {
				url.append(protocole);
				url.append(Constantes.SEPARATEUR_FIN_PROTOCOLE);
			} else {
				url.append(Constantes.HTTP_PROTOCOL);
			}

			url.append(serveur);

			if (null != port) {
				if (port < 0) {
					return null;
				} else {
					url.append(Constantes.COLON).append(port);
				}
			}

			ajouterHttpSeparateur(url, appli);

			for (final String chemin : chemins) {
				ajouterHttpSeparateur(url, chemin);
			}

			final URI testUrl = new URI(url.toString());
			return testUrl.toString();
		} catch (final URISyntaxException e) {
			throw new TechnicalException(e);
		}
	}

	/**
	 * Ajoute, si nécessaire, un séparateur entre l'URL fournie et le chemin.
	 *
	 * @param url
	 *            URL à compléter
	 * @param chemin
	 *            composante à ajouter
	 */
	public static void ajouterHttpSeparateur(final StringBuilder url, final String chemin) {
		if (null != url && null != chemin) {
			int etat = 0;
			if (url.lastIndexOf(SEPARATEUR_URL) == url.length() - 1) {
				etat = etat + 1;
			}
			if (chemin.startsWith(SEPARATEUR_URL)) {
				etat = etat + 2;
			}

			final StringBuilder finalArg = new StringBuilder();
			switch (etat) {
			case 0:
				finalArg.append(SEPARATEUR_URL).append(chemin);
				break;
			case SEPARATEUR_COMMUN:
				finalArg.append(chemin.substring(1));
				break;
			default:
				finalArg.append(chemin);
			}

			url.append(finalArg);
		}
	}

	/**
	 * Ajoute, si nécessaire, un séparateur entre l'URL fournie et le chemin. Ne
	 * valide pas les url suivante (http://localhost
	 *
	 * @param url
	 *            URL à compléter
	 * @param chemin
	 *            composante à ajouter
	 */
	public static boolean isValidFormatURL(final String url) {
		final String urlToValid = url.replace("http://localhost", "http://127.0.0.1");
		final String[] schemes = { "http", "https" };
		final UrlValidator urlValidator = new UrlValidator(schemes);
		return urlValidator.isValid(urlToValid);
	}

	/**
	 * <p>
	 * Génère une balise qui ouvre un élément X(HT)ML.
	 * </p>
	 *
	 * @param nomElement
	 *            Le nom de l'élément X(HT)ML. Par exemple, <code>ul</code>.
	 *
	 * @return Une balise ouvrante pour l'élément X(HT)ML. Par exemple,
	 *         <code>&lt;ul&gt;</code>.
	 *
	 */
	public static String ouvrirElement(final String nomElement) {
		return GenericUtils.concat(Constantes.OPEN_CHEVRON, nomElement, Constantes.CLOSE_CHEVRON);
	}

	/**
	 * <p>
	 * Génère la balise qui ferme un élément X(HT)ML.
	 * </p>
	 *
	 * @param nomElement
	 *            Le nom de l'élément X(HT)ML. Par exemple, <code>ol</code>.
	 *
	 * @return La balise fermante pour l'élément X(HT)ML. Par exemple,
	 *         <code>&lt;/ol&gt;</code>.
	 *
	 */
	public static String fermerElement(final String nomElement) {
		return GenericUtils.concat(Constantes.OPEN_CHEVRON, Constantes.SLASH, nomElement, Constantes.CLOSE_CHEVRON);
	}

	/**
	 * Round a float value to N decimals
	 *
	 * @param value
	 *            la valeur à convertir
	 * @param decimalNumber
	 *            nombre de décimales après la virgule
	 * @return Float the value rounded to decimalNumber decimal
	 */
	public static Float roundDecimal(final Float value, final int decimalNumber) {
		Float resultat = null;
		if (value != null) {
			final double factor = Math.pow(10, decimalNumber);
			resultat = Math.round(value * factor) / (float) factor;
		}
		return resultat;
	}

	public static Integer bigIntegerToInteger(final BigInteger valeur) {
		if (valeur != null) {
			return valeur.intValue();
		}
		return null;
	}

	public static String objectToString(final Object valeur) {
		if (valeur != null) {
			return valeur.toString();
		}
		return null;
	}

	public static Double bigDecimalToDouble(final BigDecimal valeur) {
		if (valeur != null) {
			return valeur.doubleValue();
		}
		return null;

	}

	/**
	 * Cette méthode permet de retourner un string sous ce format arg1|arg2|arg3
	 *
	 * @param arguments
	 *            arg1,ar2,arg3
	 * @return une chaine sous ce format arg1|arg2|arg3
	 */
	public static String joinEspace(final String... arguments) {
		final Joiner joiner = Joiner.on(Constantes.SPACE).skipNulls();
		return Strings.emptyToNull(joiner.join(arguments));
	}

	/**
	 * permet de splitter une chaine et le transforme en liste
	 *
	 * @param argument
	 *            chaine séparé par des virgule
	 * @return liste de token
	 */
	public static List<String> extractListOfTokensWithSeparateComma(final String argument) {
		if (argument != null) {
			return Splitter.on(Constantes.COMMA).trimResults().omitEmptyStrings().splitToList(argument);
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Méthode permettant de récupérer la cause suivante de l'exception passée
	 * en paramètre
	 *
	 * @param e
	 *            l'exception à traiter
	 * @return Throwable l'exception suivante
	 */
	private static Throwable retrieveNextException(final Throwable e) {
		final Throwable tmp = e.getCause();
		// On vérifie que le message retourné contient au moins 5 lettres pour
		// être "compréhensible"
		if (tmp != null && tmp.getMessage() != null && tmp.getMessage().length() > 5) {
			return tmp;
		}
		return null;
	}

	/**
	 * Méthode permettant de remonter la première exception connue de
	 * l'exception passée en paramètre
	 *
	 * @param e
	 *            l'exception à traiter
	 * @return Throwable la première exception
	 */
	public static Throwable retrieveFirstException(Throwable e) {
		Throwable tmp = null;
		while (e != null) {
			tmp = e;
			e = retrieveNextException(e);
		}
		return tmp;
	}

	/**
	 * Cette méthode vérifie si le format d'un email est valide
	 *
	 * @param email
	 * @return true : si le format est valide, false : si non
	 */
	public static boolean formatEmailValide(final String email) {
		final EmailValidator emailValidator = EmailValidator.getInstance();
		return emailValidator.isValid(email);
	}

	/**
	 * Cette méthode fait une mise en forme des CronExpressions paramètre. Elle
	 * supprime tous les blancs. Les blancs peuvent être : {' ', * '\t', '\r',
	 * '\n', '\b'}
	 *
	 * @param chaine
	 *            : chaine à transformer.
	 * @return chaine transforfée
	 */
	public static String miseEnFormeCronExpression(String chaine) {
		chaine = replaceAllSpecialBlancCharByBlanc(chaine);

		// suppression de tous les espaces au debut
		while (chaine.indexOf(" ") == 0) {
			chaine = chaine.substring(1);
		}
		// suppression de tous les espaces a la fin
		while (chaine.lastIndexOf(" ") == chaine.length() - 1) {
			chaine = chaine.substring(0, chaine.length() - 1);
		}
		// suppression de tous les doubles espaces au milieu
		while (chaine.contains("  ")) {
			chaine = chaine.replaceAll("  ", " ");
		}
		return chaine;
	}

	/**
	 * cette méthode supprime tous les caractères blanc (espaces, tabulations,
	 * saut de ligne etc...) dans la chaine passée en paramètre. Les blancs
	 * peuvent être : {' ', '\t', '\r', '\n', '\b'}
	 *
	 * @param chaine
	 *            : chaine à transformer.
	 * @return chaine transforfée
	 */
	public static String supprimerBlancs(String chaine) {
		chaine = replaceAllSpecialBlancCharByBlanc(chaine);

		// suppression de tous les espaces dans la chaine
		while (chaine.contains(" ")) {
			chaine = chaine.replaceAll(" ", "");
		}
		return chaine;
	}

	/**
	 * cette méthode supprime tous les caractères blanc (espaces, tabulations,
	 * saut de ligne etc...) au début et a la fin de la chaine passée en
	 * paramètre. Les blancs peuvent être : {' ', '\t', '\r', '\n', '\b'}
	 *
	 * @param chaine
	 *            : chaine à transformer.
	 * @return chaine transforfée
	 */
	public static String supprimerBlancsDebutEtFin(String chaine) {
		chaine = replaceAllSpecialBlancCharByBlanc(chaine);

		// suppression de tous les espaces au debut
		while (chaine.indexOf(" ") == 0) {
			chaine = chaine.substring(1);
		}
		// suppression de tous les espaces a la fin
		while (chaine.lastIndexOf(" ") == chaine.length() - 1) {
			chaine = chaine.substring(0, chaine.length() - 1);
		}
		return chaine;
	}

	/**
	 * Cette méthode
	 */
	private static String replaceAllSpecialBlancCharByBlanc(String chaine) {
		chaine = chaine.replace('\t', ' ');
		chaine = chaine.replace('\r', ' ');
		chaine = chaine.replace('\n', ' ');
		chaine = chaine.replace('\b', ' ');
		return chaine;
	}
}
