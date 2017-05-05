package com.sirh.mqd.reporting.persistence.entities;

import org.springframework.data.annotation.Id;

/**
 *
 * Entité d'action elasticsearch
 *
 */
public class GenericLogstachEntity {

	@Id
	private String id;

	private String message;

	private String path;

	private String log_date;

	private String log_level;

	private String classname;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public String getLog_date() {
		return log_date;
	}

	public void setLog_date(final String log_date) {
		this.log_date = log_date;
	}

	public String getLog_level() {
		return log_level;
	}

	public void setLog_level(final String log_level) {
		this.log_level = log_level;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(final String classname) {
		this.classname = classname;
	}

}
