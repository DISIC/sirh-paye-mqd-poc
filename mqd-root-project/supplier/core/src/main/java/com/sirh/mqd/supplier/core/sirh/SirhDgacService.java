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
import com.sirh.mqd.supplier.core.constantes.DgacConstantes;
import com.sirh.mqd.supplier.core.utils.AnomalieDetectionDGACUtils;

/**
 * Classe de service pour la gestion des entrants du SIRH DGAC
 *
 * @author alexandre
 */
@Service(CoreConstantes.SIRH_DGAC_SERVICE)
public class SirhDgacService {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_BC)
	private DossierBC dossierBC;

	public void dropSIRHDatasource(final InputStream multipleFiles) {

	}

	public void storeTXTData(final Long timestamp, final File payload) {
		final String filename = payload.getName();

		switch (filename) {
		case DgacConstantes.TXT_FILE_ADRESSE:
			importAdresseData(payload);
			break;
		case DgacConstantes.TXT_FILE_CARRIERE:
			importCarriereData(payload);
			break;
		case DgacConstantes.TXT_FILE_BANQUE:
			importCoordonneeBancaireData(payload);
			break;
		case DgacConstantes.TXT_FILE_ETAT_CIVIL:
			importEtatCivilData(payload);
			break;
		case DgacConstantes.TXT_FILE_NBI:
			importNBIData(payload);
			break;
		case DgacConstantes.TXT_FILE_POSITION:
			importPositionData(payload);
			break;
		case DgacConstantes.TXT_FILE_TEMPS_TRAVAIL:
			importTempsTravailData(payload);
			break;
		case DgacConstantes.TXT_FILE_TG:
			importTGData(payload);
			break;
		case DgacConstantes.TXT_FILE_PENSION:
			importPensionData(payload);
			break;
		default:
			break;
		}
	}

	private void importPensionData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 7) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Pension : 7
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1], null,
								null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.PENSION, InteractionSirhEnum.DGAC, lineArray[7]));
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

	private void importTGData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 11) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Numero de dossier : 7
						// Code TG : 8
						// Ministère : 9
						// Code gestionnaire : 10
						// Temoin dossier principal : 11
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(lineArray[9], lineArray[7],
								lineArray[0], null, null, null, null, null, null, lineArray[3], lineArray[4], null,
								null, lineArray[1], lineArray[10], lineArray[8], lineArray[11]));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 10) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Temps de travail : 7
						// Durée année : 8
						// Durée mois : 9
						// Durée jour : 10
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1], null,
								null, null));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin prévue : 6
						// Date de fin réelle : 7
						// Position administative : 8
						// Position statutaire : 9
						// Durée année : 10
						// Durée mois : 11
						// Durée jour : 12
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1], null,
								null, null));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Type NBI : 7
						// Nombre de points : 8
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1], null,
								null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.NB_POINTS, InteractionSirhEnum.DGAC, lineArray[8]));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Qualité : 5
						// Date de naissance : 6 > format : 1981-01-05-00.00.00
						// Situation familiale : 7
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], lineArray[6], null, lineArray[1],
								null, null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.INSEE, InteractionSirhEnum.DGAC, lineArray[1]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.NOM, InteractionSirhEnum.DGAC, lineArray[3]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.PRENOM, InteractionSirhEnum.DGAC, lineArray[4]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.DATE_NAISSANCE, InteractionSirhEnum.DGAC, lineArray[6]));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Mode paiement : 7
						// IBAN : 8
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								null, null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1], null,
								null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.MODE_PAIEMENT, InteractionSirhEnum.DGAC, lineArray[7]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.IBAN, InteractionSirhEnum.DGAC, lineArray[8]));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Qualité statutaire : 7
						// Code carrière : 8
						// Corps : 9
						// Grade : 10
						// Echelon : 11
						// Indice majoré : 12
						// Date d'entree dans le grade : 13 > format :
						// 2001-09-12-00.00.00
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], lineArray[9],
								null, lineArray[10], null, null, null, lineArray[3], lineArray[4], null, lineArray[13],
								lineArray[1], null, null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.INDICE, InteractionSirhEnum.DGAC, lineArray[12]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.GRADE, InteractionSirhEnum.DGAC, lineArray[10]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.NIVEAU_ECHELON, InteractionSirhEnum.DGAC, lineArray[11]));
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
					final String[] lineArray = AnomalieDetectionDGACUtils.splitDGACData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length > 12) {
						// Matricule : 0
						// Numero INSEE : 1
						// Provisoire Numero INSEE : 2
						// Nom patronymique : 3
						// Prenom : 4
						// Date d'effet : 5
						// Date de fin : 6
						// Code pays : 7
						// Code postal : 8
						// Adresse : 9
						dossiers.add(DossierDTOFactory.createDossierDTOFromDGAC(null, null, lineArray[0], null, null,
								lineArray[10], null, null, null, lineArray[3], lineArray[4], null, null, lineArray[1],
								null, null, null));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.VOIE, InteractionSirhEnum.DGAC, lineArray[9]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.CODE_POSTAL, InteractionSirhEnum.DGAC, lineArray[8]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(null, lineArray[0],
								AnomalieTypeEnum.CODE_PAYS, InteractionSirhEnum.DGAC, lineArray[7]));
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
	 * @param donnees
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
		AnomalieDetectionDGACUtils.verifierPresenceAnomalie(comparaisons);
		comparaisons.forEach((anomalie) -> {
			this.dossierBC.insererComparaison(anomalie);
		});
	}

}
