package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;

public class LogWorkflowDTO implements Serializable {

	private static final long serialVersionUID = -2514357959518504793L;

	private String content;

	private LogWorkflowDTO() {
		super();
	}

	public LogWorkflowDTO(final String content) {
		this();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder logText = new StringBuilder();
		logText.append(this.content);
		return logText.toString();
	}

}
