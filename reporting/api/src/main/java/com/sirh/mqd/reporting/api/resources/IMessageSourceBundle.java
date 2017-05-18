package com.sirh.mqd.reporting.api.resources;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.NoSuchMessageException;

/**
 * Interface du service technique de gestion des messages applicatifs.
 *
 * @author alexandre
 */
public interface IMessageSourceBundle extends Serializable {

	/**
	 * Retourne le message associé à un code donné. Si le code n'est pas trouvé,
	 * un message par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du message
	 * @return String Le message associé
	 */
	String getMessage(String code);

	/**
	 * Retourne le message avec arguments associé à un code donné. Si le code
	 * n'est pas trouvé, un message par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du message
	 * @param arguments
	 *            Arguments du message
	 * @return String Le message associé
	 */
	String getMessage(String code, Object[] arguments);

	/**
	 * Retourne le message avec arguments associé à un code donné. Si le code
	 * n'est pas trouvé, un message par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du message
	 * @param arguments
	 *            Arguments du message
	 * @param defaultMsg
	 *            Message par défaut
	 * @return String Le message associé
	 */
	String getMessage(String code, Object[] arguments, String defaultMsg);

	/**
	 * Retourne le message avec arguments associé à un code donné. Si le code
	 * n'est pas trouvé, un message par défaut sera utilisé.
	 *
	 * @param code
	 *            Le code du message
	 * @param arguments
	 *            Arguments du message
	 * @param defaultMsg
	 *            Message par défaut
	 * @param locale
	 *            Locale Java pour internationalisation
	 * @return String Le message associé
	 */
	String getMessage(String code, Object[] arguments, String defaultMsg, Locale locale);

	/**
	 * Retourne le message associé à un code donné.
	 *
	 * @param code
	 *            Le code du message
	 * @return String Le message associé
	 * @throws NoSuchMessageException
	 *             Si le code n'est pas trouvé
	 */
	String getMessageWithError(String code) throws NoSuchMessageException;

	/**
	 * Retourne le message avec arguments associé à un code donné.
	 *
	 * @param code
	 *            Le code du message
	 * @param arguments
	 *            Arguments du message
	 * @return String Le message associé
	 * @throws NoSuchMessageException
	 *             Si le code n'est pas trouvé
	 */
	String getMessageWithError(String code, Object[] arguments) throws NoSuchMessageException;

	/**
	 * Retourne le message avec arguments associé à un code donné.
	 *
	 * @param code
	 *            Le code du message
	 * @param arguments
	 *            Arguments du message
	 * @param locale
	 *            Locale Java pour internationalisation
	 * @return String Le message associé
	 * @throws NoSuchMessageException
	 *             Si le code n'est pas trouvé
	 */
	String getMessageWithError(String code, Object[] arguments, Locale locale) throws NoSuchMessageException;

	/**
	 * Méthode permettant de paramétrer la valeur de l'attribut Locale par
	 * défaut
	 *
	 * @param defaultLocale
	 */
	void setDefaultLocale(String defaultLocale);
}
