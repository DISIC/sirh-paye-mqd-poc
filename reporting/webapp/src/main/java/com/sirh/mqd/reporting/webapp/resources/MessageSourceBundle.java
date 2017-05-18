package com.sirh.mqd.reporting.webapp.resources;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.sirh.mqd.reporting.api.resources.IMessageCodeValue;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;
import com.sirh.mqd.reporting.webapp.constantes.ContextConstantes;

/**
 * Implémentation du service technique de gestion des messages applicatifs
 * utilisant le framework Spring.<br/>
 * L'extension de la classe {@link AbstractMap} permet à cette implémentation de
 * pouvoir être utiliser dans des EL.<br/>
 * Par défaut, la locale Java utilisée est {@link Locale#FRENCH}.
 *
 * @see org.springframework.context.MessageSource
 * @author alexandre
 */
@Component(ContextConstantes.MESSAGE)
@DependsOn(ContextConstantes.MESSAGE_SOURCE)
public class MessageSourceBundle extends AbstractMap<Object, IMessageCodeValue> implements IMessageSourceBundle {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -7909531839728222672L;

	/**
	 * Resource Bundle des Messages
	 */
	@Autowired
	@Qualifier(ContextConstantes.MESSAGE_SOURCE)
	private MessageSource messages;

	/**
	 * Default Locale Value {@link Locale#FRENCH}
	 */
	private Locale defaultLocale;

	@Override
	public IMessageCodeValue get(final Object key) {
		if (key == null) {
			return null;
		}
		if (key instanceof String) {
			return new MessageCodeValue((String) key, null, this);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Set<Entry<Object, IMessageCodeValue>> entrySet() {
		return new HashSet<Entry<Object, IMessageCodeValue>>();
	}

	/**
	 * Le message par défaut (code non trouvé) est la chaîne <i>!!code!!</i>. La
	 * locale utilisée est celle par défaut.
	 *
	 * @see MessageTS#getMessage(String)
	 */
	@Override
	public String getMessage(final String code) {
		return messages.getMessage(code, null, getDefaultMessage(code), getLocale());
	}

	/**
	 * Le message par défaut (code non trouvé) est la chaîne <i>!!code!!</i>. La
	 * locale utilisée est celle par défaut.
	 *
	 * @see MessageTS#getMessage(String, Object[])
	 */
	@Override
	public String getMessage(final String code, final Object[] arguments) {
		return messages.getMessage(code, arguments, getDefaultMessage(code), getLocale());
	}

	/**
	 * La locale utilisée est celle par défaut.
	 *
	 * @see MessageTS#getMessage(String, Object[], String)
	 */
	@Override
	public String getMessage(final String code, final Object[] arguments, final String defaultMsg) {
		return messages.getMessage(code, arguments, defaultMsg, getLocale());
	}

	/**
	 * @see MessageTS#getMessage(String, Object[], String, Locale)
	 */
	@Override
	public String getMessage(final String code, final Object[] arguments, final String defaultMsg,
			final Locale locale) {
		return messages.getMessage(code, arguments, defaultMsg, locale);
	}

	/**
	 * La locale utilisée est celle par défaut.
	 *
	 * @throws NoSuchMessageException
	 *             Si le code n'est pas trouvé
	 * @see MessageTS#getMessageWithError(String)
	 */
	@Override
	public String getMessageWithError(final String code) throws NoSuchMessageException {
		return messages.getMessage(code, null, getLocale());
	}

	/**
	 * La locale utilisée est celle par défaut.
	 *
	 * @throws JrafFunctionalException
	 *             Si le code n'est pas trouvé
	 * @see MessageTS#getMessageWithError(String, Object[])
	 */
	@Override
	public String getMessageWithError(final String code, final Object[] arguments) throws NoSuchMessageException {
		return messages.getMessage(code, arguments, getLocale());
	}

	/**
	 * @see MessageTS#getMessageWithError(String, Object[], Locale)
	 */
	@Override
	public String getMessageWithError(final String code, final Object[] arguments, final Locale locale)
			throws NoSuchMessageException {
		return messages.getMessage(code, arguments, locale);
	}

	/**
	 * Retourne la locale par défaut de ce service. Si nulle, alors retourne la
	 * locale {@link Locale#FRENCH}.
	 *
	 * @return Locale Locale par défaut
	 */
	protected Locale getLocale() {
		if (defaultLocale != null) {
			return defaultLocale;
		}
		return Locale.FRENCH;
	}

	/**
	 * Construction du message par défaut pour un code donné utilisé lorsque ce
	 * dernier n'est pas trouvé.
	 *
	 * @param code
	 *            Le code du message à résoudre
	 * @return String Message par défaut (chaîne <i>!!code!!</i>)
	 */
	protected String getDefaultMessage(final String code) {
		return "!!" + code + "!!";
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(final MessageSource messages) {
		this.messages = messages;
	}

	/**
	 * Création de la locale par défaut de ce service.
	 *
	 * @see LocaleUtils#toLocale(String)
	 * @param defaultLocale
	 *            Chaîne de la forme {langage}_{pays}_{variante}
	 */
	@Override
	public void setDefaultLocale(final String defaultLocale) {
		this.defaultLocale = LocaleUtils.toLocale(defaultLocale);
	}
}
