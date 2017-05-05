package com.sirh.mqd.reporting.core.generation;

import net.sf.dynamicreports.report.constant.TimePeriod;

import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe gérant la création du dashboard 9
 *
 * @see doc.story.ref1653
 *
 */
@Component
public class Rapport9 extends Rapport1 {

	public Rapport9() {
		super();
		TITLE = "Volumétrie en Ko";
		FOOTER_LIBELLE = "Rapport volumétrie";
		VALUE_AXIS_TITLE = "Poids (Ko)";
		timePeriod = TimePeriod.HOUR;
		SHOW_SHAPES = true;
		NULL_DATA_SWITCH = false;
	}

	/**
	 * on ajoute des données à {@link AbstractRapport#dataSource} en lui passant plusieurs paramètres correspondant aux colonnes
	 * prédéfinies. Cette méthode renvoit un Array, plus facile à générer à la place des nombreux paramètres. Et surtout facile à Override,
	 * par rapport à la méthode définie dans rapport 1 {@link Rapport1#buildObjectArrayForDataSource(WSGenericMetierEntity, String)}<br/>
	 * STIF-344
	 *
	 * @param echange
	 *            l'entité de la donnée. utilisée en fait pour la date et la taille de l'échange (Ko)
	 * @param exchangeType
	 *            le type d'echange, dont l'ensemble des données pour ce type forme au final un graphe
	 * @return the array of objects to pass into {@link AbstractRapport#dataSource}, "non-useful" data included (week/date)
	 */
	@Override
	protected Object[] buildObjectArrayForDataSource(final WSGenericMetierEntity echange, final String exchangeType) {
		return new Object[] {
				//col 1 : week
				String.valueOf("Semaine " + DateUtils.toWeekOfYear(echange.getLog_date())),
				//col 2 : COUNT_COLUMN, pas en nombre de messages par rapport au Rapport 1, mais en espace disque occupé
				echange.getTaille(),
				//col 3 : EXCHANGE_TYPE
				exchangeType,
				//col 4 : date
				DateUtils.toDate(echange.getLog_date()) };
	}

	@Override
	public String getReference() {
		return ReferenceRapport.RAPPORT_9.name();
	}

}
