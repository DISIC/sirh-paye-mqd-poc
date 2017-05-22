package com.sirh.mqd.commons.exchanges.dto.pivot;

import com.sirh.mqd.commons.exchanges.enums.DossierAffectationEnum;
import com.sirh.mqd.commons.exchanges.enums.DossierVersionPapierEnum;

/**
 * DTO permettant de manipuler les commentaires d'un dossier
 *
 * @author alexandre
 */
public class CommentaireDTO {

	private String content;

	private DossierVersionPapierEnum etatDossierVersionPapier;

	private DossierAffectationEnum etatDossierAffectation;

	public CommentaireDTO() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public DossierVersionPapierEnum getEtatDossierVersionPapier() {
		return etatDossierVersionPapier;
	}

	public void setEtatDossierVersionPapier(final DossierVersionPapierEnum etatDossierVersionPapier) {
		this.etatDossierVersionPapier = etatDossierVersionPapier;
	}

	public DossierAffectationEnum getEtatDossierAffectation() {
		return etatDossierAffectation;
	}

	public void setEtatDossierAffectation(final DossierAffectationEnum etatDossierAffectation) {
		this.etatDossierAffectation = etatDossierAffectation;
	}
}
