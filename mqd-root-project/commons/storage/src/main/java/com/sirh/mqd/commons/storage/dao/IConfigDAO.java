package com.sirh.mqd.commons.storage.dao;

import com.sirh.mqd.commons.storage.entity.ConfigEntity;

/**
 * Interface fournissant l'ensemble des méthodes permettant la saisie de
 * paramètres dynamiques
 *
 * @author khalil
 */
public interface IConfigDAO {

	ConfigEntity selectConfig(String id);

}
