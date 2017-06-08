package com.sirh.mqd.supplier.core.pay;

import java.io.File;
import java.io.FileNotFoundException;
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
import com.sirh.mqd.supplier.core.constantes.PayConstantes;
import com.sirh.mqd.supplier.core.utils.AnomalieDetectionUtils;

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

			break;
		case PayConstantes.CSV_FILE_ADRESSE:

			break;
		case PayConstantes.CSV_FILE_CARRIERE:
			importCarriereData(payload);
			break;
		case PayConstantes.CSV_FILE_COOR_BANC:

			break;
		case PayConstantes.CSV_FILE_ENFANTS:

			break;
		case PayConstantes.CSV_FILE_ETAT_CIVIL:
			importEtatCivilData(payload);
			break;
		case PayConstantes.CSV_FILE_GLOBAL:

			break;
		case PayConstantes.CSV_FILE_NBI:
			importNBIData(payload);
			break;
		case PayConstantes.CSV_FILE_PARAM_RAPPORT:

			break;
		case PayConstantes.CSV_FILE_POSITION:

			break;
		case PayConstantes.CSV_FILE_TEMPS_TRAVAIL:

			break;

		default:
			break;
		}
	}

	private void importNBIData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisonsPAY = new ArrayList<ComparaisonDTO>();
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length == 18) {
						dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NB_POINTS, InteractionSirhEnum.PAY, lineArray[16]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NB_POINTS, InteractionSirhEnum.GA, lineArray[17]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(InteractionSirhEnum.GA, comparaisonsGA);
			this.stockerComparaisons(InteractionSirhEnum.PAY, comparaisonsPAY);
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
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length == 27) {
						dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15],
								lineArray[16], lineArray[18], lineArray[20], lineArray[22], lineArray[24],
								lineArray[26], null));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NOM, InteractionSirhEnum.PAY, lineArray[17]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NOM, InteractionSirhEnum.GA, lineArray[18]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.PRENOM, InteractionSirhEnum.PAY, lineArray[19]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.PRENOM, InteractionSirhEnum.GA, lineArray[20]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.DATE_NAISSANCE, InteractionSirhEnum.PAY, lineArray[21]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.DATE_NAISSANCE, InteractionSirhEnum.GA, lineArray[22]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CIVILITE, InteractionSirhEnum.PAY, lineArray[23]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.CIVILITE, InteractionSirhEnum.GA, lineArray[24]));
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.SEXE, InteractionSirhEnum.PAY, lineArray[25]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.SEXE, InteractionSirhEnum.GA, lineArray[26]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(InteractionSirhEnum.GA, comparaisonsGA);
			this.stockerComparaisons(InteractionSirhEnum.PAY, comparaisonsPAY);
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
		final List<ComparaisonDTO> comparaisonsGA = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 2) {
					final String[] lineArray = AnomalieDetectionUtils.splitPAYData(line);
					// La dernière ligne du tableau correspond à des calculs
					// EXCEL : On ne la traite pas.
					if (lineArray.length == 23) {
						dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
								lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
								lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null,
								null, null, null, null, lineArray[16]));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.GRADE, InteractionSirhEnum.PAY, lineArray[17]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.GRADE, InteractionSirhEnum.GA, lineArray[18]));

						// TODO : Comment détecter un écart sur ces données ?
						// Les informations sont toutes différentes.
						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NIVEAU_ECHELON, InteractionSirhEnum.PAY, lineArray[19]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.NIVEAU_ECHELON, InteractionSirhEnum.GA, lineArray[20]));

						comparaisonsPAY.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.INDICE, InteractionSirhEnum.PAY, lineArray[21]));
						comparaisonsGA.add(AnomalieDTOFactory.createComparaisonDTO(lineArray[6], lineArray[9],
								AnomalieTypeEnum.INDICE, InteractionSirhEnum.GA, lineArray[22]));
					}
				}
				i++;
			}
			this.stockerDossiers(dossiers);
			this.stockerComparaisons(InteractionSirhEnum.GA, comparaisonsGA);
			this.stockerComparaisons(InteractionSirhEnum.PAY, comparaisonsPAY);
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
	 * @param referentiel
	 *            le référentiel d'où sont issues les données à comparer
	 * @param comparaisons
	 *            liste des données en entrée potentiellement en anomalie
	 */
	private void stockerComparaisons(final InteractionSirhEnum referentiel, final List<ComparaisonDTO> donnees) {
		final List<ComparaisonDTO> comparaisons = new ArrayList<ComparaisonDTO>();
		final Date dateCloture = DateUtils.getCalendarInstance().getTime();
		donnees.forEach((donnee) -> {
			final Optional<ComparaisonDTO> nullableComparaison = this.dossierBC.rechercherComparaison(donnee);
			if (nullableComparaison.isPresent()) {
				final ComparaisonDTO comparaison = nullableComparaison.get();
				switch (referentiel) {
				case PAY:
					comparaison.getDonnees().setDonneePAY(donnee.getDonnees().getDonneePAY());
					break;
				case GA:
					comparaison.getDonnees().setDonneeGA(donnee.getDonnees().getDonneeGA());
					break;
				default:
					break;
				}
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
		AnomalieDetectionUtils.verifierPresenceAnomalie(comparaisons);
		comparaisons.forEach((anomalie) -> {
			this.dossierBC.insererComparaison(anomalie);
		});
	}
}
