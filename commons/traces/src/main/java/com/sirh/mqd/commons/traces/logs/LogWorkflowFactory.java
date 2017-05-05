package com.sirh.mqd.commons.traces.logs;

import com.sirh.mqd.commons.traces.dto.LogWorkflowDTO;

public class LogWorkflowFactory {

	public static LogWorkflowDTO getLogWorkflow(final String content) {
		return new LogWorkflowDTO(content);
	}

}
