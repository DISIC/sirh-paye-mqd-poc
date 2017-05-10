package com.sirh.mqd.commons.traces;

import java.util.concurrent.Executor;

import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkException;

import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jca.work.DelegatingWork;
import org.springframework.jca.work.SimpleTaskWorkManager;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;

import com.sirh.mqd.commons.utils.exception.TechnicalException;

public class SpringDefaultTask {

	SimpleTaskWorkManager wkm;

	Work work;

	public SpringDefaultTask(final Runnable run, final Executor concurrentExecutor) {
		this(run);
		final AsyncTaskExecutor task = new ConcurrentTaskExecutor(concurrentExecutor);
		wkm.setAsyncTaskExecutor(task);

	}

	public SpringDefaultTask(final Runnable run) {
		wkm = new SimpleTaskWorkManager();
		work = new DelegatingWork(run);
	}

	public void setAsyncTaskExecutor(final AsyncTaskExecutor task) {
		wkm.setAsyncTaskExecutor(task);

	}

	public void run() {
		try {
			wkm.doWork(work);
		} catch (final WorkException e) {
			throw new TechnicalException(e);
		}

	}

}
