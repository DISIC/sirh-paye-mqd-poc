package com.sirh.mqd.reporting.core.dossier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.constante.AlerteCouleurSeuilsConstantes;
import com.sirh.mqd.commons.exchanges.constante.AnomalieCouleurSeuilsConstantes;
import com.sirh.mqd.commons.exchanges.constante.SystemConstantes;
import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.storage.bc.ConfigBC;
import com.sirh.mqd.commons.storage.bc.DossierBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.DOSSIER_SERVICE)
public class DossierService implements IDossierService {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_BC)
	private DossierBC dossierBC;

	@Autowired
	@Qualifier(PersistenceConstantes.CONFIG_BC)
	private ConfigBC configBC;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> rechercherCouleurSeuilsAlerte() {
		final Map<String, Integer> couleurSeuilsAlertes = new HashMap<String, Integer>();
		couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.ROUGE, 8);
		couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.ORANGE, 5);
		couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.JAUNE, 1);
		couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.VERT, 0);
		final Optional<Object> seuilsAlertes = configBC.rechercherConfig(SystemConstantes.COULEUR_SEUILS_ALERTE);
		if (seuilsAlertes.isPresent()) {
			final Object valeur = seuilsAlertes.get();
			if (valeur instanceof Map) {
				final Map<String, Integer> mapSeuilsAlertes = (HashMap<String, Integer>) valeur;
				if (mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ROUGE) != null) {
					couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.ROUGE,
							mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ROUGE));
				}
				if (mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ORANGE) != null) {
					couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.ORANGE,
							mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.ORANGE));
				}
				if (mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.JAUNE) != null) {
					couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.JAUNE,
							mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.JAUNE));
				}
				if (mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.VERT) != null) {
					couleurSeuilsAlertes.put(AlerteCouleurSeuilsConstantes.VERT,
							mapSeuilsAlertes.get(AlerteCouleurSeuilsConstantes.VERT));
				}
			}
		}
		return couleurSeuilsAlertes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> rechercherCouleurSeuilsAnomalie() {
		final Map<String, Integer> couleurSeuilsAnomalies = new HashMap<String, Integer>();
		couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.ROUGE, 8);
		couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.ORANGE, 5);
		couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.JAUNE, 1);
		couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.VERT, 0);
		final Optional<Object> seuilsAnomalies = configBC.rechercherConfig(SystemConstantes.COULEUR_SEUILS_ANOMALIE);
		if (seuilsAnomalies.isPresent()) {
			final Object valeur = seuilsAnomalies.get();
			if (valeur instanceof Map) {
				final Map<String, Integer> mapSeuilsAnomalies = (HashMap<String, Integer>) valeur;
				if (mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ROUGE) != null) {
					couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.ROUGE,
							mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ROUGE));
				}
				if (mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ORANGE) != null) {
					couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.ORANGE,
							mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.ORANGE));
				}
				if (mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.JAUNE) != null) {
					couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.JAUNE,
							mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.JAUNE));
				}
				if (mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.VERT) != null) {
					couleurSeuilsAnomalies.put(AnomalieCouleurSeuilsConstantes.VERT,
							mapSeuilsAnomalies.get(AnomalieCouleurSeuilsConstantes.VERT));
				}
			}
		}
		return couleurSeuilsAnomalies;
	}

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
