package com.sirh.mqd.supplier.core.pay;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.exchanges.factory.pivot.AnomalieDTOFactory;
import com.sirh.mqd.commons.storage.bc.DossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.constantes.PayConstantes;
import com.sirh.mqd.supplier.core.utils.AnomalieDetectionDGACUtils;
import com.sirh.mqd.supplier.core.utils.AnomalieDetectionMSOUtils;

/**
 * Classe de service pour gérer les données GA-PAY
 *
 * @author alexandre
 */
@Service(CoreConstantes.PAY_SERVICE)
public class PayService {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_BC)
	private DossierBC dossierBC;

	public void storeData(final Long timestamp, final File payload) {
		final String filename = payload.getName();

		switch (filename) {
		case PayConstantes.CSV_FILE_MSO_ABSENCE:
			importAbsenceData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_ADRESSE:
			importAdresseData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_CARRIERE:
			importCarriereData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_COOR_BANC:
			importCoordonneeBancaireData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_ENFANTS:
			importEnfantData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_ETAT_CIVIL:
			importEtatCivilData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_NBI:
			importNBIData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_POSITION:
			importPositionData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_TEMPS_TRAVAIL:
			importTempsTravailData(payload);
			break;
		case PayConstantes.CSV_FILE_DGAC:
			importDGACData(payload);
			break;
		default:
			break;
		}
	}

	private void importDGACData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload, StandardCharsets.ISO_8859_1.name());
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionDGACUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 13) {
						// Matricule : 0
						// INSEE : 1
						// Nom usuel : 2
						// Prenom : 3
						// Numero dossier : 4
						// Etat civil : 5
						// Code grade : 6
						// Echelon : 7
						// Indice majoré : 8
						// Pension : 9
						// Regime de rémunération : 10
						// NBI : 11
						// Nombre d'heure (Numérateur) : 12
						// Nombre d'heure (Dénominateur) : 13
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.TG_NUMERO_DOSSIER, InteractionSirhEnum.PAY, lineArray[4]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.ETAT_CIVIL_INSEE, InteractionSirhEnum.PAY, lineArray[1]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.ETAT_CIVIL_NOM, InteractionSirhEnum.PAY, lineArray[2]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.ETAT_CIVIL_PRENOM, InteractionSirhEnum.PAY, lineArray[3]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.ETAT_CIVIL_CIVILITE, InteractionSirhEnum.PAY, lineArray[5]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.CARRIERE_GRADE, InteractionSirhEnum.PAY, lineArray[6]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.CARRIERE_NIVEAU_ECHELON, InteractionSirhEnum.PAY, lineArray[7]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.CARRIERE_INDICE, InteractionSirhEnum.PAY, lineArray[8]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.PENSION_PENSION, InteractionSirhEnum.PAY, lineArray[9]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importTempsTravailData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 19) {
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.TEMPS_TRAVAIL_MODALITE, InteractionSirhEnum.PAY, lineArray[18]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importPositionData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 18) {
						// TODO : Attention à la conversion des dates.
						// Si non présente un message par défaut est écrit :
						// Aucune date trouvée
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.POSITION_DATE_FIN_FONCTION, InteractionSirhEnum.PAY, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEnfantData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 18) {
						// Colonne : Nombre d'enfants SFT PAY
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ENFANT_NB_ENFANTS, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCoordonneeBancaireData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 21) {
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_MODE_PAIEMENT, InteractionSirhEnum.PAY, lineArray[16]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_IBAN, InteractionSirhEnum.PAY, lineArray[18]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_BIC_SWIFT, InteractionSirhEnum.PAY, lineArray[20]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAdresseData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 29) {
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_NUMERO_VOIE, InteractionSirhEnum.PAY, lineArray[16]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMPLEMENT_NUMERO_VOIE, InteractionSirhEnum.PAY, lineArray[18]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_TYPE_VOIE, InteractionSirhEnum.PAY, lineArray[20]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_NOM_VOIE, InteractionSirhEnum.PAY, lineArray[22]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_CODE_POSTAL, InteractionSirhEnum.PAY, lineArray[24]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMMUNE, InteractionSirhEnum.PAY, lineArray[26]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMPLEMENT_ADRESSE, InteractionSirhEnum.PAY, lineArray[28]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAbsenceData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 17) {
						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ABSENCE_ABSENCE, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importNBIData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 17) {
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NBI_NB_POINTS, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEtatCivilData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 26) {
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_NOM, InteractionSirhEnum.PAY, lineArray[17]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_PRENOM, InteractionSirhEnum.PAY, lineArray[19]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_DATE_NAISSANCE, InteractionSirhEnum.PAY, lineArray[21]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_CIVILITE, InteractionSirhEnum.PAY, lineArray[23]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_SEXE, InteractionSirhEnum.PAY, lineArray[25]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCarriereData(final File payload) {
		Scanner scanner = null;
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 22) {
						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_GRADE, InteractionSirhEnum.PAY, lineArray[17]));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_NIVEAU_ECHELON, InteractionSirhEnum.PAY, lineArray[19]));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_INDICE, InteractionSirhEnum.PAY, lineArray[21]));
					}
				}
				i++;
			}
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	/**
	 * Méthode permettant de stocker en base de données les comparaisons de
	 * données potentiellement en anomalie.
	 *
	 * @param comparaisons
	 *            liste des données en entrée potentiellement en anomalie
	 */
	private void stockerComparaisons(final List<ComparaisonDTO> donnees) {
		final List<ComparaisonDTO> comparaisons = new ArrayList<ComparaisonDTO>();
		final Date dateCloture = DateUtils.getCalendarInstance().getTime();
		donnees.forEach((donnee) -> {
			final Optional<ComparaisonDTO> nullableComparaison = this.dossierBC.rechercherComparaison(donnee);
			if (nullableComparaison.isPresent()) {
				final ComparaisonDTO comparaison = nullableComparaison.get();
				comparaison.getDonnees().setDonneePAY(donnee.getDonnees().getDonneePAY());
				comparaison.setAnomalieReouverte(false);
				comparaison.setAnomalieDonnees(false);
				if (comparaison.getDateCloture() == null) {
					comparaison.setDateCloture(dateCloture);
				}
				comparaisons.add(comparaison);
			} else {
				comparaisons.add(donnee);
			}
		});
		AnomalieDetectionMSOUtils.verifierPresenceAnomalie(comparaisons);
		comparaisons.forEach((anomalie) -> {
			this.dossierBC.insererComparaison(anomalie);
		});
	}
}
