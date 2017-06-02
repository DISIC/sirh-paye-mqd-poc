package com.sirh.mqd.commons.storage.factory;

import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.storage.entity.DossierEntity;
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.commons.utils.constante.Constantes;

/**
 * Factory de création des entités des dossiers.
 *
 * @author alexandre
 */
public class DossierEntityFactory {

	/**
	 * Non-constructeur
	 *
	 * @throws InstantiationException
	 */
	private DossierEntityFactory() throws InstantiationException {
		throw new InstantiationException(
				"Création non autorisée d'une instance de : " + DossierEntityFactory.class.getName());
	}

	public static DossierDTO createDossierDTO(final DossierEntity entity) {
		DossierDTO dossier = null;
		if (entity != null) {
			dossier = new DossierDTO();
			dossier.setMinistere(entity.getMinistere());
			dossier.setDossierNumero(entity.getDossierNumero());
			dossier.setDiGestionnaire(entity.getDiGestionnaire());
			dossier.setAdminCode(entity.getAdminCode());
			dossier.setAdminCodeDepartement(entity.getAdminCodeDepartement());
			dossier.setPayLot(entity.getPayLot());
			dossier.setRenoiRHMatricule(entity.getRenoiRHMatricule());
			dossier.setRenoiRHCorpsCode(entity.getRenoiRHCorpsCode());
			dossier.setRenoiRHCorpsLibelleCourt(entity.getRenoiRHCorpsLibelleCourt());
			dossier.setRenoiRHGradeCode(entity.getRenoiRHGradeCode());
			dossier.setRenoiRHGradeLibelleCourt(entity.getRenoiRHGradeLibelleCourt());
			dossier.setRenoiRHAffectationCode(entity.getRenoiRHAffectationCode());
			dossier.setRenoiRHAffectationLibelleCourt(entity.getRenoiRHAffectationLibelleCourt());
			dossier.setRenoiRHDateCertification(DateUtils.clonerDate(entity.getRenoiRHDateCertification()));
			dossier.setRenoiRHCivilite(entity.getRenoiRHCivilite());
			dossier.setRenoiRHNom(entity.getRenoiRHNom());
			dossier.setRenoiRHPrenom(entity.getRenoiRHPrenom());
			dossier.setRenoiRHSexe(entity.getRenoiRHSexe());
			dossier.setRenoiRHDateNaissance(DateUtils.clonerDate(entity.getRenoiRHDateNaissance()));
			dossier.setMouvementsCarriere(entity.getMouvementsCarriere());
			dossier.setNbAlertes(entity.getNbAlertes());
			dossier.setNbAnomalies(entity.getNbAnomalies());
		}
		return dossier;
	}

	public static DossierEntity createDossierEntity(final DossierDTO dossier) {
		DossierEntity entity = null;
		if (dossier != null) {
			entity = new DossierEntity();
			entity.setId(generateEntityId(dossier.getPayLot(), dossier.getRenoiRHMatricule()));
			entity.setMinistere(dossier.getMinistere());
			entity.setDossierNumero(dossier.getDossierNumero());
			entity.setDiGestionnaire(dossier.getDiGestionnaire());
			entity.setAdminCode(dossier.getAdminCode());
			entity.setAdminCodeDepartement(dossier.getAdminCodeDepartement());
			entity.setPayLot(dossier.getPayLot());
			entity.setRenoiRHMatricule(dossier.getRenoiRHMatricule());
			entity.setRenoiRHCorpsCode(dossier.getRenoiRHCorpsCode());
			entity.setRenoiRHCorpsLibelleCourt(dossier.getRenoiRHCorpsLibelleCourt());
			entity.setRenoiRHGradeCode(dossier.getRenoiRHGradeCode());
			entity.setRenoiRHGradeLibelleCourt(dossier.getRenoiRHGradeLibelleCourt());
			entity.setRenoiRHAffectationCode(dossier.getRenoiRHAffectationCode());
			entity.setRenoiRHAffectationLibelleCourt(dossier.getRenoiRHAffectationLibelleCourt());
			entity.setRenoiRHDateCertification(DateUtils.clonerDate(dossier.getRenoiRHDateCertification()));
			entity.setRenoiRHCivilite(dossier.getRenoiRHCivilite());
			entity.setRenoiRHNom(dossier.getRenoiRHNom());
			entity.setRenoiRHPrenom(dossier.getRenoiRHPrenom());
			entity.setRenoiRHSexe(dossier.getRenoiRHSexe());
			entity.setRenoiRHDateNaissance(DateUtils.clonerDate(dossier.getRenoiRHDateNaissance()));
			entity.setMouvementsCarriere(dossier.getMouvementsCarriere());
			entity.setNbAlertes(dossier.getNbAlertes());
			entity.setNbAnomalies(dossier.getNbAnomalies());
		}
		return entity;
	}

	private static String generateEntityId(final String payLot, final String renoiRHMatricule) {
		final StringBuilder builder = new StringBuilder();
		builder.append(payLot);
		builder.append(Constantes.DASH);
		builder.append(renoiRHMatricule);
		return builder.toString();
	}
}
