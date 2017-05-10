package com.sirh.mqd.supplier.routing.rest;

/**
 * Bean permettant la réponse à un GET restfull d'une demande de log d'echange
 * http://localhost:8480/reporting/log/echange
 */
/*
 * @ControllerAdvice
 * 
 * @RestController
 */
public class ExportBean {

	/**
	 * Service de Vérification de l'Etat du Système
	 */
	/*
	 * @Autowired
	 *
	 * @Qualifier(CoreConstantes.EXPORT_SERVICE) private ExportService
	 * exportService;
	 */

	/*
	 * @RequestMapping("/echange/{from}") public String
	 * getTextFile(@RequestParam("from") final Date from) { return
	 * exportService.getTextFile(from,
	 * DateUtils.getCalendarInstance().getTime()); }
	 */

	/*
	 * @RequestMapping("/echange") public String getTextFile() {
	 *
	 * final Calendar calendar = new GregorianCalendar();
	 * calendar.add(Calendar.DAY_OF_YEAR, -7); return
	 * exportService.getTextFile(null, null);
	 *
	 * }
	 */

	/*
	 * @RequestMapping("/echange/{from}/{to}") public String
	 * getTextFile(@RequestParam("from") final Date from, @RequestParam("to")
	 * final Date to) {
	 *
	 * return exportService.getTextFile(from, to);
	 *
	 * }
	 */
}
