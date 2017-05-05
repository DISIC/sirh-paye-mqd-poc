package com.sirh.mqd.commons.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.fest.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.sirh.mqd.commons.MonObjet;
import com.sirh.mqd.commons.MonSousObjet;
import com.sirh.mqd.commons.utils.GenericUtils;
import com.sirh.mqd.commons.utils.JsonUtils;

public class JsonUtilsTest {

	/** Tests de : {@link SGAUtils#deserializerJSON(String, Class)} */

	/**
	 * Test de la méthode {@link GenericUtils#deserializerJSON(String, Class)}.
	 * <br/>
	 * Cas nominal.
	 *
	 * @throws IOException
	 */
	@Test
	public void testDeserializerJSON00CasNominal() throws IOException {
		final StringBuilder maChaineEnEntree = new StringBuilder();
		maChaineEnEntree.append("{");

		maChaineEnEntree.append("\"monBoolean\":true");
		maChaineEnEntree.append(",");

		maChaineEnEntree.append("\"monEntier\":1234");
		maChaineEnEntree.append(",");

		maChaineEnEntree.append("\"maChaine\":\"ma SUPER chaine de caracteres\"");
		maChaineEnEntree.append(",");

		maChaineEnEntree.append("\"maListe\":");
		maChaineEnEntree.append("[");

		maChaineEnEntree.append("{");
		maChaineEnEntree.append("\"monNom\":\"premier nom\"");
		maChaineEnEntree.append(",");
		maChaineEnEntree.append("\"maDecimale\":998.01");
		maChaineEnEntree.append("}");

		maChaineEnEntree.append(",");

		maChaineEnEntree.append("{");
		maChaineEnEntree.append("\"monNom\":\"deuxième nom\"");
		maChaineEnEntree.append(",");
		maChaineEnEntree.append("\"maDecimale\":1.00078");
		maChaineEnEntree.append("}");

		maChaineEnEntree.append("]");

		maChaineEnEntree.append("}");

		final MonObjet objetAttendu = new MonObjet();
		objetAttendu.setMonBoolean(true);
		objetAttendu.setMonEntier(1234);
		objetAttendu.setMaChaine("ma SUPER chaine de caracteres");

		final MonSousObjet sousObjetAttendu1 = new MonSousObjet();
		sousObjetAttendu1.setMonNom("premier nom");
		sousObjetAttendu1.setMaDecimale(new BigDecimal("998.01"));

		final MonSousObjet sousObjetAttendu2 = new MonSousObjet();
		sousObjetAttendu2.setMonNom("deuxième nom");
		sousObjetAttendu2.setMaDecimale(new BigDecimal("1.00078"));

		objetAttendu.setMaListe(Arrays.asList(sousObjetAttendu1, sousObjetAttendu2));

		final MonObjet objetResultat = JsonUtils.deserializerJSON(maChaineEnEntree.toString(), MonObjet.class);

		/** Vérification que l'objet résultat est correct. */
		Assert.assertEquals("La chaine désérialisée ne donne pas le bon objet", objetAttendu, objetResultat);
	}

	/**
	 * Test de la méthode {@link GenericUtils#deserializerJSON(String, Class)} .
	 * <br/>
	 * Cas où la chaine en entrée n'est pas au bon format. Le résultat est alors
	 * <code>null</code>.<br/>
	 * Permet de tester le catch de l'exception {@link JsonParseException}.
	 *
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void testDeserializerJSON01ProblemeSurObjet() throws IOException {
		final StringBuilder maChaineEnEntree = new StringBuilder();
		maChaineEnEntree.append("{");

		// La valeur de la chaine n'est pas fermée par des guillemets
		maChaineEnEntree.append("\"maChaine\":\"super test");

		maChaineEnEntree.append("}");

		JsonUtils.deserializerJSON(maChaineEnEntree.toString(), MonObjet.class);
	}

	/**
	 * Test de la méthode {@link GenericUtils#deserializerJSON(String, Class)}.
	 * <br/>
	 * Cas où la chaine en entrée ne correspond pas au type de l'objet attendu.
	 * Le résultat est alors <code>null</code>.<br/>
	 * Permet de tester le catch de l'exception {@link JsonMappingException}.
	 *
	 * @throws IOException
	 */
	@Test(expected = IOException.class)
	public void testDeserializerJSON02TypeIncompatible() throws IOException {
		final StringBuilder maChaineEnEntree = new StringBuilder();
		maChaineEnEntree.append("{");

		// L'attibut "monChampIncorrect" n'existe pas dans la classe
		// "monChampIncorrect"
		maChaineEnEntree.append("\"monChampIncorrect\":true");
		maChaineEnEntree.append(",");

		maChaineEnEntree.append("\"monBoolean\":true");

		maChaineEnEntree.append("}");

		JsonUtils.deserializerJSON(maChaineEnEntree.toString(), MonObjet.class);
	}

	@Test
	public void testDeserializerFichierJSON00CasNominal() throws IOException {
		final MonObjet objetResultat = JsonUtils.deserializerJSON(new File("./src/test/resources/objet.json"),
				MonObjet.class);

		Assertions.assertThat(objetResultat).isNotNull();
	}

	@Test(expected = IOException.class)
	public void testDeserializerFichierJSON02TypeIncompatible() throws IOException {
		JsonUtils.deserializerJSON(new File("./src/test/resources/objet.json"), String.class);

	}

	@Test(expected = IOException.class)
	public void testDeserializerFichierJSON01FichierInvalide() throws IOException {
		JsonUtils.deserializerJSON(new File("./src/test/resources/objetInvalide.json"), String.class);

	}

	@Test(expected = IOException.class)
	public void testDeserializerFichierJSON03FichierNonExistant() throws IOException {
		JsonUtils.deserializerJSON(new File("./src/test/resources/blabla.json"), String.class);
	}

	/******************************************************************************************/
	/** Tests de : {@link SGAUtils#serializerJSON(Object)} */

	/**
	 * Test de la méthode {@link GenericUtils#serializerJSON(Object)}.<br/>
	 * Cas nominal.
	 *
	 * @throws IOException
	 */
	@Test
	public void testSerializerJSON00CasNominal() throws IOException {
		final MonSousObjet sousObjetEnEntree1 = new MonSousObjet();
		sousObjetEnEntree1.setMonNom("premier nom");
		sousObjetEnEntree1.setMaDecimale(new BigDecimal("998.01"));

		final MonSousObjet sousObjetEnEntree2 = new MonSousObjet();
		sousObjetEnEntree2.setMonNom("deuxième nom");
		sousObjetEnEntree2.setMaDecimale(new BigDecimal("1.00078"));

		final MonObjet objetEnEntree = new MonObjet();
		objetEnEntree.setMonBoolean(true);
		objetEnEntree.setMonEntier(1234);
		objetEnEntree.setMaChaine("ma SUPER chaine de caracteres");
		objetEnEntree.setMaListe(Arrays.asList(sousObjetEnEntree1, sousObjetEnEntree2));

		final StringBuilder maChaineAttendue = new StringBuilder();
		maChaineAttendue.append("{");

		maChaineAttendue.append("\"monBoolean\":true");
		maChaineAttendue.append(",");

		maChaineAttendue.append("\"monEntier\":1234");
		maChaineAttendue.append(",");

		maChaineAttendue.append("\"maChaine\":\"ma SUPER chaine de caracteres\"");
		maChaineAttendue.append(",");

		maChaineAttendue.append("\"maListe\":");
		maChaineAttendue.append("[");

		maChaineAttendue.append("{");
		maChaineAttendue.append("\"monNom\":\"premier nom\"");
		maChaineAttendue.append(",");
		maChaineAttendue.append("\"maDecimale\":998.01");
		maChaineAttendue.append("}");

		maChaineAttendue.append(",");

		maChaineAttendue.append("{");
		maChaineAttendue.append("\"monNom\":\"deuxième nom\"");
		maChaineAttendue.append(",");
		maChaineAttendue.append("\"maDecimale\":1.00078");
		maChaineAttendue.append("}");

		maChaineAttendue.append("]");

		maChaineAttendue.append("}");

		final String chaineResultat = JsonUtils.serializerJSON(objetEnEntree);

		/** Vérification que l'objet résultat est correct. */
		Assertions.assertThat(chaineResultat).isEqualTo(maChaineAttendue.toString());
	}

	@Test
	public void testSerializerJSON00CasNULL() throws IOException {

		MonObjet objetEnEntree = null;// new MonObjet();

		String chaineResultat = JsonUtils.serializerJSON(objetEnEntree);

		/** Vérification que l'objet résultat est correct. */
		Assertions.assertThat(chaineResultat).isNull();

		objetEnEntree = new MonObjet();
		objetEnEntree.setMonBoolean(true);
		objetEnEntree.setMonEntier(1234);

		chaineResultat = JsonUtils.serializerJSON(objetEnEntree);
		Assertions.assertThat(chaineResultat)
				.isEqualTo("{\"monBoolean\":true,\"monEntier\":1234,\"maChaine\":null,\"maListe\":null}");

	}

}
