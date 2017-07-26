package com.sirh.mqd.reporting.webapp.views.user;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sirh.mqd.reporting.core.api.IUserService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue de la page de gestion du mot de passe d'un utilisateur
 *
 * @author alexandre
 */
@ManagedBean(name = ViewConstantes.USER_BEAN)
@SessionScoped
public class UserBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 555762255490874333L;

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@ManagedProperty("#{" + CoreConstantes.USER_SERVICE + "}")
	private IUserService userService;

	/**
	 * Login.
	 */
	private String username;

	/**
	 * Mot de passe.
	 */
	private String password;

	@PostConstruct
	public void setup() {
		// Initialization
		this.username = StringUtils.EMPTY;
		this.password = StringUtils.EMPTY;

		// Supplier
	}

	/**
	 * Méthode permettant de modifier le mot de passe d'un utilisateur
	 */
	public void modifierMotDePasse() throws ValidatorException {
		this.userService.modifierMotDePasse(this.username, PASSWORD_ENCODER.encode(this.password));
		this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_INFO, "view.login.update.password.success", this.username);
		setup();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(final IUserService userService) {
		this.userService = userService;
	}
}
