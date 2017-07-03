package com.sirh.mqd.supplier.core.calendrier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.factory.calendrier.CalendrierGestionDTOFactory;
import com.sirh.mqd.commons.storage.bc.CalendrierGestionBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.utils.CalendrierGestionUtils;

/**
 * Classe de service pour gérer les Calendriers de Gestion
 *
 * @author alexandre
 */
@Service(CoreConstantes.CALENDRIER_GESTION_SERVICE)
public class CalendrierGestionService {

	@Autowired
	@Qualifier(PersistenceConstantes.CALENDRIER_GESTION_BC)
	private CalendrierGestionBC calendrierGestionBC;

	public void storeCSVDataFromDGAC(final Long timestamp, final File payload) {
		importCalendrierDGACData(payload);
	}

	private void importCalendrierDGACData(final File payload) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 1) {
					final String[] lineArray = CalendrierGestionUtils.splitDGACData(line);
					// Evénement : 0
					// Type : 1
					// Début : 2
					// Échéance : 3
					// Acteurs : 4
					// Corps : 5
					// Service : 6
					// Couleur : 7
					this.calendrierGestionBC.modifierCreerEvent(CalendrierGestionDTOFactory
							.createEventCalendrierDTOFromDGAC(lineArray[0], lineArray[1], lineArray[2], lineArray[3],
									lineArray[4], lineArray[5], lineArray[6], lineArray[7]));
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
