package com.sirh.mqd.supplier.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.exchanges.factory.pivot.AnomalieDTOFactory;
import com.sirh.mqd.commons.utils.DateUtils;

public class AnomalieDetectionMSOUtilsTest {

	private static final String PAY_LOT = "000A-ANT-SD-000";

	private static final String MATRICULE = "MSO000000000";

	private static final AnomalieTypeEnum UNSUPPORTED_TYPE_ANOMALIE = AnomalieTypeEnum.COORDONNEE_BANCAIRE_BIC_SWIFT;

	private static final String UNSUPPORTED_TYPE_PAY_VALUE = "10";

	private static final String UNSUPPORTED_TYPE_GA_VALUE = "CM300 | 2";

	private ComparaisonDTO comparaisonUnsupportedTypeNouvelle;

	private ComparaisonDTO comparaisonUnsupportedTypeExistanteSansAnomalie;

	private static final AnomalieTypeEnum SENSITIVE_TYPE_ANOMALIE = AnomalieTypeEnum.ADRESSE_CODE_PAYS;

	private static final String SENSITIVE_TYPE_PAY_VALUE = "NATIONALE";

	private static final String SENSITIVE_TYPE_GA_WRONG_VALUE = "Nationale";

	private static final String SENSITIVE_TYPE_GA_VALID_VALUE = "NATIONALE";

	private ComparaisonDTO comparaisonSensitiveTypeNouvelle;

	private ComparaisonDTO comparaisonSensitiveTypeExistanteSansAnomalie;

	private ComparaisonDTO comparaisonSensitiveTypeExistanteAvecAnomalieOuverte;

	private ComparaisonDTO comparaisonSensitiveTypeExistanteAvecAnomalieFermee;

	private static final AnomalieTypeEnum SENSITIVE_BETWEEN_BRACKET_TYPE_ANOMALIE = AnomalieTypeEnum.CARRIERE_GRADE;

	private static final String SENSITIVE_BETWEEN_BRACKET_TYPE_PAY_VALUE = "0500010000";

	private static final String SENSITIVE_BETWEEN_BRACKET_TYPE_GA_WRONG_VALUE = "G2955 (0500580000)";

	private static final String SENSITIVE_BETWEEN_BRACKET_TYPE_GA_VALID_VALUE = "G2955 (0500010000)";

	private ComparaisonDTO comparaisonSensitiveWithBracketsTypeNouvelle;

	private ComparaisonDTO comparaisonSensitiveWithBracketsTypeExistanteSansAnomalie;

	private ComparaisonDTO comparaisonSensitiveWithBracketsTypeExistanteAvecAnomalieOuverte;

	private ComparaisonDTO comparaisonSensitiveWithBracketsTypeExistanteAvecAnomalieFermee;

	private static final AnomalieTypeEnum INSENSITIVE_TRUNC_20_TYPE_ANOMALIE = AnomalieTypeEnum.ETAT_CIVIL_NOM;

	private static final String INSENSITIVE_TRUNC_20_TYPE_PAY_VALUE = "SAM-MI-LAI RAHARIFIM";

	private static final String INSENSITIVE_TRUNC_20_TYPE_GA_WRONG_VALUE = "SAM-MI-SOL RAHARIFIDIMALALA";

	private static final String INSENSITIVE_TRUNC_20_TYPE_GA_VALID_VALUE = "SAM-MI-LAI RAHARIFIDIMALALA";

	private ComparaisonDTO comparaisonInsensitive20CharTypeNouvelle;

	private ComparaisonDTO comparaisonInsensitive20CharTypeExistanteSansAnomalie;

	private ComparaisonDTO comparaisonInsensitive20CharTypeExistanteAvecAnomalieOuverte;

	private ComparaisonDTO comparaisonInsensitive20CharTypeExistanteAvecAnomalieFermee;

	private static final AnomalieTypeEnum INSENSITIVE_TRUNC_15_TYPE_ANOMALIE = AnomalieTypeEnum.ETAT_CIVIL_PRENOM;

	private static final String INSENSITIVE_TRUNC_15_TYPE_PAY_VALUE = "MARIE FREDERIQU";

	private static final String INSENSITIVE_TRUNC_15_TYPE_GA_WRONG_VALUE = "Maria-Frédérique";

	private static final String INSENSITIVE_TRUNC_15_TYPE_GA_VALID_VALUE = "Marie-Frédérique";

	private ComparaisonDTO comparaisonInsensitive15CharTypeNouvelle;

	private ComparaisonDTO comparaisonInsensitive15CharTypeExistanteSansAnomalie;

	private ComparaisonDTO comparaisonInsensitive15CharTypeExistanteAvecAnomalieOuverte;

	private ComparaisonDTO comparaisonInsensitive15CharTypeExistanteAvecAnomalieFermee;

	private List<ComparaisonDTO> comparaisons;

	@Before
	public void setUp() {
		this.comparaisons = new ArrayList<ComparaisonDTO>();

		this.comparaisonUnsupportedTypeNouvelle = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				UNSUPPORTED_TYPE_ANOMALIE, InteractionSirhEnum.PAY, UNSUPPORTED_TYPE_PAY_VALUE);
		this.comparaisonUnsupportedTypeExistanteSansAnomalie = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT,
				MATRICULE, UNSUPPORTED_TYPE_ANOMALIE, InteractionSirhEnum.PAY, UNSUPPORTED_TYPE_PAY_VALUE);

		this.comparaisonSensitiveTypeNouvelle = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				SENSITIVE_TYPE_ANOMALIE, InteractionSirhEnum.PAY, SENSITIVE_TYPE_PAY_VALUE);
		this.comparaisonSensitiveTypeExistanteSansAnomalie = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				SENSITIVE_TYPE_ANOMALIE, InteractionSirhEnum.PAY, SENSITIVE_TYPE_PAY_VALUE);
		this.comparaisonSensitiveTypeExistanteAvecAnomalieOuverte = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT,
				MATRICULE, SENSITIVE_TYPE_ANOMALIE, InteractionSirhEnum.PAY, SENSITIVE_TYPE_PAY_VALUE);
		this.comparaisonSensitiveTypeExistanteAvecAnomalieFermee = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT,
				MATRICULE, SENSITIVE_TYPE_ANOMALIE, InteractionSirhEnum.PAY, SENSITIVE_TYPE_PAY_VALUE);
		this.comparaisonSensitiveTypeExistanteAvecAnomalieFermee
				.setDateCloture(DateUtils.getCalendarInstance().getTime());

		this.comparaisonSensitiveWithBracketsTypeNouvelle = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				SENSITIVE_BETWEEN_BRACKET_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				SENSITIVE_BETWEEN_BRACKET_TYPE_PAY_VALUE);
		this.comparaisonSensitiveWithBracketsTypeExistanteSansAnomalie = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, SENSITIVE_BETWEEN_BRACKET_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				SENSITIVE_BETWEEN_BRACKET_TYPE_PAY_VALUE);
		this.comparaisonSensitiveWithBracketsTypeExistanteAvecAnomalieOuverte = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, SENSITIVE_BETWEEN_BRACKET_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				SENSITIVE_BETWEEN_BRACKET_TYPE_PAY_VALUE);
		this.comparaisonSensitiveWithBracketsTypeExistanteAvecAnomalieFermee = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, SENSITIVE_BETWEEN_BRACKET_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				SENSITIVE_BETWEEN_BRACKET_TYPE_PAY_VALUE);
		this.comparaisonSensitiveWithBracketsTypeExistanteAvecAnomalieFermee
				.setDateCloture(DateUtils.getCalendarInstance().getTime());

		this.comparaisonInsensitive20CharTypeNouvelle = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				INSENSITIVE_TRUNC_20_TYPE_ANOMALIE, InteractionSirhEnum.PAY, INSENSITIVE_TRUNC_20_TYPE_PAY_VALUE);
		this.comparaisonInsensitive20CharTypeExistanteSansAnomalie = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT,
				MATRICULE, INSENSITIVE_TRUNC_20_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_20_TYPE_PAY_VALUE);
		this.comparaisonInsensitive20CharTypeExistanteAvecAnomalieOuverte = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, INSENSITIVE_TRUNC_20_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_20_TYPE_PAY_VALUE);
		this.comparaisonInsensitive20CharTypeExistanteAvecAnomalieFermee = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, INSENSITIVE_TRUNC_20_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_20_TYPE_PAY_VALUE);
		this.comparaisonInsensitive20CharTypeExistanteAvecAnomalieFermee
				.setDateCloture(DateUtils.getCalendarInstance().getTime());

		this.comparaisonInsensitive15CharTypeNouvelle = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT, MATRICULE,
				INSENSITIVE_TRUNC_15_TYPE_ANOMALIE, InteractionSirhEnum.PAY, INSENSITIVE_TRUNC_15_TYPE_PAY_VALUE);
		this.comparaisonInsensitive15CharTypeExistanteSansAnomalie = AnomalieDTOFactory.createComparaisonDTO(PAY_LOT,
				MATRICULE, INSENSITIVE_TRUNC_15_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_15_TYPE_PAY_VALUE);
		this.comparaisonInsensitive15CharTypeExistanteAvecAnomalieOuverte = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, INSENSITIVE_TRUNC_15_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_15_TYPE_PAY_VALUE);
		this.comparaisonInsensitive15CharTypeExistanteAvecAnomalieFermee = AnomalieDTOFactory.createComparaisonDTO(
				PAY_LOT, MATRICULE, INSENSITIVE_TRUNC_15_TYPE_ANOMALIE, InteractionSirhEnum.PAY,
				INSENSITIVE_TRUNC_15_TYPE_PAY_VALUE);
		this.comparaisonInsensitive15CharTypeExistanteAvecAnomalieFermee
				.setDateCloture(DateUtils.getCalendarInstance().getTime());
	}

	@Test
	public void testVerifierPresenceAnomalieCasUnsupportedDataTypeNoneData() {
		// Given
		this.comparaisons.add(this.comparaisonUnsupportedTypeNouvelle);

		// When
		AnomalieDetectionMSOUtils.verifierPresenceAnomalie(this.comparaisons);

		// Then
		Assertions.assertThat(this.comparaisons).isNotNull();
		Assertions.assertThat(this.comparaisons).isNotEmpty();
		Assertions.assertThat(this.comparaisons.get(0)).isNotNull();
		Assertions.assertThat(this.comparaisons.get(0).isAnomalieDonnees()).isFalse();
		Assertions.assertThat(this.comparaisons.get(0).isAnomalieReouverte()).isFalse();
		Assertions.assertThat(this.comparaisons.get(0).getEtatCorrection()).isNull();
		Assertions.assertThat(this.comparaisons.get(0).getDateCloture()).isNull();
	}

	@Test
	public void testVerifierPresenceAnomalieCasUnsupportedDataTypeWithData() {
		// Given
		this.comparaisonUnsupportedTypeExistanteSansAnomalie.getDonnees().setDonneeGA(UNSUPPORTED_TYPE_GA_VALUE);
		this.comparaisons.add(this.comparaisonUnsupportedTypeNouvelle);

		// When
		AnomalieDetectionMSOUtils.verifierPresenceAnomalie(this.comparaisons);

		// Then
		Assertions.assertThat(this.comparaisons).isNotNull();
		Assertions.assertThat(this.comparaisons).isNotEmpty();
		Assertions.assertThat(this.comparaisons.get(0)).isNotNull();
		Assertions.assertThat(this.comparaisons.get(0).isAnomalieDonnees()).isFalse();
		Assertions.assertThat(this.comparaisons.get(0).isAnomalieReouverte()).isFalse();
		Assertions.assertThat(this.comparaisons.get(0).getEtatCorrection()).isNull();
		Assertions.assertThat(this.comparaisons.get(0).getDateCloture()).isNull();
	}
}
