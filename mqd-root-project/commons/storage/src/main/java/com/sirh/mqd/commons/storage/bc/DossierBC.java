package com.sirh.mqd.commons.storage.bc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.AlerteDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IDossierDAO;
import com.sirh.mqd.commons.storage.entity.AlerteEntity;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DossierEntity;
import com.sirh.mqd.commons.storage.factory.AlerteEntityFactory;
import com.sirh.mqd.commons.storage.factory.AnomalieEntityFactory;
import com.sirh.mqd.commons.storage.factory.DossierEntityFactory;
import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Implémentation du BusinessController permettant de gérer les dossiers et les
 * anomalies associées.
 *
 * @author alexandre
 */
@Service(PersistenceConstantes.DOSSIER_BC)
public class DossierBC {

	@Autowired
	@Qualifier(PersistenceConstantes.DOSSIER_DAO)
	private IDossierDAO dossierDAO;

	/**
	 * Méthode permettant de remonter un dossier.
	 *
	 * @param dossier
	 *            le dossier à rechercher en base de données
	 * @return {@link DossierDTO} correspondant au dossier contenant les
	 *         informations issues de la base de données
	 */
	public Optional<DossierDTO> rechercherDossier(final DossierDTO dossier) {
		final DossierEntity dossierEntity = this.dossierDAO.selectDossier(dossier.getPayLot(),
				dossier.getRenoiRHMatricule());
		dossierEntity.setNbAnomalies(
				this.dossierDAO.countAnomaliesDossier(dossierEntity.getPayLot(), dossierEntity.getRenoiRHMatricule()));
		dossierEntity.setNbAlertes(0);
		return Optional.ofNullable(DossierEntityFactory.createDossierDTO(dossierEntity));
	}

	/**
	 * Méthode permettant de lister des dossiers associé à un utilisateur.
	 *
	 * @param payLot
	 *            le lot paye associé à l'utilisateur
	 * @param renoiRHCorpsCode
	 *            le corps auquel est associé l'utilisateur
	 * @param renoiRHAffectationCode
	 *            l'affectation auquel est associé à l'utilisateur
	 * @param gestionnaireCode
	 *            le code gestionnaire DGAC
	 * @return {@link List} correspondant à une liste de dossiers
	 */
	public List<DossierDTO> listerDossiers(final String payLot, final String renoiRHCorpsCode,
			final String renoiRHAffectationCode, final String gestionnaireCode) {
		final List<DossierEntity> dossierEntities = this.dossierDAO.selectDossiers(payLot, renoiRHCorpsCode,
				renoiRHAffectationCode, gestionnaireCode);
		dossierEntities.forEach((dossierEntity) -> {
			dossierEntity.setNbAnomalies(this.dossierDAO.countAnomaliesDossier(dossierEntity.getPayLot(),
					dossierEntity.getRenoiRHMatricule()));
			dossierEntity.setNbAlertes(this.dossierDAO.countAlertesDossier(dossierEntity.getPayLot(),
					dossierEntity.getRenoiRHMatricule()));
		});
		return dossierEntities.stream().map(dossierEntity -> DossierEntityFactory.createDossierDTO(dossierEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant d'insérer un dossier ou de le mettre à jour s'il
	 * existe
	 *
	 * @param dossier
	 *            le dossier à insérer en base de données
	 */
	public void insererDossier(final DossierDTO dossier) {
		this.dossierDAO.insertDossier(DossierEntityFactory.createDossierEntity(dossier));
	}

	/**
	 * Méthode permettant de remonter une anomalie. Si elle n'existe pas, la
	 * méthode remonte null.
	 *
	 * @param comparaison
	 *            l'anomalie à rechercher en base de données
	 * @return {@link ComparaisonDTO} correspondant à l'anomalie contenant les
	 *         informations issues de la base de données
	 */
	public Optional<ComparaisonDTO> rechercherComparaison(final ComparaisonDTO comparaison) {
		final ComparaisonEntity anomalieEntity = this.dossierDAO.selectComparaison(comparaison.getPayLot(),
				comparaison.getRenoiRHMatricule(), comparaison.getType());
		return Optional.ofNullable(AnomalieEntityFactory.createComparaisonDTO(anomalieEntity));
	}

	/**
	 * Méthode permettant d'insérer une anomalie ou de la mettre à jour si elle
	 * existe
	 *
	 * @param comparaison
	 *            la comparaison de données à insérer en base de données
	 */
	public void insererComparaison(final ComparaisonDTO comparaison) {
		this.dossierDAO.insertComparaison(AnomalieEntityFactory.createComparaisonEntity(comparaison));
		final AlerteEntity entity = AlerteEntityFactory.createAlerteEntity(comparaison.getPayLot(),
				comparaison.getRenoiRHMatricule(), comparaison.getType(), comparaison.getDonnees());
		final boolean existAlerte = this.dossierDAO.countAlerte(entity) > 0;
		if (!comparaison.isAnomalieReouverte() && existAlerte) {
			final AlerteEntity existingAlerte = this.dossierDAO.selectAlerte(comparaison.getPayLot(),
					comparaison.getRenoiRHMatricule(), comparaison.getType());
			existingAlerte.setDateCloture(DateUtils.getCalendarInstance().getTime());
			this.dossierDAO.insertAlerte(existingAlerte);
		} else if (comparaison.isAnomalieReouverte() && !existAlerte) {
			this.dossierDAO.insertAlerte(entity);
		}
	}

	/**
	 * Méthode permettant de lister des alertes entre les données de flux
	 * associé à un dossier.
	 *
	 * @param payLot
	 *            le lot paye du dossier
	 * @param matricule
	 *            le matricule du dossier
	 * @return {@link List} correspondant à une liste de alertes
	 */
	public List<AlerteDTO> listerAlertes(final String payLot, final String matricule) {
		final List<AlerteEntity> alerteEntities = this.dossierDAO.selectAlertes(payLot, matricule);
		return alerteEntities.stream().map(alerteEntity -> AlerteEntityFactory.createAlerteDTO(alerteEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant de modifier l'état d'une alerte
	 *
	 * @param alerte
	 *            l'alerte à mettre à jour
	 */
	public void modifierAlerte(final AlerteDTO alerte) {
		this.dossierDAO.updateAlerte(AlerteEntityFactory.createAlerteEntity(alerte));
	}

	/**
	 * Méthode permettant de lister des comparaisons de données associé à un
	 * dossier.
	 *
	 * @param payLot
	 *            le lot paye du dossier
	 * @param matricule
	 *            le matricule du dossier
	 * @return {@link List} correspondant à une liste de comparaisons
	 */
	public List<ComparaisonDTO> listerComparaisons(final String payLot, final String matricule) {
		final List<ComparaisonEntity> comparaisonsEntities = this.dossierDAO.selectComparaisons(payLot, matricule);
		return comparaisonsEntities.stream()
				.map(comparaisonEntity -> AnomalieEntityFactory.createComparaisonDTO(comparaisonEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant de lister des anomalies entre les données de flux
	 * associé à un dossier.
	 *
	 * @param payLot
	 *            le lot paye du dossier
	 * @param matricule
	 *            le matricule du dossier
	 * @return {@link List} correspondant à une liste de comparaisons
	 */
	public List<ComparaisonDTO> listerAnomalies(final String payLot, final String matricule) {
		final List<ComparaisonEntity> comparaisonsEntities = this.dossierDAO.selectAnomalies(payLot, matricule);
		return comparaisonsEntities.stream()
				.map(comparaisonEntity -> AnomalieEntityFactory.createComparaisonDTO(comparaisonEntity))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode permettant de modifier l'état de correction d'une anomalie
	 *
	 * @param anomalie
	 *            l'anomalie à mettre à jour
	 */
	public void modifierAnomalie(final ComparaisonDTO anomalie) {
		this.dossierDAO.updateAnomalie(AnomalieEntityFactory.createComparaisonEntity(anomalie));
	}

}
