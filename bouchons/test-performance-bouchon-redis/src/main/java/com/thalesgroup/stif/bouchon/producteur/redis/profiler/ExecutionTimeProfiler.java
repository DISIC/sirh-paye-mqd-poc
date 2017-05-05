package com.thalesgroup.stif.bouchon.producteur.redis.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.thalesgroup.stif.commons.traces.api.IFacadeLogs;
import com.thalesgroup.stif.commons.traces.logs.FacadeLogFactory;
import com.thalesgroup.stif.commons.traces.logs.LogWorkflowFactory;

/**
 * Profiler permettant de logger le temps d'execution des methodes
 *
 * @author adile
 *
 */
@Aspect
public class ExecutionTimeProfiler {

	/** Logger. */
	private final IFacadeLogs LOG = FacadeLogFactory.getLogger(ExecutionTimeProfiler.class);

	/**
	 * Injection des properties du fichier application.properties
	 */
	//	@Value("#{application}")
	//	private Properties properties;

	/**
	 * Fait reference à l'ensemble des methodes de la classe SiriBean
	 */
	@Pointcut("execution(* com.thalesgroup.stif.bouchon.producteur.redis.simulator.SimulatorImpl.*(..))")
	public void simulation() {
	}

	@Around("simulation()")
	public Object profile(final ProceedingJoinPoint pjp) throws Throwable {

		long start = System.currentTimeMillis();

		// on exectute la methode
		Object output = pjp.proceed();

		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Durée de la simulation : " + pjp.getSignature().getName() + " "
				+ (System.currentTimeMillis() - start) + "ms"));
		LOG.logWorkflowInfo(LogWorkflowFactory.getLogWorkflow("Nombre de notification : " + output));

		return output;
	}
}
