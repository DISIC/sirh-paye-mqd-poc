package com.sirh.mqd.supplier.core.sirh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.sirh.mqd.supplier.core.constantes.MsoConstantes;
import com.sirh.mqd.supplier.core.constantes.PayConstantes;
import com.sirh.mqd.supplier.core.utils.AnomalieDetectionMSOUtils;

/**
 * Classe de service pour la gestion des entrants du SIRH MSO
 *
 * @author alexandre
 */
@Service(CoreConstantes.SIRH_MSO_SERVICE)
public class SirhMsoService {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_BC)
	private DossierBC dossierBC;

	public void dropSIRHDatasource(final InputStream multipleFiles) {

	}

	public void storeCSVData(final Long timestamp, final File payload) {
		final String filename = payload.getName();

		switch (filename) {
		case MsoConstantes.CSV_FILE_ABSENCE:
			importAbsenceData(payload);
			break;
		case MsoConstantes.CSV_FILE_ADRESSE:
			importAdresseData(payload);
			break;
		case MsoConstantes.CSV_FILE_CARRIERE:
			importCarriereData(payload);
			break;
		case MsoConstantes.CSV_FILE_COOR_BANC:
			importCoordonneeBancaireData(payload);
			break;
		case MsoConstantes.CSV_FILE_ENFANTS:
			importEnfantData(payload);
			break;
		case MsoConstantes.CSV_FILE_ETAT_CIVIL:
			importEtatCivilData(payload);
			break;
		case MsoConstantes.CSV_FILE_NBI:
			importNBIData(payload);
			break;
		case MsoConstantes.CSV_FILE_POSITION:
			importPositionData(payload);
			break;
		case MsoConstantes.CSV_FILE_TEMPS_TRAVAIL:
			importTempsTravailData(payload);
			break;
		case PayConstantes.CSV_FILE_MSO_GLOBAL:
			importGlobalData(payload);
			break;
		default:
			break;
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

	private void importTempsTravailData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 19) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, lineArray[16], lineArray[17]));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.TEMPS_TRAVAIL_MODALITE, InteractionSirhEnum.MSO, lineArray[19]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importPositionData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
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
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.POSITION_DATE_FIN_FONCTION, InteractionSirhEnum.MSO, lineArray[18]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEnfantData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 18) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));
						// TODO : Quelle colonne prendre en compte pour RenoiRH
						// ?
						// - Nombre d'enfants SFT RenoiRH : 17
						// - Nombre total d'enfants RenoiRH : 18
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ENFANT_NB_ENFANTS, InteractionSirhEnum.MSO, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCoordonneeBancaireData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 21) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_MODE_PAIEMENT, InteractionSirhEnum.MSO, lineArray[17]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_IBAN, InteractionSirhEnum.MSO, lineArray[19]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.COORDONNEE_BANCAIRE_BIC_SWIFT, InteractionSirhEnum.MSO, lineArray[21]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAdresseData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 29) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_NUMERO_VOIE, InteractionSirhEnum.MSO, lineArray[17]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMPLEMENT_NUMERO_VOIE, InteractionSirhEnum.MSO, lineArray[19]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_TYPE_VOIE, InteractionSirhEnum.MSO, lineArray[21]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_NOM_VOIE, InteractionSirhEnum.MSO, lineArray[23]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_CODE_POSTAL, InteractionSirhEnum.MSO, lineArray[25]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMMUNE, InteractionSirhEnum.MSO, lineArray[27]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ADRESSE_COMPLEMENT_ADRESSE, InteractionSirhEnum.MSO, lineArray[29]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importAbsenceData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 17) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ABSENCE_ABSENCE, InteractionSirhEnum.MSO, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importNBIData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 17) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null, null, null));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NBI_NB_POINTS, InteractionSirhEnum.MSO, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEtatCivilData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 26) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15],
								lineArray[16], lineArray[18], lineArray[20], lineArray[22], lineArray[24],
								lineArray[26], null, null, null));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_NOM, InteractionSirhEnum.MSO, lineArray[18]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_PRENOM, InteractionSirhEnum.MSO, lineArray[20]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_DATE_NAISSANCE, InteractionSirhEnum.MSO, lineArray[22]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_CIVILITE, InteractionSirhEnum.MSO, lineArray[24]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.ETAT_CIVIL_SEXE, InteractionSirhEnum.MSO, lineArray[26]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCarriereData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionMSOUtils.splitMSOData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 22) {
						dossiers.add(DossierDTOFactory.createDossierDTOFromMSO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, lineArray[16], null, null));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_GRADE, InteractionSirhEnum.MSO, lineArray[18]));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_NIVEAU_ECHELON, InteractionSirhEnum.MSO, lineArray[20]));

						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CARRIERE_INDICE, InteractionSirhEnum.MSO, lineArray[22]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(comparaisonsGA);
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
				comparaison.getDonnees().setDonneeGA(donnee.getDonnees().getDonneeGA());
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
