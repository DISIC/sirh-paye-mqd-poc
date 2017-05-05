package com.thalesgroup.stif.commons.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;

import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.sirh.mqd.commons.utils.GenericUtils;
import com.sirh.mqd.commons.utils.com;

public class STIFUtilsUnitTest {

	@Test(expected = InstantiationException.class)
	public void testConstructeur() throws InstantiationException {
		new GenericUtils();
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#concat(String...)} */

	/**
	 * Test de la méthode {@link GenericUtils#concat(String...)}. <br/>
	 * Cas nominal avec trois chaînes en entrée.
	 */
	@Test
	public void testConcat00CasNominal() {
		final String chaine1 = "aze";
		final String chaine2 = "rty";
		final String chaine3 = "uio";

		final String chaineAttendue = "azertyuio";
		final String chaineResultat = GenericUtils.concat(chaine1, chaine2, chaine3);

		Assertions.assertThat(chaineResultat).isEqualTo(chaineAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#concat(String...)}.<br/>
	 * Cas au limite avec aucune entrée.
	 */
	@Test
	public void testConcat01AucuneEntree() {
		final String chaineConstruite = GenericUtils.concat();

		Assertions.assertThat(chaineConstruite).isEmpty();
	}

	/**
	 * <p>
	 * Test de la méthode {@link GenericUtils#remplacerEspaces(String)}.
	 * </p>
	 *
	 * <p>
	 * Cas avec un cocktail d'espaces, d'espaces insécables, et d'accents.
	 * </p>
	 *
	 */
	@Test
	public void testRemplacerEspaces00Cocktail() {
		final String entree = "Nous étions le 21 décembre 2012.";
		final String resultatObtenu = GenericUtils.remplacerEspaces(entree);
		final String resultatVoulu = "Nous_étions_le_21_décembre_2012.";

		Assert.assertEquals("La chaîne obtenue diffère de la chaîne voulue.", resultatObtenu, resultatVoulu);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#suffixe(String)} */

	/**
	 * <p>
	 * Test de la méthode {@link GenericUtils#suffixe(String)}.
	 * </p>
	 *
	 * <p>
	 * Cas avec <em>MonArchive.tar.gz</em>.
	 * </p>
	 *
	 */
	@Test
	public void testSuffixe00ArchiveTarGz() {
		final String entree = "MonArchive.tar.gz";
		final String resultatObtenu = GenericUtils.suffixe(entree);
		final String resultatVoulu = "gz";

		Assert.assertEquals("La chaîne obtenue diffère de la chaîne voulue.", resultatObtenu, resultatVoulu);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#ajouterHttpSeparateur(StringBuilder, String)} */

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où les paramètres sont null.<br/>
	 * L'url passée doit rester null.
	 */
	@Test
	public void testAjouterHttpSeparateur00ParamsNull() throws Exception {
		final StringBuilder sbEnEntree = null;

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, null);

		Assertions.assertThat(sbEnEntree).isNull();
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est null et le chemin non null.<br/>
	 * L'url passée doit rester null.
	 */
	@Test
	public void testAjouterHttpSeparateur01UrlNull() throws Exception {
		final StringBuilder sbEnEntree = null;

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, "chemin");

		Assertions.assertThat(sbEnEntree).isNull();
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est non null et le chemin null.<br/>
	 * L'url passée ne doit pas être modifiée.
	 */
	@Test
	public void testAjouterHttpSeparateur02CheminNull() throws Exception {
		final String url = "http://serveur:port/";
		final StringBuilder sbEnEntree = new StringBuilder(url);

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, null);

		Assertions.assertThat(sbEnEntree.toString()).isEqualTo(url);
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est non null et le chemin non null. L'URL n'a pas de séparateur à la fin et le chemin n'a pas de séparateur au début.<br/>
	 * L'URL finale doit être la concaténation de l'URL initiale + séparateur + chemin.
	 */
	@Test
	public void testAjouterHttpSeparateur03AucunSeparateur() throws Exception {
		final String url = "http://serveur:port";
		final String cheminEnEntree = "monChemin";

		final StringBuilder sbEnEntree = new StringBuilder(url);

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, cheminEnEntree);

		final String urlAttendue = "http://serveur:port/monChemin";

		Assertions.assertThat(sbEnEntree.toString()).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est non null et le chemin non null. L'URL a un séparateur à la fin et le chemin n'a pas de séparateur au début.<br/>
	 * L'URL finale doit être la concaténation de l'URL initiale + chemin.
	 */
	@Test
	public void testAjouterHttpSeparateur04CheminSansSeparateur() throws Exception {
		final String url = "http://serveur:port/";
		final String cheminEnEntree = "monChemin";

		final StringBuilder sbEnEntree = new StringBuilder(url);

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, cheminEnEntree);

		final String urlAttendue = "http://serveur:port/monChemin";

		Assertions.assertThat(sbEnEntree.toString()).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est non null et le chemin non null. L'URL n'a pas de séparateur à la fin et le chemin a un séparateur au début.<br/>
	 * L'URL finale doit être la concaténation de l'URL initiale + chemin.
	 */
	@Test
	public void testAjouterHttpSeparateur05UrlSansSeparateur() throws Exception {
		final String url = "http://serveur:port/";
		final String cheminEnEntree = "monChemin";

		final StringBuilder sbEnEntree = new StringBuilder(url);

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, cheminEnEntree);

		final String urlAttendue = "http://serveur:port/monChemin";

		Assertions.assertThat(sbEnEntree.toString()).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#ajouterHttpSeparateur(StringBuilder, String)}.<br/>
	 * Cas où l'URL est non null et le chemin non null. L'URL n'a pas de séparateur à la fin et le chemin a un séparateur au début.<br/>
	 * L'URL finale doit être la concaténation de l'URL initiale + chemin (avec un seul séparateur entre les deux composantes).
	 */
	@Test
	public void testAjouterHttpSeparateur06UrlEtCheminAvecSeparateur() throws Exception {
		final String url = "http://serveur:port/";
		final String cheminEnEntree = "/monChemin";

		final StringBuilder sbEnEntree = new StringBuilder(url);

		GenericUtils.ajouterHttpSeparateur(sbEnEntree, cheminEnEntree);

		final String urlAttendue = "http://serveur:port/monChemin";

		Assertions.assertThat(sbEnEntree.toString()).isEqualTo(urlAttendue);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#creerHttpUrl(String, Integer, String, String...)} */

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est null, peu importe la valeur des autres paramètres.<br/>
	 * Le résultat doit être null.
	 */
	@Test
	public void testCreerHttpUrl00ServeurNull() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, null, 1234, "appli", "chemins");

		Assertions.assertThat(urlResultat).isNull();
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est non null et tous les autres paramètres null.<br/>
	 * Le résultat doit être une URL avec le protocole et le serveur.
	 */
	@Test
	public void testCreerHttpUrl01ServeurNonNull() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl("https", "monServeur", null, null);

		final String urlAttendue = "https://monServeur";

		Assertions.assertThat(urlResultat).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est non null et le port négatif.<br/>
	 * Le résultat doit être null.
	 */
	@Test
	public void testCreerHttpUrl02ServeurNonNullPortNegatif() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, "monServeur", -1234, null);

		final String urlAttendue = null;

		Assertions.assertThat(urlResultat).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est non null et le port positif.<br/>
	 * Le résultat doit être une URL avec le protocole, le serveur et le port.
	 */
	@Test
	public void testCreerHttpUrl03ServeurNonNullPortPositif() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, "monServeur", 1234, null);

		final String urlAttendue = "http://monServeur:1234";

		Assertions.assertThat(urlResultat).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est non null, le port positif et l'application non null.<br/>
	 * Le résultat doit être une URL avec le protocole, le serveur, le port et l'application.
	 */
	@Test
	public void testCreerHttpUrl04ServeurNonNullPortPositifAppliNonNull() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, "monServeur", 1234, "monAppli");

		final String urlAttendue = "http://monServeur:1234/monAppli";

		Assertions.assertThat(urlResultat).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où le serveur est non null, le port positif, l'application non null et plusieurs chemins avec ou sans séparateurs.<br/>
	 * Le résultat doit être une URL avec le protocole, le serveur, le port, l'application les chemins.
	 */
	@Test
	public void testCreerHttpUrl05ServeurNonNullPortPositifAppliNonNullPlusieursChemins() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, "monServeur", 1234, "monAppli", "chemin0", "chemin1/", "/chemin2",
				"/chemin3");

		final String urlAttendue = "http://monServeur:1234/monAppli/chemin0/chemin1/chemin2/chemin3";

		Assertions.assertThat(urlResultat).isEqualTo(urlAttendue);
	}

	/**
	 * Test de la méthode {@link GenericUtils#creerHttpUrl(String, Integer, String, String...)}.<br/>
	 * Cas où l'URL finale est invalide (un espace dans le nom du serveur).<br/>
	 * Le résultat doit être null.
	 */
	@Test(expected = URISyntaxException.class)
	public void testCreerHttpUrl06UrlInvalide() throws Exception {
		final String urlResultat = GenericUtils.creerHttpUrl(null, "mon Serveur", 1234, "mon Appli", "chemin0", "chemin1/", "/chemin2",
				"/chemin3");

		Assertions.assertThat(urlResultat).isNull();
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#ouvrirElement(String)} */

	/**
	 * <p>
	 * Test de la méthode {@link GenericUtils#ouvrirElement(String)}.
	 * </p>
	 *
	 * <p>
	 * Cas nominal.
	 * </p>
	 *
	 */
	@Test
	public void testOuvrirElement00CasNominal() {
		final String entree = "ul";
		final String resultatObtenu = GenericUtils.ouvrirElement(entree);
		final String resultatVoulu = "<ul>";

		Assert.assertEquals("La chaîne obtenue diffère de la chaîne voulue.", resultatObtenu, resultatVoulu);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#fermerElement(String)} */

	/**
	 * <p>
	 * Test de la méthode {@link GenericUtils#fermerElement(String)}.
	 * </p>
	 *
	 * <p>
	 * Cas nominal.
	 * </p>
	 *
	 */
	@Test
	public void testFermerElement00CasNominal() {
		final String entree = "ol";
		final String resultatObtenu = GenericUtils.fermerElement(entree);
		final String resultatVoulu = "</ol>";

		Assert.assertEquals("La chaîne obtenue diffère de la chaîne voulue.", resultatObtenu, resultatVoulu);
	}

	@Test
	public void clonerObjTableau00CasNominal() {
		final String[] tabOri = { "test", "test2" };

		final String[] tabNew = GenericUtils.clonerObjTableau(tabOri);

		Assertions.assertThat(tabNew).isEqualTo(tabOri);
		Assertions.assertThat(tabOri == tabNew).isFalse();
	}

	@Test
	public void clonerObjTableau00CasNull() {
		final String[] tabOri = null;

		final String[] tabNew = GenericUtils.clonerObjTableau(tabOri);

		Assertions.assertThat(tabNew).isNull();
	}

	@Test
	public void clonerByteTableau00CasNominal() {
		final byte[] tabOri = { 0, 1, 0, 0 };

		final byte[] tabNew = GenericUtils.clonerByteTableau(tabOri);

		Assertions.assertThat(tabNew).isEqualTo(tabOri);
		Assertions.assertThat(tabOri == tabNew).isFalse();
	}

	@Test
	public void clonerByteTableau00CasNull() {
		final byte[] tabOri = null;

		final byte[] tabNew = GenericUtils.clonerByteTableau(tabOri);

		Assertions.assertThat(tabNew).isNotNull();
		Assertions.assertThat(tabNew.length).isEqualTo(0);
	}

	/**
	 * Test method for
	 * <ul>
	 * <li>{@link com.GenericUtils.alerting.util.STIFUtils#roundDecimal(Float, int)}</li>
	 * </ul>
	 * Cas nominal pour arrondir à 2 décimales un float avec plusieurs décimales après la virgule.
	 */
	@Test
	public void testRoundDecimalCasNominal00() {
		final Float value = 3.5245451521210f;
		final int decimalNumber = 2;

		Float result = GenericUtils.roundDecimal(value, decimalNumber);

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isNotEqualTo(value);
		Assertions.assertThat(result).isEqualTo(3.52f);
	}

	/**
	 * Test method for
	 * <ul>
	 * <li>{@link com.GenericUtils.alerting.util.STIFUtils#roundDecimal(Float, int)}</li>
	 * </ul>
	 * Cas nominal pour arrondir à 5 décimales un float avec plusieurs décimales après la virgule.
	 */
	@Test
	public void testRoundDecimalCasNominal01() {
		final Float value = 3.5245451521210f;
		final int decimalNumber = 5;

		Float result = GenericUtils.roundDecimal(value, decimalNumber);

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isNotEqualTo(value);
		Assertions.assertThat(result).isEqualTo(3.52455f);
	}

	/**
	 * Test method for
	 * <ul>
	 * <li>{@link com.GenericUtils.alerting.util.STIFUtils#roundDecimal(Float, int)}</li>
	 * </ul>
	 * Cas nominal pour arrondir à 0 décimale un float avec plusieurs décimales après la virgule.
	 */
	@Test
	public void testRoundDecimalCasNominal02() {
		final Float value = 3.5245451521210f;
		final int decimalNumber = 0;

		Float result = GenericUtils.roundDecimal(value, decimalNumber);

		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isNotEqualTo(value);
		Assertions.assertThat(result).isEqualTo(4f);
	}

	/**
	 * Test method for
	 * <ul>
	 * <li>{@link com.GenericUtils.alerting.util.STIFUtils#roundDecimal(Float, int)}</li>
	 * </ul>
	 * Cas où la valeur passée en entrée est null.
	 */
	@Test
	public void testRoundDecimalCasNullValue() {
		final Float value = null;
		final int decimalNumber = 5;

		Float result = GenericUtils.roundDecimal(value, decimalNumber);

		Assertions.assertThat(result).isNull();
	}

	/**
	 * test BigInteger to Integer
	 *
	 * @throws Exception
	 */
	@Test
	public void testBigIntegerToInteger() throws Exception {
		Assertions.assertThat(GenericUtils.bigIntegerToInteger(null)).isNull();
		Assertions.assertThat(GenericUtils.bigIntegerToInteger(new BigInteger("45"))).isEqualTo(45);
	}

	@Test
	public void testObjectToString() throws Exception {
		Assertions.assertThat(GenericUtils.objectToString(null)).isNull();
		Assertions.assertThat(GenericUtils.objectToString(new String("hello"))).isEqualTo("hello");
	}

	@Test
	public void testBigDecimalToDouble() throws Exception {
		Assertions.assertThat(GenericUtils.bigDecimalToDouble(null)).isNull();
		Assertions.assertThat(GenericUtils.bigDecimalToDouble(new BigDecimal("45.5"))).isEqualTo(45.5);
	}

	@Test
	public void testJoin() throws Exception {
		Assertions.assertThat(GenericUtils.join()).isEqualTo(null);
	}

	@Test(expected = NullPointerException.class)
	public void testJoinArgumentsNull() throws Exception {
		GenericUtils.join("arg1", null);
	}

	@Test
	public void testJoinNominal() throws Exception {
		Assertions.assertThat(GenericUtils.join("arg1", "arg2", "arg3")).isEqualTo("arg1|arg2|arg3");
	}

	@Test
	public void testJoinVide() throws Exception {
		Assertions.assertThat(GenericUtils.join("arg1", "", "arg3")).isEqualTo("arg1||arg3");
	}

	@Test
	public void testJoinEspace() throws Exception {
		Assertions.assertThat(GenericUtils.joinEspace("arg1", "arg2", "arg3")).isEqualTo("arg1 arg2 arg3");
		Assertions.assertThat(GenericUtils.joinEspace("arg1", "", "arg3")).isEqualTo("arg1  arg3");
		Assertions.assertThat(GenericUtils.joinEspace("arg1", null, "arg3")).isEqualTo("arg1 arg3");
		Assertions.assertThat(GenericUtils.joinEspace(null, null)).isEqualTo(null);
	}

	@Test
	public void testExtractListOfTokensWithSeparateComma() throws Exception {
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma(null)).isEmpty();
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma("")).isEmpty();
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma(",")).isEmpty();
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma("operateur1")).contains("operateur1");
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma("operateur1,operateur2")).contains("operateur1", "operateur2");
		Assertions.assertThat(GenericUtils.extractListOfTokensWithSeparateComma("operateur1,operateur2,"))
				.contains("operateur1", "operateur2");
	}

	@Test
	public void testUrlValidator() throws Exception {

		Assertions.assertThat(GenericUtils.isValidFormatURL("http://127.0.0.1:8689/test145_1Diffuseur")).isTrue();
		Assertions.assertThat(GenericUtils.isValidFormatURL("http://free.fr")).isTrue();
		Assertions.assertThat(GenericUtils.isValidFormatURL("http://localhost")).isTrue();

	}
}
