package com.sirh.mqd.reporting.core.statut_dossier;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.statut_dossier.StatutDossierDTO;
import com.sirh.mqd.commons.storage.bc.StatutDossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.IStatutDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.STATUT_DOSSIER_SERVICE)
public class StatutDossierService implements IStatutDossierService {

	@Autowired
	@Qualifier(PersistenceConstantes.STATUT_DOSSIER_BC)
	private StatutDossierBC statutDossierBC;

	@Override
	public void modifierStatutDossier(final StatutDossierDTO statutDossier) {
		statutDossierBC.modifierStatutDossier(statutDossier);
	}

	@Override
	public Optional<StatutDossierDTO> rechercherStatutDossier(final String renoiRHMatricule, final String payLot) {
		return statutDossierBC.rechercherStatutDossier(renoiRHMatricule, payLot);
	}
}
