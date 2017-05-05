package com.sirh.mqd.commons.storage.bc;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.enums.ReferentielEnum;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.ISynchroReferentielsDAO;

/**
 * Implémentation du BusinessController permettant de vérifier si les
 * référentiels sont synchronisés avec le Tampon.
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.SYNCHRO_REFERENTIELS_TAMPON_BC)
public class SynchroReferentielsBC {

	@Autowired
	@Qualifier(PersistenceConstantes.SYNCHRO_REFERENTIELS_DAO)
	private ISynchroReferentielsDAO synchroReferentielsDAO;

	/**
	 * Méthode permettant de remonter la date de la dernière mise à jours d'un
	 * referentiel donné
	 *
	 * @param referentiel
	 * @return
	 */
	public String rechercherDerniereMAJ(final ReferentielEnum referentiel) {
		return synchroReferentielsDAO.selectLastDateUpDate(referentiel);
	}

	/**
	 * Méthode permettant de modifier la date de mise à jours d'un referentiel
	 * donné
	 *
	 * @param majDate
	 * @param referentiel
	 * @param duree
	 *            TODO
	 */
	public void modifierDateMaj(final String majDate, final ReferentielEnum referentiel, final Long duree,
			final TimeUnit timeUnit) {
		synchroReferentielsDAO.insertLastDateUpDate(majDate, referentiel, duree, timeUnit);
	}

}
