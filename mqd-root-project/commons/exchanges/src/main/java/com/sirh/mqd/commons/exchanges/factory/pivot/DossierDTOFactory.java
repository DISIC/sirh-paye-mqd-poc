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

	public static DossierDTO createDossierDTOFromMSO(final String ministere, final String payCle,
			final String dossierNumero, final String diGestionnaire, final String adminCode,
			final String adminCodeDepartement, final String payLot, final String matricule, final String corpsCode,
			final String corpsLibelleCourt, final String gradeCode, final String gradeLibelleCourt,
			final String affectationCode, final String affectationLibelleCourt, final String dateCertificationPay,
			final String nom, final String prenom, final String dateNaissance, final String civilite, final String sexe,
			final String dateMouvementCarriere, final String modaliteServiceCode,
			final String modaliteServiceLibelleLong) {
		final DossierDTO dossier = new DossierDTO();
		dossier.setMinistere((ministere != null) ? Integer.parseInt(ministere) : null);
		dossier.setPayCle(payCle);
		dossier.setDossierNumero((dossierNumero != null) ? Integer.parseInt(dossierNumero) : null);
		dossier.setDiGestionnaire((diGestionnaire != null) ? Integer.parseInt(diGestionnaire) : null);
		dossier.setAdminCode(adminCode);
		dossier.setAdminCodeDepartement(adminCodeDepartement);
		dossier.setPayLot(payLot);
		dossier.setRenoiRHMatricule(matricule);
		dossier.setRenoiRHCorpsCode(corpsCode);
		dossier.setRenoiRHCorpsLibelleCourt(corpsLibelleCourt);
		dossier.setRenoiRHGradeCode(gradeCode);
		dossier.setRenoiRHGradeLibelleCourt(gradeLibelleCourt);
		dossier.setRenoiRHAffectationCode(affectationCode);
		dossier.setRenoiRHAffectationLibelleCourt(affectationLibelleCourt);
		dossier.setRenoiRHModaliteServiceCode(modaliteServiceCode);
		dossier.setRenoiRHModaliteServiceLibelleLong(modaliteServiceLibelleLong);

		dossier.setRenoiRHDateCertification(DateUtils.parseDateJJMMAAAA(dateCertificationPay));
		dossier.setRenoiRHNom(nom);
		dossier.setRenoiRHPrenom(prenom);
		dossier.setRenoiRHDateNaissance(DateUtils.parseDateMMAA(dateNaissance));
		dossier.setRenoiRHCivilite((civilite != null) ? Integer.parseInt(civilite) : null);
		dossier.setRenoiRHSexe((sexe != null) ? Integer.parseInt(sexe) : null);
		dossier.getMouvementsCarriere().add(DateUtils.parseDateJJMMAAAA(dateMouvementCarriere));
		return dossier;
	}

	public static DossierDTO createDossierDTOFromDGAC(final String ministere, final String dossierNumero,
			final String matricule, final String corpsCode, final String corpsLibelleCourt, final String gradeCode,
			final String gradeLibelleCourt, final String affectationCode, final String affectationLibelleCourt,
			final String nom, final String prenom, final String dateNaissance, final String dateMouvementCarriere,
			final String nir, final String codeGestionnaire, final String codeTG, final String temoinDossierPrincipal) {
		final DossierDTO dossier = new DossierDTO();
		dossier.setMinistere((ministere != null) ? Integer.parseInt(ministere.trim()) : null);
		dossier.setDossierNumero((dossierNumero != null) ? Integer.parseInt(dossierNumero.trim()) : null);
		dossier.setNir(nir);
		dossier.setCodeGestionnaire(codeGestionnaire);
		dossier.setCodeTG(codeTG);
		dossier.setTemoinDossierPrincipal(
				(temoinDossierPrincipal != null) ? Integer.parseInt(temoinDossierPrincipal.trim()) : null);
		dossier.setRenoiRHMatricule(matricule.trim());
		dossier.setRenoiRHCorpsCode(corpsCode);
		dossier.setRenoiRHCorpsLibelleCourt(corpsLibelleCourt);
		dossier.setRenoiRHGradeCode(gradeCode);
		dossier.setRenoiRHGradeLibelleCourt(gradeLibelleCourt);
		dossier.setRenoiRHAffectationCode(affectationCode);
		dossier.setRenoiRHAffectationLibelleCourt(affectationLibelleCourt);

		dossier.setRenoiRHNom(nom);
		dossier.setRenoiRHPrenom(prenom);
		dossier.setRenoiRHDateNaissance(
				(dateNaissance != null) ? DateUtils.parseDGACDateAAAAMMJJhhmmss(dateNaissance.trim()) : null);
		dossier.getMouvementsCarriere().add((dateMouvementCarriere != null)
				? DateUtils.parseDGACDateAAAAMMJJhhmmss(dateMouvementCarriere.trim()) : null);
		return dossier;
	}
}
