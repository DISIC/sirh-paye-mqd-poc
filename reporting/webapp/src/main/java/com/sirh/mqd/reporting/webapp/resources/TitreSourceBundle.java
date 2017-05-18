package com.sirh.mqd.reporting.webapp.resources;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.LocaleUtils;

import com.sirh.mqd.reporting.api.resources.ITitreSourceBundle;

/**
 * {@linkplain http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/}
 *
 * @author alexandre
 */
public class TitreSourceBundle extends ResourceBundle implements ITitreSourceBundle {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 9202807066855562804L;

	private static final String BUNDLE_NAME = "i18n/titres";

	private static final Control UTF8_CONTROL = new UTF8Control();

	/**
	 * Default Locale Value {@link Locale#FRENCH}
	 */
	private Locale defaultLocale;

	public TitreSourceBundle() {
		setParent(ResourceBundle.getBundle(BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale(),
				UTF8_CONTROL));
	}

	@Override
	protected Object handleGetObject(final String key) {
		return parent.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

	@Override
	public Locale getLocale() {
		if (this.defaultLocale != null) {
			return this.defaultLocale;
		}
		return Locale.FRENCH;
	}

	/**
	 * Création de la locale par défaut de ce service.
	 *
	 * @see LocaleUtils#toLocale(String)
	 * @param defaultLocale
	 *            Chaîne de la forme {langage}_{pays}_{variante}
	 */
	public void setDefaultLocale(final String defaultLocale) {
		this.defaultLocale = LocaleUtils.toLocale(defaultLocale);
	}

	@Override
	public String getTitre(final String code, final Object... arguments) {
		try {
			final String result = this.getString(code);
			return MessageFormat.format(result, arguments);
		} catch (final MissingResourceException e) {
			return getDefaultMessage(code);
		}
	}

	/**
	 * Construction du message par défaut pour un code donné utilisé lorsque ce
	 * dernier n'est pas trouvé.
	 *
	 * @param code
	 *            Le code du message à résoudre
	 * @return String Message par défaut (chaîne <i>!!code!!</i>)
	 */
	private String getDefaultMessage(final String code) {
		return "!!" + code + "!!";
	}
}
