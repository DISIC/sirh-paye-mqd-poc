package com.sirh.mqd.commons.storage.bc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.ComparaisonDTO;
import com.sirh.mqd.commons.exchanges.dto.pivot.DossierDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IDossierDAO;
import com.sirh.mqd.commons.storage.entity.ComparaisonEntity;
import com.sirh.mqd.commons.storage.entity.DossierEntity;
import com.sirh.mqd.commons.storage.factory.AnomalieEntityFactory;
import com.sirh.mqd.commons.storage.factory.DossierEntityFactory;

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
	 * @return {@link List} correspondant à une liste de dossiers
	 */
	public List<DossierDTO> listerDossiers(final String payLot, final String renoiRHCorpsCode,
			final String renoiRHAffectationCode) {
		final List<DossierEntity> dossierEntities = this.dossierDAO.selectDossiers(payLot, renoiRHCorpsCode,
				renoiRHAffectationCode);

		dossierEntities.forEach((dossierEntity) -> {
			dossierEntity.setNbAnomalies(this.dossierDAO.countAnomaliesDossier(dossierEntity.getPayLot(),
					dossierEntity.getRenoiRHMatricule()));
			dossierEntity.setNbAlertes(0);
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
	}

	/**
	 * Méthode permettant de lister des comparaisons de données associé à un
	 * dossier.
	 *
	 * @param payLot
	 *            le lot paye du dossier
	 * @param renoiRHMatricule
	 *            le matricule du dossier
	 * @return {@link List} correspondant à une liste de comparaisons
	 */
	public List<ComparaisonDTO> listerComparaisons(final String payLot, final String renoiRHMatricule) {
		final List<ComparaisonEntity> comparaisonsEntities = this.dossierDAO.selectComparaisons(payLot,
				renoiRHMatricule);
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
	 * @param renoiRHMatricule
	 *            le matricule du dossier
	 * @return {@link List} correspondant à une liste de comparaisons
	 */
	public List<ComparaisonDTO> listerAnomalies(final String payLot, final String renoiRHMatricule) {
		final List<ComparaisonEntity> comparaisonsEntities = this.dossierDAO.selectAnomalies(payLot, renoiRHMatricule);
		return comparaisonsEntities.stream()
				.map(comparaisonEntity -> AnomalieEntityFactory.createComparaisonDTO(comparaisonEntity))
				.collect(Collectors.toList());
	}
}
