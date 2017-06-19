package com.sirh.mqd.commons.exchanges.factory.pivot;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Factory de création des dossiers à partir des fichiers reçus en entrée du
 * système.
 *
 * @author alexandre
 */
public final class DossierDTOFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private DossierDTOFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + DossierDTOFactory.class.getName());
	}

	public static DossierDTO createDossierDTO(final String ministere, final String payCle, final String dossierNumero,
			final String diGestionnaire, final String adminCode, final String adminCodeDepartement, final String payLot,
			final String renoiRHMatricule, final String renoiRHCorpsCode, final String renoiRHCorpsLibelleCourt,
			final String renoiRHGradeCode, final String renoiRHGradeLibelleCourt, final String renoiRHAffectationCode,
			final String renoiRHAffectationLibelleCourt, final String renoiRHDateCertification, final String renoiRHNom,
			final String renoiRHPrenom, final String renoiRHDateNaissance, final String renoiRHCivilite,
			final String renoiRHSexe, final String dateMouvementCarriere, final String renoiRHModaliteServiceCode,
			final String renoiRHModaliteServiceLibelleLong) {
		final DossierDTO dossier = new DossierDTO();
		dossier.setMinistere(Integer.parseInt(ministere));
		dossier.setPayCle(payCle);
		dossier.setDossierNumero(Integer.parseInt(dossierNumero));
		dossier.setDiGestionnaire(Integer.parseInt(diGestionnaire));
		dossier.setAdminCode(adminCode);
		dossier.setAdminCodeDepartement(adminCodeDepartement);
		dossier.setPayLot(payLot);
		dossier.setRenoiRHMatricule(renoiRHMatricule);
		dossier.setRenoiRHCorpsCode(renoiRHCorpsCode);
		dossier.setRenoiRHCorpsLibelleCourt(renoiRHCorpsLibelleCourt);
		dossier.setRenoiRHGradeCode(renoiRHGradeCode);
		dossier.setRenoiRHGradeLibelleCourt(renoiRHGradeLibelleCourt);
		dossier.setRenoiRHAffectationCode(renoiRHAffectationCode);
		dossier.setRenoiRHAffectationLibelleCourt(renoiRHAffectationLibelleCourt);
		dossier.setRenoiRHModaliteServiceCode(renoiRHModaliteServiceCode);
		dossier.setRenoiRHModaliteServiceLibelleLong(renoiRHModaliteServiceLibelleLong);

		dossier.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA(renoiRHDateCertification));
		dossier.setRenoiRHNom(renoiRHNom);
		dossier.setRenoiRHPrenom(renoiRHPrenom);
		dossier.setRenoiRHDateNaissance(DateUtils.parseDateMMAA(renoiRHDateNaissance));
		dossier.setRenoiRHCivilite((renoiRHCivilite != null) ? Integer.parseInt(renoiRHCivilite) : null);
		dossier.setRenoiRHSexe((renoiRHSexe != null) ? Integer.parseInt(renoiRHSexe) : null);
		dossier.getMouvementsCarriere().add(DateUtils.parseDateJJMMAAAA(dateMouvementCarriere));
		return dossier;
	}
}
