package com.sirh.mqd.reporting.core.calendrier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.calendrier.EventCalendrierDTO;
import com.sirh.mqd.commons.exchanges.enums.InteractionSirhEnum;
import com.sirh.mqd.commons.storage.bc.CalendrierGestionBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.ICalendrierGestionService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.CALENDRIER_GESTION_SERVICE)
public class CalendrierGestionService implements ICalendrierGestionService {

	@Autowired
	@Qualifier(PersistenceConstantes.CALENDRIER_GESTION_BC)
	private CalendrierGestionBC calendrierGestionBC;

	@Override
	public List<EventCalendrierDTO> listerEventsAvecBornesTemporelles(final InteractionSirhEnum referentiel,
			final String service, final Date dateDebut, final Date dateFin, final String type, final String couleur,
			final String corps) {
		return calendrierGestionBC.listerEvents(referentiel, service, dateDebut, dateFin, type, couleur, corps);
	}

	@Override
	public void ajouterEvent(final EventCalendrierDTO eventCalendrierGestion) {
		calendrierGestionBC.modifierCreerEvent(eventCalendrierGestion);
	}

	@Override
	public List<String> listerCouleursEvents() {
		return calendrierGestionBC.listerCouleursEvents();
	}

	@Override
	public List<String> listerCorpsEvents() {
		return calendrierGestionBC.listerCorpsEvents();
	}

	@Override
	public List<String> listerTypesEvents() {
		return calendrierGestionBC.listerTypesEvents();
	}
}
