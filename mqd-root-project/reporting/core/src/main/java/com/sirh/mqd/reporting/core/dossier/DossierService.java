package com.sirh.mqd.reporting.core.dossier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.storage.bc.DossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.DOSSIER_SERVICE)
public class DossierService implements IDossierService {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_BC)
	private DossierBC dossierBC;

	@Override
	public List<DossierDTO> listerDossiers(final String payLot, final String corpsCode, final String affectationCode,
			final String gestionnaireCode) {
		return dossierBC.listerDossiers(payLot, corpsCode, affectationCode, gestionnaireCode);
	}

	@Override
	public List<ComparaisonDTO> listerAnomalies(final String matricule, final String payLot) {
		return dossierBC.listerAnomalies(payLot, matricule);
	}

	@Override
	public void modifierAnomalie(final ComparaisonDTO anomalie) {
		dossierBC.modifierAnomalie(anomalie);
	}

	@Override
	public List<AlerteDTO> listerAlertes(final String matricule, final String payLot) {
		return dossierBC.listerAlertes(payLot, matricule);
	}

	@Override
	public void modifierAlerte(final AlerteDTO alerte) {
		dossierBC.modifierAlerte(alerte);
	}

}
