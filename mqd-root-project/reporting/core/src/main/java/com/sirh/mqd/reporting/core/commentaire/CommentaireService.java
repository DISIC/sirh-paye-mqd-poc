package com.sirh.mqd.reporting.core.commentaire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.commentaire.CommentaireDTO;
import com.sirh.mqd.commons.storage.bc.CommentaireBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.ICommentaireService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.COMMENTAIRE_SERVICE)
public class CommentaireService implements ICommentaireService{

	@Autowired
	@Qualifier(PersistenceConstantes.COMMENTAIRE_BC)
	private CommentaireBC commentaireBC;

	@Override
	public List<CommentaireDTO> listerCommentaires(final String renoiRHMatricule, final String payLot) {
		return commentaireBC.listerCommentaires(renoiRHMatricule, payLot);
	}

	@Override
	public void ajouterCommentaire(final CommentaireDTO commentaire) {

	}

}
