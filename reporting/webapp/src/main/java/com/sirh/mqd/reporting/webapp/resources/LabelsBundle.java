package com.sirh.mqd.reporting.webapp.resources;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

/**
 * {@linkplain http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/}
 *
 * @author alexandre
 */
public class LabelsBundle extends ResourceBundle {

	private static final String BUNDLE_NAME = "i18n/Label";

	private static final Control UTF8_CONTROL = new UTF8Control();

	public LabelsBundle() {
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
