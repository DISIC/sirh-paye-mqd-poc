package com.sirh.mqd.reporting.core.calendriergestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sirh.mqd.commons.exchanges.dto.eventcalendriergestion.EventCalendrierGestionDTO;
import com.sirh.mqd.commons.storage.bc.EventCalendrierGestionBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.reporting.core.api.ICalendrierGestionService;
import com.sirh.mqd.reporting.core.constantes.CoreConstantes;

@Service(CoreConstantes.CALENDRIER_GESTION_SERVICE)
public class CalendrierGestionService implements ICalendrierGestionService {

	@Autowired
	@Qualifier(PersistenceConstantes.EVENT_CALENDRIER_GESTION_BC)
	private EventCalendrierGestionBC eventCalendrierGestionBC;

	@Override
	public List<EventCalendrierGestionDTO> listerEventCalendrierGestion() {
		return eventCalendrierGestionBC.listerEventCalendrierGestion();
	}

	@Override
	public void ajouterEventCalendrierGestion(final EventCalendrierGestionDTO eventCalendrierGestion) {
		eventCalendrierGestionBC.modifierCreerEventCalendrierGestion(eventCalendrierGestion);
	}
}
