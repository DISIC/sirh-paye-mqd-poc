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
import com.sirh.mqd.commons.utils.DateUtils;
import com.sirh.mqd.reporting.core.api.ICalendrierGestionService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.CALENDRIER_GESTION_SERVICE)
public class CalendrierGestionService implements ICalendrierGestionService {

	@Autowired
	@Qualifier(PersistenceConstantes.CALENDRIER_GESTION_BC)
	private CalendrierGestionBC calendrierGestionBC;

	@Override
	public List<EventCalendrierDTO> listerEventsAvecBornesTemporelles(final InteractionSirhEnum referentiel,
			final String service) {
		final Date now = DateUtils.getCalendarInstance().getTime();
		final Date dateDebut = DateUtils.addMonthsWithBounds(now, -1);
		final Date dateFin = DateUtils.addMonthsWithBounds(now, 6);
		return calendrierGestionBC.listerEvents(referentiel, service, dateDebut, dateFin);
	}

	@Override
	public void ajouterEvent(final EventCalendrierDTO eventCalendrierGestion) {
		calendrierGestionBC.modifierCreerEvent(eventCalendrierGestion);
	}
}
