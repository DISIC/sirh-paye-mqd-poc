package com.sirh.mqd.supplier.core.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.factory.user.UserDTOFactory;
import com.sirh.mqd.commons.storage.bc.UserBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.utils.exception.TechnicalException;
import com.sirh.mqd.supplier.core.constantes.CoreConstantes;
import com.sirh.mqd.supplier.core.constantes.UserConstantes;
import com.sirh.mqd.supplier.core.utils.UserUtils;

/**
 * Classe de service pour gérer les utilisateurs
 *
 * @author alexandre
 */
@Service(CoreConstantes.USER_SERVICE)
public class UserService {

	@Autowired
	@Qualifier(PersistenceConstantes.USER_BC)
	private UserBC userBC;

	public void storeCSVData(final Long timestamp, final File payload) {
		final String filename = payload.getName();

		if (UserConstantes.CSV_FILE_USER.equals(filename)) {
			importUtilisateursData(payload);
		}
	}

	private void importUtilisateursData(final File payload) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(payload);
			int i = 0;
			while (scanner.hasNextLine()) {
				final String line = scanner.nextLine();
				if (i > 1) {
					final String[] lineArray = UserUtils.splitUtilisateursData(line);
					// Ministère : 0
					// Utilisateur : 1
					// Prénom : 2
					// Nom : 3
					// Email : 4
					// Login : 5
					// Password : 6
					// Code gestionnaire paye (DGAC) : 7
					// Lot de paye : 8
					// Corps (code) : 9
					// Affectation administrative (code) : 10
					// Rôle : 11
					// Service (si rôle pilotage) : 12
					// Commentaire : 13
					userBC.ajouterUtilisateur(UserDTOFactory.createUserDTO(lineArray[5], lineArray[6], lineArray[4],
							lineArray[2], lineArray[3], lineArray[8], lineArray[9], lineArray[10], lineArray[7],
							lineArray[0], lineArray[12], UserUtils.createListeRoles(lineArray[11])));
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
