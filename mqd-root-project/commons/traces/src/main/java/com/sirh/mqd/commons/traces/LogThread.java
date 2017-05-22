package com.sirh.mqd.commons.traces;

import org.springframework.core.task.AsyncTaskExecutor;

public class LogThread extends SpringDefaultTask {

	public LogThread(final Runnable run) {
		super(run);
	}

	@Override
	public void run() {
		super.run();
	}

	public void start(final AsyncTaskExecutor task) {
		if (task != null) {
			super.setAsyncTaskExecutor(task);
		}
		super.run();
	}
}
