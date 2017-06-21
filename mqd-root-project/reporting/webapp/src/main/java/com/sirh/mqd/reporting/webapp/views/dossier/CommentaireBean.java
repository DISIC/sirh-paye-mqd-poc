package com.sirh.mqd.reporting.webapp.views.dossier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.traces.IFacadeLogs;
import com.sirh.mqd.commons.traces.constantes.ConstantesTraces;
import com.sirh.mqd.commons.traces.enums.IHMPageNameEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserActionEnum;
import com.sirh.mqd.commons.traces.enums.IHMUserResultEnum;
import com.sirh.mqd.commons.utils.DateUtils;
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

	@Inject
	@Qualifier(ConstantesTraces.FACADE_LOGS)
	private IFacadeLogs logger;

	/**
	 * Commentaire sélectionné dans le tableau.
	 */
	private CommentaireModel selectedCommentaire;

	/**
	 * Commentaire saisi par l'utilisateur
	 */
	private String commentaire;

	/**
	 * Liste des commentaires du dossier.
	 */
	private List<CommentaireModel> commentaires;

	public void setup() {
		// Initialization

		// Supplier
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null && !facesContext.isPostback()) {
			this.commentaires = new ArrayList<CommentaireModel>();
		}
	}

	public void alimenterCommentaires(final DossierModel selectedDossier) {
		this.commentaire = StringUtils.EMPTY;
		listerCommentaires(selectedDossier.getRenoiRHMatricule(), selectedDossier.getPayLot());
	}

	public void ajouterCommentaire(final ActionEvent actionEvent) {
		final String userLogin = this.getCurrentUsername();
		final String userPrenom = this.getCurrentUserFirstname();
		final String userNom = this.getCurrentUserLastname();
		final DossierModel dossier = getCurrentDossier();
		if (dossier != null) {
			final String matricule = dossier.getRenoiRHMatricule();
			final String payLot = dossier.getPayLot();
			final CommentaireDTO commentaireDTO = CommentaireModelFactory.createCommentaireDTO(this.commentaire,
					userLogin, userPrenom, userNom, payLot, matricule);
			this.commentaireService.ajouterCommentaire(commentaireDTO);
			this.logger.logAction(Level.INFO, computeLogActionDTO(IHMUserActionEnum.CREATION, IHMUserResultEnum.SUCCESS,
					IHMPageNameEnum.COMMENTAIRE, DateUtils.formateDateJJMMAAAAhhmmss(commentaireDTO.getDateCreation()),
					commentaireDTO, null));

			alimenterCommentaires(dossier);
		} else {
			this.logger.logAction(Level.ERROR,
					computeLogActionDTO(IHMUserActionEnum.CREATION, IHMUserResultEnum.ERROR,
							IHMPageNameEnum.COMMENTAIRE,
							DateUtils.formateDateJJMMAAAAhhmmss(DateUtils.getCalendarInstance().getTime()),
							this.commentaire, null));
			this.jsfUtils.addMessageByCode(FacesMessage.SEVERITY_ERROR,
					"view.dossiers.commentaires.erreur.no.dossier.selected");
		}
	}

	private void listerCommentaires(final String matricule, final String payLot) {
		final List<CommentaireDTO> commentairesDTO = this.commentaireService.listerCommentaires(matricule, payLot);
		this.commentaires = new ArrayList<CommentaireModel>();
		this.commentaires.addAll(commentairesDTO.stream()
				.map(commentaireDTO -> CommentaireModelFactory.createCommentaireModel(commentaireDTO))
				.collect(Collectors.toList()));
		this.commentaires.sort((c1, c2) -> {
			return c2.getDateCreation().compareTo(c1.getDateCreation());
		});
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(final String commentaire) {
		this.commentaire = commentaire;
	}
}
