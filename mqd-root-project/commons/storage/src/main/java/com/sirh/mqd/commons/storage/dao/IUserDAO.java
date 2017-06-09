package com.sirh.mqd.commons.storage.dao;

import java.util.Date;

import com.sirh.mqd.commons.storage.entity.UserEntity;

/**
 * Interface fournissant l'ensemble des méthodes d'accès à la table des
 * utilisateurs
 *
 * @author alexandre
 */
public interface IUserDAO {

	UserEntity selectUser(String username);

	void insertUser(UserEntity user);

	void updateUserAuthenticationDate(String username, Date lastConnection);
}
