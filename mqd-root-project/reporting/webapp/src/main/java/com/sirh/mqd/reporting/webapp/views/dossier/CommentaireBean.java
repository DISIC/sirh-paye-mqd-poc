package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.reporting.core.api.IDossierService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.model.CommentaireModel;
import com.sirh.mqd.reporting.webapp.views.GenericBean;

/**
 * La vue des commentaires d'un dossier
 *
 * @author khalil
 */
@Named(ViewConstantes.COMMENTAIRE_BEAN)
@SessionScoped
public class CommentaireBean extends GenericBean {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 4048923272053274622L;

	@Inject
	@Qualifier(CoreConstantes.DOSSIER_SERVICE)
	private IDossierService dossierService;

	/**
	 * Commentaire sélectionné dans le tableau.
	 */
	private CommentaireModel selectedCommentaire;

	private String commentaire;

	/**
	 * Liste des commentaires du dossier.
	 */
	private List<CommentaireModel> commentaires;

	public void setup() {
		// Initialization
		this.commentaires = new ArrayList<CommentaireModel>();
		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.commentaires = new ArrayList<CommentaireModel>();
		}
	}

	public void ajouterCommentaire() {
	}

	public CommentaireModel getSelectedCommentaire() {
		return selectedCommentaire;
	}

	public void setSelectedCommentaire(final CommentaireModel selectedCommentaire) {
		this.selectedCommentaire = selectedCommentaire;
	}

	public List<CommentaireModel> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(final List<CommentaireModel> commentaires) {
		this.commentaires = commentaires;
	}

	public void enterNewCommentaire(final CommentaireModel commentaire) {
		this.commentaires.add(commentaire);
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}

}
