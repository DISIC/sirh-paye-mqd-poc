package com.sirh.mqd.reporting.webapp.resources;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * {@linkplain http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/}
 *
 * @author alexandre
 */
public class MessagesBundle extends ResourceBundle {

	private static final String BUNDLE_NAME = "i18n/Message";

	private static final Control UTF8_CONTROL = new UTF8Control();

	public MessagesBundle() {
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

}
