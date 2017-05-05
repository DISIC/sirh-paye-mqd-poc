package com.sirh.mqd.commons.traces.dto;

import java.io.Serializable;

public class LogExceptionDTO implements Serializable {

	private static final long serialVersionUID = 3127261125583762108L;

	private LogTechniqueDTO logTechniqueDTO;

	private LogWorkflowDTO logWorkflowDTO;

	private LogExceptionDTO() {
		super();
	}

	public LogExceptionDTO(final LogTechniqueDTO logTechniqueDTO, final LogWorkflowDTO logWorkflowDTO) {
		this();
		this.logTechniqueDTO = logTechniqueDTO;
		this.logWorkflowDTO = logWorkflowDTO;
	}

	public LogTechniqueDTO getLogTechniqueDTO() {
		return logTechniqueDTO;
	}

	public void setLogTechniqueDTO(final LogTechniqueDTO logTechniqueDTO) {
		this.logTechniqueDTO = logTechniqueDTO;
	}

	public LogWorkflowDTO getLogWorkflowDTO() {
		return logWorkflowDTO;
	}

	public void setLogWorkflowDTO(final LogWorkflowDTO logWorkflowDTO) {
		this.logWorkflowDTO = logWorkflowDTO;
	}

}
