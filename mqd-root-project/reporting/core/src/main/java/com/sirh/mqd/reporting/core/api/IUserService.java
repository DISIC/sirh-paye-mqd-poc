package com.sirh.mqd.reporting.core.api;

import java.util.Date;
import java.util.Optional;

import com.sirh.mqd.commons.exchanges.dto.security.UserDTO;

/**
 * Service de gestion des accès utilisateurs
 *
 * @author alexandre
 */
public interface IUserService {

	/**
	 * Méthode permettant de recherche un utilisateur
	 *
	 * @param username
	 * @return {@link UserDTO} l'utilsateur trouvé ou null s'il n'existe pas
	 */
	Optional<UserDTO> rechercherUtilisateur(String username);

	/**
	 * Méthode permettant de recherche un utilisateur qui possède un mot de
	 * passe
	 *
	 * @param username
	 * @return {@link UserDTO} l'utilsateur trouvé ou null s'il n'existe pas
	 */
	Optional<UserDTO> rechercherUtilisateurAvecMotDePasse(String username);

	/**
	 * Méthode permettant d'ajouter un utilisateur au système
	 *
	 * @param user
	 */
	void ajouterUtilisateur(UserDTO user);

	/**
	 * Méthode permettant de modifier la date de dernière connexion d'un
	 * utilisateur
	 *
	 * @param username
	 * @param lastConnection
	 */
	void modifierDateDerniereConnexion(String username, Date lastConnection);

	/**
	 * Méthode permettant de modifier le mot de passe d'un utilisateur
	 *
	 * @param username
	 * @param password
	 */
	void modifierMotDePasse(String username, String password);
}
