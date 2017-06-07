package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.reporting.core.api.ICommentaireService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.webapp.constantes.ViewConstantes;
import com.sirh.mqd.reporting.webapp.factory.CommentaireModelFactory;
import com.sirh.mqd.reporting.webapp.model.CommentaireModel;
import com.sirh.mqd.reporting.webapp.model.DossierModel;
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
	@Qualifier(CoreConstantes.COMMENTAIRE_SERVICE)
	private ICommentaireService commentaireService;

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

	public void ajouterCommentaire(final ActionEvent actionEvent) {
		final String username = getCurrentUsername();
		final DossierModel dossier = getCurrentDossier();
		if (dossier != null) {
			final String matricule = dossier.getRenoiRHMatricule();
			final String payLot = dossier.getPayLot();
			final CommentaireDTO commentaireDTO = CommentaireModelFactory.createCommentaireDTO(commentaire, username, payLot, matricule);
			commentaireService.ajouterCommentaire(commentaireDTO);
		} else {
			// TODO Remonter un message d'erreur
		}

	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}

}
