package com.sirh.mqd.commons.storage.factory;

import com.google.common.base.Joiner;
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
			dossier.setRenoiRHModaliteServiceCode(entity.getRenoiRHModaliteServiceCode());
			dossier.setRenoiRHModaliteServiceLibelleLong(entity.getRenoiRHModaliteServiceLibelleLong());
			dossier.setRenoiRHDateCertification(DateUtils.clonerDate(entity.getRenoiRHDateCertification()));
			dossier.setRenoiRHCivilite(entity.getRenoiRHCivilite());
			dossier.setRenoiRHNom(entity.getRenoiRHNom());
			dossier.setRenoiRHPrenom(entity.getRenoiRHPrenom());
			dossier.setRenoiRHSexe(entity.getRenoiRHSexe());
			dossier.setRenoiRHDateNaissance(DateUtils.clonerDate(entity.getRenoiRHDateNaissance()));
			dossier.setMouvementsCarriere(entity.getMouvementsCarriere());
			dossier.setNbAlertes(entity.getNbAlertes());
			dossier.setNbAnomalies(entity.getNbAnomalies());
			dossier.setNir(entity.getNir());
			dossier.setTgCode(entity.getTgCode());
			dossier.setTemoinDossierPrincipal(entity.getTemoinDossierPrincipal());
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
			entity.setRenoiRHModaliteServiceCode(dossier.getRenoiRHModaliteServiceCode());
			entity.setRenoiRHModaliteServiceLibelleLong(dossier.getRenoiRHModaliteServiceLibelleLong());
			entity.setRenoiRHDateCertification(DateUtils.clonerDate(dossier.getRenoiRHDateCertification()));
			entity.setRenoiRHCivilite(dossier.getRenoiRHCivilite());
			entity.setRenoiRHNom(dossier.getRenoiRHNom());
			entity.setRenoiRHPrenom(dossier.getRenoiRHPrenom());
			entity.setRenoiRHSexe(dossier.getRenoiRHSexe());
			entity.setRenoiRHDateNaissance(DateUtils.clonerDate(dossier.getRenoiRHDateNaissance()));
			entity.setMouvementsCarriere(dossier.getMouvementsCarriere());
			entity.setNbAlertes(dossier.getNbAlertes());
			entity.setNbAnomalies(dossier.getNbAnomalies());
			entity.setNir(dossier.getNir());
			entity.setTgCode(dossier.getTgCode());
			entity.setTemoinDossierPrincipal(dossier.getTemoinDossierPrincipal());
		}
		return entity;
	}

	private static String generateEntityId(final String payLot, final String renoiRHMatricule) {
		final StringBuilder builder = new StringBuilder();
		final Joiner joiner = Joiner.on(Constantes.DASH).useForNull(Constantes.MONGO_COLLECTION_ID_DEFAULT_VALUE);
		joiner.appendTo(builder, payLot, renoiRHMatricule);
		return builder.toString();
	}
}
