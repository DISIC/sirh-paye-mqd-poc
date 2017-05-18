package com.sirh.mqd.reporting.webapp.resources;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sirh.mqd.reporting.api.resources.IMessageCodeValue;
import com.sirh.mqd.reporting.api.resources.IMessageSourceBundle;

/**
 * Service technique de gestion de l'association code/value des messages
 * applicatifs. ApplicationContextAware
 *
 * @author alexandre
 */
public class MessageCodeValue extends AbstractMap<String, IMessageCodeValue> implements IMessageCodeValue {

	/**
	 * Code associé au message
	 */
	private String code;

	/**
	 * Arguments propres au message
	 */
	private List<Object> arguments;

	/**
	 * Resource Bundle associé au message
	 */
	private IMessageSourceBundle bundle;

	/**
	 * Constructeur par défaut.
	 *
	 * @param code
	 * @param arguments
	 * @param bundle
	 */
	public MessageCodeValue(final String code, final List<Object> arguments, final IMessageSourceBundle bundle) {
		this.code = code;
		this.arguments = arguments;
		if (arguments == null) {
			this.arguments = new ArrayList<Object>(3);
		}
		this.bundle = bundle;
	}

	/**
	 * @see java.util.AbstractMap#get(java.lang.Object)
	 */
	@Override
	public IMessageCodeValue get(final Object key) {
		this.arguments.add(key);
		return this;
	}

	/**
	 * @see java.util.AbstractMap#entrySet()
	 */
	@Override
	public Set<Entry<String, IMessageCodeValue>> entrySet() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the arguments
	 */
	public List<Object> getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(final List<Object> arguments) {
		this.arguments = arguments;
	}

	/**
	 * @return the bundle
	 */
	public IMessageSourceBundle getBundle() {
		return bundle;
	}

	/**
	 * @param bundle
	 *            the bundle to set
	 */
	public void setBundle(final IMessageSourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return bundle.getMessage(code, arguments.toArray());
	}
}
