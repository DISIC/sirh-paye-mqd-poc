package com.sirh.mqd.reporting.core.dossier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DifferenceDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.exchanges.enums.AnomalieEtatEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomaliePerimetreEnum;
import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.DOSSIER_SERVICE)
public class DossierService implements IDossierService {

	@Override
	public List<DossierDTO> listerDossiers(final String userId) {
		final List<DossierDTO> dossiers = new ArrayList<DossierDTO>();

		final DossierDTO dossier1 = new DossierDTO();

		dossier1.setMinistere(256);
		dossier1.setPayCle(161027503500227L);
		dossier1.setDossierNumero(20);
		dossier1.setDiGestionnaire(20);
		dossier1.setAdminCode("E10");
		dossier1.setAdminCodeDepartement(075);
		dossier1.setPayLot("011B-ANT-AC-001");
		dossier1.setPayCivilite(1);
		dossier1.setPayNom("DELHAYE");
		dossier1.setPayPrenom("JEAN FABIEN");
		dossier1.setPaySexe(1);
		dossier1.setPayDateNaissance(DateUtils.parseDateMMAA("02/61"));
		dossier1.setRenoiRHMatricule("MSO000007087");
		dossier1.setRenoiRHCorpsCode("G1049");
		dossier1.setRenoiRHCorpsLibelleCourt("CONT NIVE 2 AFFA SOCI ARTI 4 84-16");
		dossier1.setRenoiRHGradeCode("G2953");
		dossier1.setRenoiRHGradeLibelleCourt("CONT NIVE 2 AFFA SOCI ART 4 84-16");
		dossier1.setRenoiRHAffectationCode("011C120001");
		dossier1.setRenoiRHAffectationLibelleCourt("DFAS");
		dossier1.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA("01/01/1988"));
		dossier1.setRenoiRHCivilite(1);
		dossier1.setRenoiRHNom("DELHAYE");
		dossier1.setRenoiRHPrenom("Jean-Fabien");
		dossier1.setRenoiRHSexe(1);
		dossier1.setRenoiRHDateNaissance(DateUtils.parseDateMMAA("02/61"));
		dossier1.setNbAlertes(0);
		dossier1.setNbAnomalies(1);

		dossiers.add(dossier1);

		final DossierDTO dossier2 = new DossierDTO();

		dossier2.setMinistere(256);
		dossier2.setPayCle(254087511303184L);
		dossier2.setDossierNumero(00);
		dossier2.setDiGestionnaire(20);
		dossier2.setAdminCode("E10");
		dossier2.setAdminCodeDepartement(075);
		dossier2.setPayLot("011B-ANT-AC-001");
		dossier2.setPayCivilite(2);
		dossier2.setPayNom("SIMON-PEIRANO");
		dossier2.setPayPrenom("DOMINIQUE");
		dossier2.setPaySexe(2);
		dossier2.setPayDateNaissance(DateUtils.parseDateMMAA("08/54"));
		dossier2.setRenoiRHMatricule("MSO000007377");
		dossier2.setRenoiRHCorpsCode("G959");
		dossier2.setRenoiRHCorpsLibelleCourt("CONT 78 AFFA SOCI HORS CATE");
		dossier2.setRenoiRHGradeCode("G2629");
		dossier2.setRenoiRHGradeLibelleCourt("CONT 78 AFFA SOCI HORS CATE");
		dossier2.setRenoiRHAffectationCode("011ZS00142");
		dossier2.setRenoiRHAffectationLibelleCourt("Cab Solid");
		dossier2.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA("01/01/1988"));
		dossier2.setRenoiRHCivilite(2);
		dossier2.setRenoiRHNom("SIMON PEIRANO");
		dossier2.setRenoiRHPrenom("Dominique");
		dossier2.setRenoiRHSexe(2);
		dossier2.setRenoiRHDateNaissance(DateUtils.parseDateMMAA("08/54"));
		dossier2.setNbAlertes(0);
		dossier2.setNbAnomalies(2);

		dossiers.add(dossier2);

		return dossiers;
	}

	@Override
	public List<AnomalieDTO> listerAnomalies(final String renoiRHMatricule, final String payLot) {
		final List<AnomalieDTO> anomalies = new ArrayList<AnomalieDTO>();

		if ("MSO000007087".equals(renoiRHMatricule) && "011B-ANT-AC-001".equals(payLot)) {
			final DifferenceDTO donnees1 = new DifferenceDTO();
			donnees1.setDonneeGA("Jean-Fabien");
			donnees1.setDonneePAY("JEAN FABIEN");
			final AnomalieDTO anomalie1 = new AnomalieDTO();
			anomalie1.setDonnees(donnees1);
			anomalie1.setPerimetre(AnomaliePerimetreEnum.ETAT_CIVIL);
			anomalie1.setType(AnomalieTypeEnum.PRENOM);
			anomalie1.setEtatCorrection(AnomalieEtatEnum.A_TRAITER);

			anomalies.add(anomalie1);
		} else if ("MSO000007377".equals(renoiRHMatricule) && "011B-ANT-AC-001".equals(payLot)) {
			final DifferenceDTO donnees1 = new DifferenceDTO();
			donnees1.setDonneeGA("Dominique");
			donnees1.setDonneePAY("DOMINIQUE");
			final AnomalieDTO anomalie1 = new AnomalieDTO();
			anomalie1.setDonnees(donnees1);
			anomalie1.setPerimetre(AnomaliePerimetreEnum.ETAT_CIVIL);
			anomalie1.setType(AnomalieTypeEnum.PRENOM);
			anomalie1.setEtatCorrection(AnomalieEtatEnum.A_TRAITER);

			final DifferenceDTO donnees2 = new DifferenceDTO();
			donnees2.setDonneeGA("SIMON PEIRANO");
			donnees2.setDonneePAY("SIMON-PEIRANO");
			final AnomalieDTO anomalie2 = new AnomalieDTO();
			anomalie2.setDonnees(donnees2);
			anomalie2.setPerimetre(AnomaliePerimetreEnum.ETAT_CIVIL);
			anomalie2.setType(AnomalieTypeEnum.NOM);
			anomalie2.setEtatCorrection(AnomalieEtatEnum.A_TRAITER);

			anomalies.add(anomalie1);
			anomalies.add(anomalie2);
		}

		return anomalies;
	}

}
