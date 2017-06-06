package com.sirh.mqd.reporting.webapp.model;

import java.io.Serializable;
import java.util.Date;

import com.sirh.mqd.commons.utils.DateUtils;

/**
 * Model de commentaire d'un dossier à manipuler dans la partie Vue
 *
 * @author khalil
 */
public class CommentaireModel implements Serializable{

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 3482444480416100175L;

	private Date dateCreation;

	private String contenu;

	private String utilisateur;

	public CommentaireModel() {
		super();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = DateUtils.clonerDate(dateCreation);
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(final String contenu) {
		this.contenu = contenu;
	}

	public String getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(final String utilisateur) {
		this.utilisateur = utilisateur;
	}
}
