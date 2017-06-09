package com.sirh.mqd.commons.traces;

/**
 * Classe permettant d'appeler la méthode
 * {@link SecurityManager#getClassContext()}, qui a un périmètre protected.
 * Cette enveloppe permet de rendre cette méthode visible dans le package.
 *
 * @author alexandre
 */
public class ClassContextSecurityManager extends SecurityManager {

	@Override
	public Class<?>[] getClassContext() {
		return super.getClassContext();
	}
}
