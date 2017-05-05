package com.sirh.mqd.reporting.core.generation;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.grp;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.AxisFormatBuilder;
import net.sf.dynamicreports.report.builder.chart.XyBarChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.dynamicreports.report.definition.chart.DRIChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.sirh.mqd.commons.exchanges.enums.ModeEchange;
import com.sirh.mqd.commons.exchanges.enums.ModuleEnum;
import com.sirh.mqd.commons.exchanges.enums.TypeObjetReflex;
import com.sirh.mqd.commons.exchanges.exception.ApplicationException;
import com.sirh.mqd.commons.exchanges.exception.InvalidDataReferenceException;
import com.sirh.mqd.commons.storage.bc.ReflexTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.traces.api.IFacadeLogs;
import com.sirh.mqd.commons.traces.enums.LogType;
import com.sirh.mqd.commons.traces.logs.FacadeLogFactory;
import com.sirh.mqd.commons.traces.logs.LogTechniqueFactory;
import com.sirh.mqd.reporting.core.enums.ReferenceRapport;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;
import com.thalesgroup.stif.commons.echange.dto.reflex.ReflexTamponDTO;

/**
 * Classe gérant la création du dashboard 10
 *
 * @see doc.story.ref1665
 *
 */
@Component
public class Rapport10 extends AbstractRapport {

	@Autowired
	@Qualifier(PersistenceConstantes.REFLEX_TAMPON_BC)
	private ReflexTamponBC reflexTamponBC;

	private final String TITLE = "Relais IVTR - Taux de demande des objets du référentiel REFLEX";

	private final String FOOTER_LIBELLE = "Relais IVTR - Taux de demande des objets du référentiel REFLEX";

	private final String VALUE_AXIS_TITLE = "Nombre d'arrêts";

	private final String numberPattern = "#########";

	/**
	 *
	 * La facade de log de l'application
	 */
	private static final IFacadeLogs LOG = FacadeLogFactory.getLogger(Rapport10.class);

	public Rapport10() {

	}

	@Override
	protected void buildRapport(final JasperReportBuilder report) {

		final TextColumnBuilder<Integer> nbDiffuseur = col.column("Nombre de diffuseur", "nbDiffuseur", type.integerType());
		final TextColumnBuilder<Integer> nbArret = col.column("Nombre d'arrêts", "nbArret", type.integerType());
		final TextColumnBuilder<String> granularite = col.column("Granularite", "granularite", type.stringType());
		final TextColumnBuilder<String> mode = col.column("Mode", "mode", type.stringType());

		final XyBarChartBuilder lineChart = cht.xyBarChart();
		lineChart.setSubtitle("");
		lineChart.setTitleFont(boldFont);
		lineChart.setXValue(nbDiffuseur);
		lineChart.series(cht.xySerie(nbArret));

		final AxisFormatBuilder axisFormat = cht.axisFormat();
		axisFormat.setLabel("Nombre de diffuseurs");
		lineChart.setXAxisFormat(axisFormat);

		lineChart.setYAxisFormat(cht.axisFormat().setLabel(VALUE_AXIS_TITLE));

		//		lineChart.setShowShapes(true);
		lineChart.setShowValues(true);
		lineChart.setValuePattern(numberPattern);

		final DRIChartCustomizer customizers = new DRIChartCustomizer() {

			@Override
			public void customize(final JFreeChart chart, final ReportParameters reportParameters) {
				final ValueAxis axis = chart.getXYPlot().getDomainAxis();
				final TickUnits tickUnits = new TickUnits();
				tickUnits.add(new NumberTickUnit(1));
				axis.setStandardTickUnits(tickUnits);

			}
		};
		lineChart.customizers(customizers);

		report.setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

		report.summary();

		report.sortBy(granularite, mode);

		report.groupBy(grp.group(granularite), grp.group(mode).footer(lineChart));

		report.setDataSource(dataSource);

	}

	@Override
	protected void createDataSourceRapport() {

		dataSource = new DRDataSource("granularite", "mode", "nbArret", "nbDiffuseur");

		try {
			//			int totalReflex = reflexTamponBC.rechercherToutObjetReflex().size();

			final List<ReflexTamponDTO> gdl = new ArrayList<ReflexTamponDTO>();
			final List<ReflexTamponDTO> lda = new ArrayList<ReflexTamponDTO>();
			final List<ReflexTamponDTO> zde = new ArrayList<ReflexTamponDTO>();
			final List<ReflexTamponDTO> zdl = new ArrayList<ReflexTamponDTO>();

			// on split tout reflex par granularité
			for (final ReflexTamponDTO reflex : reflexTamponBC.rechercherToutObjetReflex()) {

				if (reflex != null) {
					switch (reflex.getTypeObjetReflex()) {
						case GDL:
							gdl.add(reflex);
							break;
						case LDA:
							lda.add(reflex);
							break;
						case ZDE:
							zde.add(reflex);
							break;
						case ZDL:
							zdl.add(reflex);
							break;
					}
				}
			}
			int i = 0;
			for (final TypeObjetReflex reflexType : TypeObjetReflex.values()) {

				final String nomReflex = ++i + "-" + reflexType.name();

				int totalReflex = 0;

				switch (reflexType) {
					case GDL:
						totalReflex = gdl.size();
						break;
					case LDA:
						totalReflex = lda.size();
						break;
					case ZDE:
						totalReflex = zde.size();
						break;
					case ZDL:
						totalReflex = zdl.size();
						break;
				}

				final List<String> modules = new ArrayList<String>();
				modules.add(ModuleEnum.EMISSION.name());
				modules.add("administration");

				for (final String module : modules) {

					final Page<WSGenericMetierEntity> data = elasticsearchBC.findByComposantAndSens(ModeEchange.REQUETE, module,
							"Sens_reception", startDate, endDate);

					if (data == null) {
						continue;
					}

					String type;

					if (module.equalsIgnoreCase(ModuleEnum.EMISSION.name())) {
						type = "REQUETE";
					} else {
						type = "ABONNEMENT";
					}

					final Map<Integer, Integer> diffuseurs = processData(data, reflexType);

					int totalUsedStopPoint = 0;

					for (final Entry<Integer, Integer> nbDiffuseur : diffuseurs.entrySet()) {

						dataSource.add(nomReflex, type, nbDiffuseur.getValue(), nbDiffuseur.getKey());

						totalUsedStopPoint += nbDiffuseur.getValue();
					}

					// on set la valeur pour 0 diffuseur

					int diffuseur0 = 0;

					if (totalReflex > totalUsedStopPoint) {
						diffuseur0 = totalReflex - totalUsedStopPoint;
					}

					dataSource.add(nomReflex, type, diffuseur0, 0);

				}

			}

		} catch (final ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Traite les données ES pour obtenir une datasource simple
	 *
	 * @param data
	 * @return une liste ordonnée du nombre de diffuseur
	 * @throws ApplicationException
	 */
	private Map<Integer, Integer> processData(final Page<WSGenericMetierEntity> data, final TypeObjetReflex reflexType)
			throws ApplicationException {

		final Map<String, List<String>> rawData = new HashMap<String, List<String>>();

		final Iterator<WSGenericMetierEntity> iterator = data.iterator();

		// on rempli notre map de données brut ( Arret -> list de diffuseur)
		while (iterator.hasNext()) {
			final WSGenericMetierEntity echange = iterator.next();

			if ("null".equals(echange.getMonitoring_ref()) || "null".equals(echange.getRequestor_ref())) {
				continue;
			}
			// On recupere l'objet reflex dans le tampon
			try {
				final ReflexTamponDTO reflexOject = reflexTamponBC.rechercherObjetReflex(echange.getMonitoring_ref());

				// on ne prend que la granulatiré qui nous interesse
				if (!reflexOject.getTypeObjetReflex().equals(reflexType)) {
					continue;
				}
			} catch (final InvalidDataReferenceException erreurObjetReflex) {
				// objet reflex inconnu en base ou nom mal formé (pattern non respecté).
				LOG.logTechniqueWarn(LogTechniqueFactory.getLogTechnique(new Date(), LogType.OTHER, Rapport10.class.getSimpleName(),
						"Erreur dans l'objet Reflex " + echange.getMonitoring_ref() + " lors de la génération du Rapport 10",
						erreurObjetReflex.getMessage()));
				continue;
			}

			// si c'est un monitoringRef deja connu alors on ajoute le diffuseur si celui si n'est pas deja presente
			if (rawData.containsKey(echange.getMonitoring_ref())) {

				final List<String> diffuseurs = rawData.get(echange.getMonitoring_ref());

				// on ajoute le diffuseur s'il n'existe pas dans la liste des diffuseurs
				if (!diffuseurs.contains(echange.getProducer_ref())) {

					diffuseurs.add(echange.getProducer_ref());
				}

			} else {

				// On ajoute l'arret qui n'existe pas et on crée la liste des diffuseur
				final List<String> diffuseurs = new ArrayList<>();
				diffuseurs.add(echange.getProducer_ref());
				rawData.put(echange.getMonitoring_ref(), diffuseurs);
			}

		}

		// On créer une liste contenant le nombre de diffuseur
		final List<Integer> diffuseursCount = new ArrayList<Integer>();

		for (final Entry<String, List<String>> entry : rawData.entrySet()) {

			diffuseursCount.add(entry.getValue().size());

		}

		Collections.sort(diffuseursCount);

		final Map<Integer, Integer> finalData = new HashMap<Integer, Integer>();

		for (final Integer integer : diffuseursCount) {

			// on compte le nombre d'arrêt  ayant le même nombre de diffuseur
			if (finalData.containsKey(integer)) {
				int i = finalData.get(integer);
				i = i + 1;
				finalData.put(integer, i);
			} else {
				finalData.put(integer, 1);
			}

		}

		return finalData;

	}

	@Override
	public String getRapportTitle() {
		return TITLE;
	}

	@Override
	public String getRapportFooterLibelle() {
		return FOOTER_LIBELLE;
	}

	@Override
	public String getReference() {
		// TODO Auto-generated method stub
		return ReferenceRapport.RAPPORT_10.name();
	}

}
