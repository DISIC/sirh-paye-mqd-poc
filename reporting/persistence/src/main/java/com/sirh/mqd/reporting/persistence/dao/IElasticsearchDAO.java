package com.sirh.mqd.reporting.persistence.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.FacetedPage;

import com.sirh.mqd.commons.exchanges.enums.EnumErreursMetier;
import com.sirh.mqd.commons.exchanges.enums.EnumErrorCondition;
import com.sirh.mqd.commons.exchanges.enums.EnumService;
import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.reporting.persistence.entities.IHMActionEntity;
import com.sirh.mqd.reporting.persistence.entities.WSPerformanceEntity;

public interface IElasticsearchDAO<T> {

	/**
	 * Recupere la totalité des logs
	 *
	 * @return
	 */
	Page<T> findAll();

	/**
	 * Recupère les logs du service en parametre
	 *
	 * @param serviceName
	 *            nom du service SIRI (ex generalmessage etc...)
	 * @param from
	 * @param to
	 * @return
	 */
	Page<T> findByService(String serviceName, final Date from, final Date to);

	/**
	 * Compte le nombre de notification faite par un partenaire sur une période donnée.
	 *
	 * @param from
	 *            date de début
	 * @param to
	 *            date de fin
	 *
	 * @return nombre de notification
	 */
	FacetedPage<T> findNotification(Date date, Date addTime);

	/**
	 * Recupère les logs de la nature en parametre
	 *
	 * @param serviceName
	 *            nom du service SIRI (ex generalmessage etc...)
	 * @param from
	 * @param to
	 * @return
	 */
	Page<IHMActionEntity> findByActionNature(String nature, Date from, Date to);

	/**
	 * Recupère en fonction du service et du sens
	 *
	 * @param mode
	 *
	 * @param service
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	Page<T> findByServiceAndSens(ModeEchange mode, final ModuleEnum composant, final String service, final String sens, final Date from,
			final Date to);

	/**
	 * Recupère en fonction du service et du sens
	 *
	 * @param mode
	 *
	 * @param service
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	Page<T> findByServiceAndSensAndStatus(ModeEchange mode, final ModuleEnum composant, final String service, final String sens,
			final Boolean status, final Date from, final Date to);

	/**
	 * Recupère en fonction du composant et de l'erreur metier
	 *
	 * @param sens
	 * @param erreurMetier
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Page<T> findByErreurMetier(String sens, List<EnumErreursMetier> list, Date startDate, Date endDate);

	/**
	 * Recupère en fonction de l'erreur condition
	 *
	 * @param emission
	 * @param erreurConditionList
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Page<T> findByErreurCondition(List<EnumErrorCondition> erreurConditionList, Date startDate, Date endDate);

	/**
	 * Recupère en fonction de l'erreur condition et du service
	 *
	 * @param erreurConditionList
	 * @param erreurServiceList
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Page<T> findByErreurConditionAndService(List<EnumErrorCondition> erreurConditionList, List<EnumService> erreurServiceList,
			Date startDate, Date endDate);

	/**
	 * Recherche les logs par composant et sens
	 *
	 * @param mode
	 * @param list
	 * @param sens
	 * @param from
	 * @param to
	 * @return
	 */
	Page<T> findByComposantAndSens(ModeEchange mode, String module, String sens, Date from, Date to);

	/**
	 * Recupère les logs de perf
	 *
	 * @param composant
	 * @param from
	 * @param to
	 * @return
	 */
	Page<WSPerformanceEntity> find(String composant, final Date from, final Date to);

	/**
	 * Recupère les logs de la date from à la date to.
	 *
	 * @param from
	 * @param to
	 * @return
	 */
	Page<T> findEchange(Date from, Date to);

	/**
	 * rapport 4
	 *
	 * @param mode
	 * @param asList
	 * @param sens
	 * @param from
	 * @param to
	 * @param domaineSiriSm
	 * @return
	 */
	Page<T> findArretBySensAndClassName(String mode, List<ModuleEnum> asList, String sens, Date from, Date to, String domaineSiriSm);

	/**
	 * rappport 4
	 *
	 * @param mode
	 * @param asList
	 * @param sens
	 * @param from
	 * @param to
	 * @param domaineSiriEt
	 * @return
	 */
	Page<T> findLigneBySensAndClassName(String mode, List<ModuleEnum> asList, String sens, Date from, Date to, String domaineSiriEt);

}
