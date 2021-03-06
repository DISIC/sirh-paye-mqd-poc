package com.sirh.mqd.reporting.webapp.model.comparator;

import java.util.Comparator;

import com.sirh.mqd.reporting.webapp.model.AlerteModel;

public class AlerteModelTypeComparator implements Comparator<AlerteModel> {

	@Override
	public int compare(final AlerteModel o1, final AlerteModel o2) {
		return o1.getType().compareTo(o2.getType());
	}
}
