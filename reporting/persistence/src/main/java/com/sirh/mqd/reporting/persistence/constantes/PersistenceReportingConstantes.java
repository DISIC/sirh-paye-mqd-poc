package com.sirh.mqd.reporting.persistence.constantes;

/**
 * Constante du composant reporting persistence
 *
 * @author adile
 *
 */
public final class PersistenceReportingConstantes {

	/**
	 * Nom du business controller ayant l'acces à elasticsearch
	 */
	public static final String ELASTICSEARCH_BC = "elasticsearchBC";

	/**
	 * Nom du business controller ayant l'acces mail
	 */
	public static final String MAIL_BC = "mailBC";

	/**
	 * Valeur *void* par défaut qui apparaît dans elasticsearch
	 */
	public static final String NULL_VALUE = "-";

	//-----------------------------------------------------------------------------------------------------------------
	// Data pour les index logstash-metier : com.thalesgroup.stif.commons.echange.dto.logstash.AlertLogstashTamponDTO
	//-----------------------------------------------------------------------------------------------------------------

	/**
	 * colonne de l'erreur métier dans l'index logstash-metier
	 */
	public static final String CODE_ERREUR_IVTR = "code_erreur_ivtr";

	/**
	 * colonne du sens de communication (émission/réception) dans l'index logstash-metier
	 */
	public static final String SENS = "sens";

	/**
	 * colonne de date/timestamp dans l'index logstash-metier
	 */
	public static final String REQUEST_TIMESTAMP = "request_timestamp";

	/**
	 * colonne de date/timestamp dans les index logstash-ihm et logstash-performance
	 */
	public static final String TIMESTAMP = "@timestamp";

	/**
	 * colonne de libellé d'erreur dans l'index logstash-metier
	 */
	public static final String ERROR_CONDITION_STRUCTURE = "error_condition_structure";

	/**
	 * colonne de service Siri dans les index logstash-metier et logstash-performance
	 */
	public static final String SERVICE = "service";

	/**
	 * colonne de classe/package dans les index logstash-metier et logstash-performance. Permet d'identifier le composant (
	 * {@link com.sirh.mqd.commons.exchanges.enums.ModuleEnum}) si la valeur est passée en minuscule ({@link String#toLowerCase()}
	 */
	public static final String CLASSNAME = "classname";

	/**
	 * colonne de statut (booléen) Siri dans l'index logstash-metier
	 */
	public static final String STATUS = "status";

	/**
	 * colonne du mode d'échange Siri ({@link com.thalesgroup.stif.commons.echange.enums.ModeEchange} ) dans l'index logstash-metier
	 */
	public static final String MODE = "mode";

	/**
	 * colonne du domaine d'échange Siri ({@link com.thalesgroup.stif.commons.echange.enums.EnumService.getDomaine()}) dans l'index
	 * logstash-metier
	 */
	public static final String DOMAINE = "domaine";

	/**
	 * colonne de l'objet à monitorer (exemple : "STIF:StopPoint:Q:29066:") dans l'index logstash-metier
	 */
	public static final String MONITORING_REF = "monitoring_ref";

	/**
	 * colonne de l'émetteur de la requête dans l'index logstash-metier
	 */
	public static final String REQUESTOR_REF = "requestor_ref";

	/**
	 * colonne du composant technique {@link com.sirh.mqd.commons.exchanges.enums.ModuleEnum} dans l'index logstash-performance
	 */
	public static final String COMPOSANT = "composant";

	/**
	 * colonne du code de retour (success/error) {@link com.sirh.mqd.commons.traces.enums.EnumActionNature} dans l'index
	 * logstash-ihm
	 */
	public static final String ACTION_NATURE = "action_nature";

	/**
	 * Pattern de séparation pour les adresses email
	 */
	public static String SEPARATOR_PATTERN = ";";

}
