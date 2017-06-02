package com.sirh.mqd.commons.storage.constantes;

/**
 * Liste des constantes utilisées dans la persistence
 *
 * @author alexandre
 */
public final class DossierConstantes {

	public static final String COLLECTION_NAME = "dossiers";

	public static final String COLONNE_ID = "_id";

	public static final String COLONNE_MINISTERE = "ministere";

	public static final String COLONNE_PAY_CLE = "pay_cle";

	public static final String COLONNE_PAY_LOT = "pay_lot";

	public static final String COLONNE_DOSSIER_NUMERO = "dossier_numero";

	public static final String COLONNE_DI_GESTIONNAIRE = "di_gestionnaire";

	public static final String COLONNE_ADMIN_CODE = "admin_code";

	public static final String COLONNE_ADMIN_CODE_DEPARTEMENT = "admin_code_departement";

	public static final String COLONNE_MATRICULE = "matricule";

	public static final String COLONNE_CORPS_CODE = "corps_code";

	public static final String COLONNE_CORPS_LIBELLE_COURT = "corps_libelle_court";

	public static final String COLONNE_GRADE_CODE = "grade_code";

	public static final String COLONNE_GRADE_LIBELLE_COURT = "grade_libelle_court";

	public static final String COLONNE_AFFECTATION_CODE = "affectation_code";

	public static final String COLONNE_AFFECTATION_LIBELLE_COURT = "affectation_libelle_court";

	public static final String COLONNE_CIVILITE = "civilite";

	public static final String COLONNE_NOM = "nom";

	public static final String COLONNE_PRENOM = "prenom";

	public static final String COLONNE_SEXE = "sexe";

	public static final String COLONNE_DATE_NAISSANCE = "date_naissance";

	public static final String COLONNE_PAY_DATE_CERTIFICATION = "pay_date_certification";

	public static final String COLONNE_DATES_MOUVEMENTS_CARRIERE = "dates_mouvements_carriere";

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private DossierConstantes() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + DossierConstantes.class.getName());
	}
}
