package com.sirh.mqd.reporting.webapp.model.comparator;

import java.util.Comparator;

import com.sirh.mqd.reporting.webapp.model.DossierModel;

public class DossierModelAlerteComparator implements Comparator<DossierModel> {

	@Override
	public int compare(final DossierModel o1, final DossierModel o2) {
		final Integer valeur1 = o1.getNbAlertes();
		final Integer valeur2 = o2.getNbAlertes();

		return valeur2.compareTo(valeur1);
	}
}