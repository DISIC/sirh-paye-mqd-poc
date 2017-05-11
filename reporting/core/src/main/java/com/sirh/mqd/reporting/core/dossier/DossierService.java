package com.sirh.mqd.reporting.core.dossier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.AnomalieDTO;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.DOSSIER_SERVICE)
public class DossierService implements IDossierService {

	@Override
	public List<AnomalieDTO> listerAnomalies(final String dossierId) {
		final List<AnomalieDTO> anomalies = new ArrayList<AnomalieDTO>();

		final AnomalieDTO anomalie1 = new AnomalieDTO();

		anomalie1.setMinistere(256);
		anomalie1.setPayCle(161027503500227L);
		anomalie1.setDossierNumero(20);
		anomalie1.setDiGestionnaire(20);
		anomalie1.setAdminCode("E10");
		anomalie1.setAdminCodeDepartement(075);
		anomalie1.setPayLot("011B-ANT-AC-001");
		anomalie1.setPayCivilite(1);
		anomalie1.setPayNom("DELHAYE");
		anomalie1.setPayPrenom("JEAN FABIEN");
		anomalie1.setPaySexe(1);
		anomalie1.setPayDateNaissance(DateUtils.parseDateMMAA("02/61"));
		anomalie1.setRenoiRHMatricule("MSO000007087");
		anomalie1.setRenoiRHCorpsCode("G1049");
		anomalie1.setRenoiRHCorpsLibelleCourt("CONT NIVE 2 AFFA SOCI ARTI 4 84-16");
		anomalie1.setRenoiRHGradeCode("G2953");
		anomalie1.setRenoiRHGradeLibelleCourt("CONT NIVE 2 AFFA SOCI ART 4 84-16");
		anomalie1.setRenoiRHAffectationCode("011C120001");
		anomalie1.setRenoiRHAffectationLibelleCourt("DFAS");
		anomalie1.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA("01/01/1988"));
		anomalie1.setRenoiRHCivilite(1);
		anomalie1.setRenoiRHNom("DELHAYE");
		anomalie1.setRenoiRHPrenom("Jean-Fabien");
		anomalie1.setRenoiRHSexe(1);
		anomalie1.setRenoiRHDateNaissance(DateUtils.parseDateMMAA("02/61"));

		anomalies.add(anomalie1);

		final AnomalieDTO anomalie2 = new AnomalieDTO();

		anomalie2.setMinistere(256);
		anomalie2.setPayCle(254087511303184L);
		anomalie2.setDossierNumero(00);
		anomalie2.setDiGestionnaire(20);
		anomalie2.setAdminCode("E10");
		anomalie2.setAdminCodeDepartement(075);
		anomalie2.setPayLot("011B-ANT-AC-001");
		anomalie2.setPayCivilite(2);
		anomalie2.setPayNom("SIMON-PEIRANO");
		anomalie2.setPayPrenom("DOMINIQUE");
		anomalie2.setPaySexe(2);
		anomalie2.setPayDateNaissance(DateUtils.parseDateMMAA("08/54"));
		anomalie2.setRenoiRHMatricule("MSO000007377");
		anomalie2.setRenoiRHCorpsCode("G959");
		anomalie2.setRenoiRHCorpsLibelleCourt("CONT 78 AFFA SOCI HORS CATE");
		anomalie2.setRenoiRHGradeCode("G2629");
		anomalie2.setRenoiRHGradeLibelleCourt("CONT 78 AFFA SOCI HORS CATE");
		anomalie2.setRenoiRHAffectationCode("011ZS00142");
		anomalie2.setRenoiRHAffectationLibelleCourt("Cab Solid");
		anomalie2.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA("01/01/1988"));
		anomalie2.setRenoiRHCivilite(2);
		anomalie2.setRenoiRHNom("SIMON PEIRANO");
		anomalie2.setRenoiRHPrenom("Dominique");
		anomalie2.setRenoiRHSexe(2);
		anomalie2.setRenoiRHDateNaissance(DateUtils.parseDateMMAA("08/54"));

		anomalies.add(anomalie2);

		return anomalies;
	}
}
