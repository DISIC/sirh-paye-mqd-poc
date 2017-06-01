package com.sirh.mqd.supplier.core.pay;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.exchanges.factory.pivot.AnomalieDTOFactory;
import com.sirh.mqd.commons.exchanges.factory.pivot.DossierDTOFactory;
import com.sirh.mqd.commons.utils.constante.Constantes;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.constantes.PayConstantes;

/**
 * Classe de service pour gérer les données GA-PAY
 *
 * @author alexandre
 */
@Service(CoreConstantes.PAY_SERVICE)
public class PayService {

	/*
	 * @Autowired
	 *
	 * @Qualifier(PersistenceSupplierConstantes.ELASTICSEARCH_BC) private
	 * ElasticsearchBC elasticsearchBC;
	 */

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
		final List<ComparaisonDTO> comparaisons = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 1) {
					final String[] lineArray = line.split(Constantes.COMMA);

					dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
							lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
							lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null, null,
							null, null, null, null));

					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.NB_POINTS, lineArray[16],
							lineArray[17]));
				}
				i++;
			}
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importEtatCivilData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisons = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 1) {
					final String[] lineArray = line.split(Constantes.COMMA);

					dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
							lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
							lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], lineArray[16],
							lineArray[18], lineArray[20], lineArray[22], lineArray[24], lineArray[26], null));

					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.NOM, lineArray[17],
							lineArray[18]));
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.PRENOM, lineArray[19],
							lineArray[20]));
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.DATE_NAISSANCE,
							lineArray[21], lineArray[22]));
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.CIVILITE, lineArray[23],
							lineArray[24]));
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.SEXE, lineArray[25],
							lineArray[26]));
				}
				i++;
			}
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}

	private void importCarriereData(final File payload) {
		Scanner scanner = null;
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();
		final List<ComparaisonDTO> comparaisons = new ArrayList<ComparaisonDTO>();
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 1) {
					final String[] lineArray = line.split(Constantes.COMMA);

					dossiers.add(DossierDTOFactory.createDossierDTO(lineArray[0], lineArray[1], lineArray[2],
							lineArray[3], lineArray[4], lineArray[5], lineArray[6], lineArray[9], lineArray[10],
							lineArray[11], lineArray[12], lineArray[13], lineArray[14], lineArray[15], null, null, null,
							null, null, null, lineArray[16]));

					// TODO : Comment détecter un écart sur ces données ? Les
					// informations sont toutes différentes.
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.GRADE, lineArray[17],
							lineArray[18]));

					// TODO : Comment détecter un écart sur ces données ? Les
					// informations sont toutes différentes.
					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.NIVEAU_ECHELON,
							lineArray[19], lineArray[20]));

					comparaisons.add(AnomalieDTOFactory.createComparaisonDTO(AnomalieTypeEnum.INDICE, lineArray[21],
							lineArray[22]));
				}
				i++;
			}
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			IOUtils.closeQuietly(scanner);
		}
	}
}
