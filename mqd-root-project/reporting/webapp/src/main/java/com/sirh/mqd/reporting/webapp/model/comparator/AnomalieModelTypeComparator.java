package com.sirh.mqd.reporting.webapp.model.comparator;

import java.util.Comparator;

import com.sirh.mqd.reporting.webapp.model.AnomalieModel;

public class AnomalieModelTypeComparator implements Comparator<AnomalieModel> {

	@Override
	public int compare(final AnomalieModel o1, final AnomalieModel o2) {
		return o1.getType().compareTo(o2.getType());
	}
}
