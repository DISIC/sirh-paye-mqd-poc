package com.sirh.mqd.commons.storage.bc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.pivot.StatutDossierDTO;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.IStatutDossierDAO;
import com.sirh.mqd.commons.storage.entity.StatutDossierEntity;
import com.sirh.mqd.commons.storage.factory.StatutDossierEntityFactory;

/**
 * Implémentation du BusinessController permettant de gérer les statuts des
 * dossiers
 *
 * @author khalil
 */
@Service(PersistenceConstantes.STATUT_DOSSIER_BC)
public class StatutDossierBC {

	@Autowired
	@Qualifier(PersistenceConstantes.STATUT_DOSSIER_DAO)
	private IStatutDossierDAO statutDossierDAO;

	/**
	 * Méthode permettant de récuperer le statut d'un dossier
	 *
	 * @param renoiRHMatricule
	 * @param payLot
	 * @return {@link StatutDossierDTO} correspondant au statut du dossier
	 *         contenant les informations issues de la base de données
	 */
	public Optional<StatutDossierDTO> rechercherStatutDossier(final String renoiRHMatricule, final String payLot) {
		final StatutDossierEntity statutDossierEntity = this.statutDossierDAO.selectStatutDossier(renoiRHMatricule,
				payLot);
		return Optional.ofNullable(StatutDossierEntityFactory.createStatutDossierDTO(statutDossierEntity));
	}

	/**
	 * Méthode permettant de modifier le statut d'un dossier
	 *
	 * @param statutDossierDTO
	 *            les statuts du dossier à enregistrer
	 */
	public void modifierStatutDossier(final StatutDossierDTO statutDossierDTO) {
		final StatutDossierEntity entity = StatutDossierEntityFactory.createStatutDossierEntity(statutDossierDTO);
		statutDossierDAO.updateStatutDossier(entity);
	}
}
