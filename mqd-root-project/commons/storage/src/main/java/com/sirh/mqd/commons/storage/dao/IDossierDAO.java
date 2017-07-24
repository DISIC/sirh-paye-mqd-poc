package com.sirh.mqd.commons.storage.dao;

import java.util.List;

import com.sirh.mqd.commons.exchanges.enums.AnomalieTypeEnum;
import com.sirh.mqd.commons.storage.entity.AlerteEntity;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DossierEntity;

/**
 * Interface fournissant l'ensemble des méthodes d'accès à la table des dossiers
 * et des comparaisons de données en entrée
 *
 * @author alexandre
 */
public interface IDossierDAO {

	DossierEntity selectDossier(String payLot, String matricule);

	List<DossierEntity> selectDossiers(String payLot, String corpsCode, String affectationCode,
			String gestionnaireCode);

	void insertDossier(DossierEntity dossier);

	ComparaisonEntity selectComparaison(String payLot, String matricule, AnomalieTypeEnum typeDonnee);

	List<ComparaisonEntity> selectAnomalies(String payLot, String matricule);

	List<ComparaisonEntity> selectComparaisons(String payLot, String matricule);

	void insertComparaison(ComparaisonEntity comparaison);

	int countAnomaliesDossier(String payLot, String matricule);

	int countAlertesDossier(String payLot, String matricule);

	void insertAlerte(AlerteEntity alerte);

	int countAlerte(AlerteEntity entity);

	void deleteAlerte(AlerteEntity entity);

	void updateAnomalie(ComparaisonEntity entity);

	AlerteEntity selectAlerte(String payLot, String matricule, AnomalieTypeEnum type);

	List<AlerteEntity> selectAlertes(String payLot, String matricule);

	void updateAlerte(AlerteEntity createAlerteEntity);
}
