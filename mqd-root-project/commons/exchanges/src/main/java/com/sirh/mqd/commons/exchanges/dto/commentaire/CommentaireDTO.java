package com.sirh.mqd.commons.exchanges.dto.commentaire;

import java.util.Date;

/**
 * DTO permettant de manipuler les commentaires d'un dossier
 *
 * @author khalil
 */
public class CommentaireDTO {

	private Date dateCreation;

	private String contenu;

	public CommentaireDTO() {
		super();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(final Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(final String contenu) {
		this.contenu = contenu;
	}

}
