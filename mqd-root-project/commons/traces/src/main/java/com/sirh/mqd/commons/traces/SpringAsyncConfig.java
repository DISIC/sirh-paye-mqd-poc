package com.sirh.mqd.commons.traces;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;

@Configuration
@EnableAsync
public class SpringAsyncConfig {

	public SpringAsyncConfig() {
		super();
	}

	@Bean(name = ConstantesTraces.TASK_EXECUTOR_NAME)
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
}
