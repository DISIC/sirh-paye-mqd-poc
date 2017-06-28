package com.sirh.mqd.supplier.core.pay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.exchanges.factory.pivot.AnomalieDTOFactory;
import com.sirh.mqd.commons.exchanges.factory.pivot.DossierDTOFactory;
import com.sirh.mqd.commons.storage.bc.DossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.constantes.PayConstantes;
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

	public void storeCSVData(final Long timestamp, final File payload) {
		final String filename = payload.getName();

		switch (filename) {
		case PayConstantes.CSV_FILE_ABSENCE:
			importAbsenceData(payload);
			break;
		case PayConstantes.CSV_FILE_ADRESSE:
			importAdresseData(payload);
			break;
		case PayConstantes.CSV_FILE_CARRIERE:
			importCarriereData(payload);
			break;
		case PayConstantes.CSV_FILE_COOR_BANC:
			importCoordonneeBancaireData(payload);
			break;
		case PayConstantes.CSV_FILE_ENFANTS:
			importEnfantData(payload);
			break;
		case PayConstantes.CSV_FILE_ETAT_CIVIL:
			importEtatCivilData(payload);
			break;
		case PayConstantes.CSV_FILE_GLOBAL:
			importGlobalData(payload);
			break;
		case PayConstantes.CSV_FILE_NBI:
			importNBIData(payload);
			break;
		case PayConstantes.CSV_FILE_POSITION:
			importPositionData(payload);
			break;
		case PayConstantes.CSV_FILE_TEMPS_TRAVAIL:
			importTempsTravailData(payload);
			break;
		default:
			break;
		}
	}

	private void importTempsTravailData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, lineArray[16], lineArray[17]));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.MODALITE, InteractionSirhEnum.PAY, lineArray[18]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importPositionData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						// TODO : Attention à la conversion des dates.
						// Si non présente un message par défaut est écrit :
						// Aucune date trouvée
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.DATE_FIN_FONCTION, InteractionSirhEnum.PAY, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importGlobalData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (StringUtils.isNotBlank(lineArray[0])) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEnfantData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						// Colonne : Nombre d'enfants SFT PAY
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NB_ENFANTS, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCoordonneeBancaireData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.MODE_PAIEMENT, InteractionSirhEnum.PAY, lineArray[16]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.IBAN, InteractionSirhEnum.PAY, lineArray[18]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.BIC_SWIFT, InteractionSirhEnum.PAY, lineArray[20]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAdresseData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NUMERO_VOIE, InteractionSirhEnum.PAY, lineArray[16]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COMPLEMENT_NUMERO_VOIE, InteractionSirhEnum.PAY, lineArray[18]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.TYPE_VOIE, InteractionSirhEnum.PAY, lineArray[20]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NOM_VOIE, InteractionSirhEnum.PAY, lineArray[22]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CODE_POSTAL, InteractionSirhEnum.PAY, lineArray[24]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COMMUNE, InteractionSirhEnum.PAY, lineArray[26]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COMPLEMENT_ADRESSE, InteractionSirhEnum.PAY, lineArray[28]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAbsenceData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ABSENCE, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importNBIData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NB_POINTS, InteractionSirhEnum.PAY, lineArray[16]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEtatCivilData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15],
								lineArray[16], lineArray[18], lineArray[20], lineArray[22], lineArray[24],
								lineArray[26], null, null, null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NOM, InteractionSirhEnum.PAY, lineArray[17]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.PRENOM, InteractionSirhEnum.PAY, lineArray[19]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.DATE_NAISSANCE, InteractionSirhEnum.PAY, lineArray[21]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CIVILITE, InteractionSirhEnum.PAY, lineArray[23]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.SEXE, InteractionSirhEnum.PAY, lineArray[25]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCarriereData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
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
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, lineArray[16], null, null));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.GRADE, InteractionSirhEnum.PAY, lineArray[17]));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NIVEAU_ECHELON, InteractionSirhEnum.PAY, lineArray[19]));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.INDICE, InteractionSirhEnum.PAY, lineArray[21]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsPAY);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	/**
	 * Méthode permettant de stocker en base de données les dossiers.
	 *
	 * @param dossiers
	 *            liste des dossiers en entrée
	 */
	private void stockerDossiers(final List<DossierDTO> dossiers) {
		dossiers.forEach((dossier) -> {
			this.dossierBC.insererDossier(dossier);
		});
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
