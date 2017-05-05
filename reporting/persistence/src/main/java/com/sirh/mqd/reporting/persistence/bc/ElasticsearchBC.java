package com.sirh.mqd.reporting.persistence.bc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.exchanges.factory.LogFactory;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.dao.IElasticsearchDAO;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

/**
 * Implémentation du BusinessController permettant la manipulation des données provenant d'elasticsearch
 *
 * @author adile
 *
 */
@Service(PersistenceReportingConstantes.ELASTICSEARCH_BC)
public class ElasticsearchBC {

	@Autowired
	private IElasticsearchDAO<WSGenericMetierEntity> echangeDao;

	public ElasticsearchBC() {

	}

	enum ActionValue {
		DEFAULT("Interne au relais"),
		//
		SEND_REQUEST_PRODUCTEUR("Envois d'un appel à un producteur du web service: "),
		//
		SEND_DELIVERY_DIFFUSEUR("Envois d'une réponse à un diffuseur du web service: "),
		//
		RECEV_REQUEST_DIFFUSEUR("Reception d'un appel d'un diffuseur sur le service: "),
		//
		RECEV_DELIVERY_PRODUCTEUR("Réception d'une réponse/notification d'un producteur sur le service: ");

		String libelle;

		ActionValue(final String libelle) {
			this.libelle = libelle;
		}

	}

	/**
	 * Recupere l'ensemble des données contenu dans les noeuds
	 *
	 * @return
	 */
	public Page<WSGenericMetierEntity> findAll() {
		return echangeDao.findAll();
	}

	/**
	 * Recupere les données avec comme critère le nom du service
	 *
	 * @param serviceName
	 * @param from
	 * @param to
	 * @return
	 */
	public Page<WSGenericMetierEntity> findByService(final String serviceName, final Date from, final Date to) {
		return echangeDao.findByService(serviceName, from, to);
	}

	/**
	 * Recupère les logs de la nature en parametre
	 *
	 * @param serviceName
	 *            nom du service SIRI (ex generalmessage etc...)
	 * @param from
	 * @param to
	 * @return
	 */
	public Long findByActionNature(final String nature, final Date from, final Date to) {
		return echangeDao.findByActionNature(nature, from, to).getTotalElements();
	}

	/**
	 * Recupere les données avec comme critère le nom du service
	 *
	 * @param serviceName
	 * @return
	 */
	public Page<WSGenericMetierEntity> findByService(final String serviceName) {
		return echangeDao.findByService(serviceName, null, null);
	}

	/**
	 * Recupere le nombre de notification d'un partenaire faite sur une durée et à une date précise.
	 *
	 * @param partenaire
	 * @param duree
	 * @param date
	 * @return le nombre de notification
	 */
	public Page<WSGenericMetierEntity> findNotif(final Date dateDebut, final Date dateFin) {

		return echangeDao.findNotification(dateDebut, dateFin);
	}

	/**
	 * Recherche avec les critères en param
	 *
	 * @param mode
	 * @param composant
	 * @param service
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	public Page<WSGenericMetierEntity> findByServiceAndSens(final ModeEchange mode, final ModuleEnum composant, final String service,
			final String sens, final Date from, final Date to) {

		return echangeDao.findByServiceAndSens(mode, composant, service, sens, from, to);
	}

	/**
	 * Recherche avec les critères en param
	 *
	 * @param mode
	 * @param composant
	 * @param service
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	public Page<WSGenericMetierEntity> findByServiceAndSensAndStatus(final ModeEchange mode, final ModuleEnum composant, final String service,
			final String sens, final Boolean status, final Date from, final Date to) {

		return echangeDao.findByServiceAndSensAndStatus(mode, composant, service, sens, status, from, to);

	}

	public Page<WSGenericMetierEntity> findByErreurMetier(final String sens, final List<EnumErreursMetier> list, final Date startDate,
			final Date endDate) {
		return echangeDao.findByErreurMetier(sens, list, startDate, endDate);
	}

	public Page<WSGenericMetierEntity> findByErreurCondition(final List<EnumErrorCondition> erreurConditionList, final Date startDate,
			final Date endDate) {
		return echangeDao.findByErreurCondition(erreurConditionList, startDate, endDate);
	}

	public Page<WSGenericMetierEntity> findByErreurConditionAndService(final List<EnumErrorCondition> erreurConditionList,
			final List<EnumService> erreurServiceList, final Date startDate, final Date endDate) {
		return echangeDao.findByErreurConditionAndService(erreurConditionList, erreurServiceList, startDate, endDate);
	}

	/**
	 * Recupere les données pour le rapport 4
	 *
	 * @param isArret
	 * @param isProducteur
	 * @param isRequete
	 * @return Page<WSGenericMetierEntity>
	 */
	public Page<WSGenericMetierEntity> findforRapport4(final Boolean isArret, final Boolean isProducteur, final Boolean isRequete,
			final Date from, final Date to) {
		String sens = null;
		ModuleEnum className = null;
		String modeEchange = null;

		if (isProducteur) {
			className = ModuleEnum.ACQUISITION;
			if (!isRequete) {
				//mode abonnement
				sens = LogFactory.SENS_RECEPTION;
				modeEchange = ModeEchange.NOTIFICATION.name();
			} else {
				//mode requete
				sens = LogFactory.SENS_RECEPTION;
				modeEchange = "REPONSE"; // ModeEchange.REPONSE_22 ModeEchange.REPONSE_24
			}
		} else {
			//Diffuseur
			className = ModuleEnum.EMISSION;
			if (!isRequete) {
				//mode abonnement
				sens = LogFactory.SENS_EMISSION;
				modeEchange = ModeEchange.NOTIFICATION.name();
			} else {
				//mode requete
				sens = LogFactory.SENS_EMISSION;
				modeEchange = "REPONSE"; // ModeEchange.REPONSE_22 ModeEchange.REPONSE_24
			}
		}
		//les creations d abonnement ne compte pas pour l admin et emission la meme que diffuseur
		// Le module reception envois des requêtes et reçoit des réponses;
		if (sens != null && modeEchange != null && className != null) {
			///sur une semaine le rapport 4

			if (isArret) {

				return echangeDao.findArretBySensAndClassName(modeEchange, Arrays.asList(className), sens, from, to,
						EnumService.GetStopMonitoring.getDomaine());
			} else {
				return echangeDao.findLigneBySensAndClassName(modeEchange, Arrays.asList(className), sens, from, to,
						EnumService.GetEstimatedTimetable.getDomaine());
			}
		}
		return null;
	}

	/**
	 * Recherche les logs par composant et sens
	 *
	 * @param list
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	public Page<WSGenericMetierEntity> findByComposantAndSens(final ModeEchange mode, final String module, final String sens,
			final Date from, final Date to) {

		return echangeDao.findByComposantAndSens(mode, module, sens, from, to);

	}

	/**
	 * Récupére les logs de performance
	 *
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 */
	public Page<WSGenericMetierEntity> findEchange(final Date dateDebut, final Date dateFin) {

		return echangeDao.findEchange(dateDebut, dateFin);
	}

	public Page<WSPerformanceEntity> find(final String composant, final Date startDate, final Date endDate) {
		return echangeDao.find(composant, startDate, endDate);
	}

}
