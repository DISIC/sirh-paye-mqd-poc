package com.thalesgroup.stif.log.analyser.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repartition du temps du service
 * 
 * @author adile
 * 
 */
public class RepartitionTime {

	public final static int TIME_500MS = 500;

	public final static int TIME_1000MS = 1000;

	public final static int TIME_1500MS = 1500;

	public final static int TIME_2000MS = 2000;

	public final static int TIME_2500MS = 2500;

	public final static int TIME_3000MS = 3000;

	public final static int TIME_5000MS = 5000;

	public final static int TIME_5000PLUSMS = 9999;

	public static Map<Integer, Integer> repartitionTable(final List<Integer> content) {

		int cas1 = 0;
		int cas2 = 0;
		int cas3 = 0;
		int cas4 = 0;
		int cas5 = 0;
		int cas6 = 0;
		int cas7 = 0;
		int cas8 = 0;

		Map<Integer, Integer> table = new HashMap<Integer, Integer>();

		for (Integer time : content) {

			if (time <= TIME_500MS) {
				cas1++;
			} else if (TIME_500MS < time && time <= TIME_1000MS) {
				cas2++;
			} else if (TIME_1000MS < time && time <= TIME_1500MS) {
				cas3++;
			} else if (TIME_1500MS < time && time <= TIME_2000MS) {
				cas4++;
			} else if (TIME_2000MS < time && time <= TIME_2500MS) {
				cas5++;
			} else if (TIME_2500MS < time && time <= TIME_3000MS) {
				cas6++;
			} else if (TIME_3000MS < time && time <= TIME_5000MS) {
				cas7++;
			} else {
				cas8++;
			}

		}

		table.put(TIME_500MS, cas1);
		table.put(TIME_1000MS, cas2);
		table.put(TIME_1500MS, cas3);
		table.put(TIME_2000MS, cas4);
		table.put(TIME_2500MS, cas5);
		table.put(TIME_3000MS, cas6);
		table.put(TIME_5000MS, cas7);
		table.put(TIME_5000PLUSMS, cas8);

		return table;

	}

	public static double mean(final List<Integer> content) {
		double sum = 0;
		for (Integer elem : content) {
			sum += elem;
		}

		return sum / content.size();
	}

	public static double median(final List<Integer> content) {

		int middle = content.size() / 2;
		if (content.size() % 2 == 1) {
			return content.get(middle);
		} else {
			return (content.get(middle - 1) + content.get(middle)) / 2.0;
		}
	}

	public static int percentileValue(final List<Integer> content) {

		int size = content.size();
		int indexValue = (int) (size * 0.99);

		return content.get(indexValue);
	}

}
