package com.sirh.mqd.reporting.core.dossier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.exchanges.enums.AlerteEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.bc.DossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.utils.DateUtils;
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
		final List<AlerteDTO> alertes = new ArrayList<AlerteDTO>();

		final AlerteDTO alerte1 = new AlerteDTO();
		alerte1.setMatricule(matricule);
		alerte1.setPayLot(payLot);
		alerte1.setDateEcheance(DateUtils.getCalendarInstance().getTime());
		alerte1.setDonnee("78500");
		alerte1.setType(AnomalieTypeEnum.ADRESSE_CODE_PAYS);
		alerte1.setEtatCorrection(AlerteEtatEnum.A_TRAITER);
		alertes.add(alerte1);

		final AlerteDTO alerte2 = new AlerteDTO();
		alerte2.setMatricule(matricule);
		alerte2.setPayLot(payLot);
		alerte2.setDateEcheance(DateUtils.getCalendarInstance().getTime());
		alerte2.setDonnee("14/02/1984");
		alerte2.setType(AnomalieTypeEnum.ETAT_CIVIL_DATE_NAISSANCE);
		alerte2.setEtatCorrection(AlerteEtatEnum.DEMANDE_ASSISTANCE);
		alerte2.setResponsableLogin("atingaud");
		alerte2.setResponsableNom("TINGAUD");
		alerte2.setResponsablePrenom("Alexandre");
		alerte2.setDateModification(DateUtils.parseDateJJMMAAAAhhmmss("03/07/2017 10:15:27"));
		alertes.add(alerte2);

		return alertes;
	}

	@Override
	public void modifierAlerte(final AlerteDTO alerte) {
		// TODO Implémenter la couche BC
	}
}
