package com.thalesgroup.stif.log.analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thalesgroup.stif.log.analyser.core.RepartitionTime;

public class Analyser {

	private static String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";

	class Logger {

		PrintStream log;

		Logger(final File outputfile) throws FileNotFoundException {
			log = new PrintStream(new FileOutputStream(outputfile), true);
		}

		public void close() {
			if (log != null)
				log.close();

		}

		public void println(final String string) {
			log.println(string);
			System.out.println(string);

		}

		public void println() {
			log.println();
			System.out.println();
		}

		public void println(final String string, final String value) {
			log.printf("%28s %-10s\n", string, value);
		}

		public void println(final String string, final int value) {
			log.printf("%28s %10d\n", string, value);
		}

		public void println(final String string, final int value, final String v) {
			log.printf("%28s %10d %s\n", string, value, v);
		}

		public void println(final String string, final Double value, final String v) {
			log.printf("%28s %10.4f %s\n", string, value.floatValue(), v);
		}

	}

	class GlobalData {

		Map<String, Map<String, Integer>> times;

		int errors;

		int echantillons;

		int nbFiles;

		Date dateDebut;

		Date dateFin;

		GlobalData() {
			times = new HashMap<String, Map<String, Integer>>();
		}

		public Map<String, Map<String, Integer>> getTimes() {
			return times;
		}

		public void addTime(final String key, final Integer time, final String ref) {
			Map<String, Integer> list = times.get(key);
			if (list == null) {
				list = new HashMap<String, Integer>();
				times.put(key, list);
			}
			if (list.containsKey(ref)) {
				list.put(ref, list.get(key) + time);
			} else {
				list.put(ref, time);
			}
		}

		public void addDate(final Date date) throws ParseException {

			if (dateDebut == null || date.before(dateDebut))
				dateDebut = date;
			if (dateFin == null || date.after(dateFin))
				dateFin = date;
		}

		public Date getDateFin() {
			return dateFin;
		}

		public Date getDateDebut() {
			return dateDebut;
		}

		public void putAll(final GlobalData value) {
			if (dateDebut == null || (value.dateDebut != null && value.dateDebut.before(dateDebut)))
				dateDebut = value.dateDebut;
			if (dateFin == null || (value.dateFin != null && value.dateFin.after(dateFin)))
				dateFin = value.dateFin;
			for (Entry<String, Map<String, Integer>> entry : value.times.entrySet()) {
				Map<String, Integer> list = times.get(entry.getKey());
				if (list == null) {
					list = new HashMap<String, Integer>();
					times.put(entry.getKey(), list);
				}
				list.putAll(entry.getValue());
			}
			errors += value.errors;
			echantillons += value.echantillons;
			nbFiles += value.nbFiles;
		}

		public void incError() {
			errors++;
		}

		public int getErrors() {
			return errors;
		}

		public void incEchantillon() {
			echantillons++;

		}

		public int getEchantillons() {
			return echantillons;
		}

		public void incNbFiles() {
			nbFiles++;
		}

		public int getNbFiles() {
			return nbFiles;
		}
	}

	File workDir;

	Logger out;

	Analyser(final File workDir, final String service) throws FileNotFoundException {

		this.workDir = workDir;
		File outputfile = new File(workDir, "consolidation.txt");
		if (service != null)
			outputfile = new File(workDir, service + "-consolidation.txt");

		out = new Logger(outputfile);
	}

	Analyser(final File workDir) throws FileNotFoundException {
		new Analyser(workDir, null);
	}

	public static void help() {
		System.out.println("Programe d'analyse des performances");
		System.out.println("arguments :");
		System.out.println(" logDir=[nom du répertoire des log]");
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {

		String logDir = null;
		String service = null;

		long start = GregorianCalendar.getInstance().getTimeInMillis();

		for (String arg : args) {
			System.out.println(arg);
			String[] split = arg.split("=");
			if (split.length == 2) {
				if ("logDir".equals(split[0].trim())) {
					logDir = split[1].trim();
					break;
				}
				if ("service".equals(split[0].trim())) {
					service = split[1].trim();
					break;
				}
			}
		}
		if (logDir == null) {
			help();
			System.exit(1);
		} else {
			File dir = new File(logDir);
			if (dir.isDirectory()) {

				Analyser analyser = null;
				try {
					analyser = new Analyser(dir, service);
					GlobalData g = analyser.analyseDir(dir, 0, service);

					long duration = GregorianCalendar.getInstance().getTimeInMillis() - start;
					System.out.println("        Temps de l'analyse : " + durationToString(duration));
					System.out.println("Nombre de fichiers traités : " + g.getNbFiles());

				} finally {
					if (analyser != null) {
						analyser.close();
					}
				}
			}

		}

	}

	private void close() {
		if (out != null)
			out.close();
	}

	public GlobalData analyseDir(final File dir, final int depth, final String service) throws Exception {

		GlobalData globalData = new GlobalData();
		File[] listFile = dir.listFiles(new FileFilter() {

			public boolean accept(final File pathname) {
				return pathname.isDirectory() || pathname.getName().endsWith(".log");
			}
		});
		if (listFile != null) {
			Arrays.sort(listFile);
		}

		for (File file : listFile) {
			if (file.isDirectory()) {
				GlobalData value = analyseDir(file, depth + 1, service);
				globalData.putAll(value);
			} else {
				analyseFile(globalData, file);
			}
		}
		if (depth <= 1) {
			out.println();
			out.println("*******************************************************************************");
			out.println("* Analyse du répertoire : " + dir.getName());
			out.println("*******************************************************************************");
			analyseStatistics(globalData);
			out.println();
		}

		return globalData;
	}

	static String durationToString(long duration) {
		StringBuilder sb = new StringBuilder();

		duration = TimeUnit.MILLISECONDS.toSeconds(duration);
		long sec = duration % 60;

		duration = TimeUnit.SECONDS.toMinutes(duration);
		long min = duration % 60;

		duration = TimeUnit.MINUTES.toHours(duration);
		long hours = duration % 24;

		duration = TimeUnit.HOURS.toDays(duration);
		long days = duration;

		if (days > 0) {
			sb.append(days).append(" jour");
			if (days > 1)
				sb.append("s");
		}
		if (hours > 0) {
			sb.append(" ");
			sb.append(hours).append(" heure");
			if (hours > 1)
				sb.append("s");
		}
		if (min > 0) {
			sb.append(" ");
			sb.append(min).append(" minute");
			if (min > 1)
				sb.append("s");
		}
		if (sec > 0) {
			sb.append(" ");
			sb.append(sec).append(" seconde");
			if (sec > 1)
				sb.append("s");
		}
		return sb.toString().trim();

	}

	/**
	 * @param fileContent
	 */
	public void analyseStatistics(final GlobalData fileContent) {

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		Date dateDebut = fileContent.getDateDebut();
		Date dateFin = fileContent.getDateFin();
		if (dateDebut != null && dateFin != null) {
			long duration = dateFin.getTime() - dateDebut.getTime();

			out.println();
			out.println("Consolidation générée le", sdf.format(new Date()));
			out.println("Période des statistiques", "du " + sdf.format(dateDebut) + " au " + sdf.format(dateFin) + " ("
					+ durationToString(duration) + ") ");

			for (Entry<String, Map<String, Integer>> entry : fileContent.getTimes().entrySet()) {
				out.println();
				out.println("Statistique pour le service", entry.getKey());

				List<Integer> content = new ArrayList<Integer>(entry.getValue().values());

				Collections.sort(content, new Comparator<Integer>() {

					public int compare(final Integer o1, final Integer o2) {
						return o1.intValue() - o2.intValue();
					}
				});

				Map<Integer, Integer> repartition = RepartitionTime.repartitionTable(content);

				out.println();
				out.println("*********** Répartition des temps de traitements ***********");
				out.println("nombre d'échantillons", content.size());
				out.println("moyennes", RepartitionTime.mean(content), "ms");
				out.println("median", RepartitionTime.median(content), "ms");
				out.println("min", content.get(0), "ms");
				out.println("max", content.get(content.size() - 1), "ms");
				out.println("t < 500 ms", repartition.get(RepartitionTime.TIME_500MS));
				out.println("500 ms < t < 1000 ms", repartition.get(RepartitionTime.TIME_1000MS));
				out.println("1000 ms < t < 1500 ms", repartition.get(RepartitionTime.TIME_1500MS));
				out.println("1500 ms < t < 2000 ms", repartition.get(RepartitionTime.TIME_2000MS));
				out.println("2000 ms < t < 2500 ms", repartition.get(RepartitionTime.TIME_2500MS));
				out.println("2500 ms < t < 3000 ms", repartition.get(RepartitionTime.TIME_3000MS));
				out.println("3000 ms < t < 5000 ms", repartition.get(RepartitionTime.TIME_5000MS));
				out.println("5000 ms < t < 5000 ms", repartition.get(RepartitionTime.TIME_5000PLUSMS));
				out.println("99%", RepartitionTime.percentileValue(content), "ms");

			}
			out.println();
			out.println("nombre total d'échantillons", fileContent.getEchantillons());
			out.println("nombre de lignes en erreur", fileContent.getErrors());
		}
	}

	/**
	 * Parseur d'une ligne de log<br>
	 * Exemple d'une ligne :
	 * 
	 * <pre>
	 * 2015-03-09 15:42:24,489 [INFO] c.t.stif.log.system.performance - [Service][notifyStopMonitoring][719]
	 * </pre>
	 * 
	 * @author gerard
	 * 
	 */
	static class LineParser {

		Date date;

		String group;

		String intValue;

		String reference;

		String serviceName;

		static String PARSE_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss.SSS";

		SimpleDateFormat sdf = new SimpleDateFormat(PARSE_DATE_PATTERN);

		Pattern p = Pattern.compile("\\[(.*?)\\]");

		/**
		 * Variante avec regexp
		 * 
		 * @param line
		 * @throws ParseException
		 */
		private void parseLineX(final String line) throws ParseException {

			Matcher m = p.matcher(line);

			String splittedValues[] = new String[10];
			int index = 0;
			while (m.find()) {
				splittedValues[index] = m.group(1).trim();
				index++;
			}

			if (index != 4) {
				throw new ArrayIndexOutOfBoundsException("Bad line, nb token=" + index);
			}

			String strDate = line.substring(0, line.indexOf(','));
			if (strDate.length() != PARSE_DATE_PATTERN.length()) {
				throw new ParseException("Bad date " + strDate, 0);
			}

			date = sdf.parse(strDate);

			group = splittedValues[2];
			intValue = splittedValues[3].trim();

		}

		/**
		 * Variante avec StringTokenizer
		 * 
		 * @param line
		 * @throws ParseException
		 */
		private void parseLine(final String line) throws ParseException {
			StringTokenizer st = new StringTokenizer(line, ",[]");
			int index = 0;
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				token = token.trim();
				switch (index) {
					case 0:
						if (token.length() != PARSE_DATE_PATTERN.length()) {
							throw new ParseException("Bad date " + token, 0);
						}

						date = sdf.parse(token);

						break;
					case 4:
						group = token;
						break;
					case 5:
						intValue = token;
						break;
					case 6:
						if (token == null || token.contentEquals("null"))
							reference = null;
						else
							reference = token;
					default:
						break;
				}
				index++;

			}

			if (index != 7) {
				throw new ArrayIndexOutOfBoundsException("Bad line, nb token=" + index);
			}

		}

		public Date getDate() {
			return date;
		}

		public String getGroup() {
			return group;
		}

		public String getIntValue() {
			return intValue;
		}

		public String getService() {
			return serviceName;
		}

		public String getRef() {
			return reference;
		}

	}

	public GlobalData analyseFile(final GlobalData globalData, final File filePath) throws Exception {

		if (!filePath.isFile()) {
			throw new Exception("bad file path");
		}

		globalData.incNbFiles();

		String relativeFileName = filePath.getAbsolutePath().substring(workDir.getAbsolutePath().length() + 1);

		System.out.println("===== " + relativeFileName);

		FileInputStream fis = new FileInputStream(filePath);

		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String strLine;

		int lineNum = 0;
		LineParser lineParser = new LineParser();
		while ((strLine = br.readLine()) != null) {

			lineNum++;

			try {

				lineParser.parseLine(strLine);
				if (lineParser.getRef() != null) {
					globalData.addDate(lineParser.getDate());
					globalData.addTime(lineParser.getGroup(), Integer.valueOf(lineParser.getIntValue()), lineParser.getRef());

					globalData.incEchantillon();
				}

			} catch (Exception e) {
				System.err.println("Error log file : " + relativeFileName + " at line : " + lineNum + " [ " + strLine + " ] "
						+ e.getClass().getName() + " : " + e.getMessage());
				globalData.incError();

			}

		}

		br.close();
		fis.close();

		return globalData;

	}
}
