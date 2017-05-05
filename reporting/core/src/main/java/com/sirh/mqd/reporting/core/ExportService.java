package com.sirh.mqd.reporting.core;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sirh.mqd.reporting.core.constantes.CoreConstantes;
import com.sirh.mqd.reporting.persistence.bc.ElasticsearchBC;
import com.sirh.mqd.reporting.persistence.constantes.PersistenceReportingConstantes;
import com.sirh.mqd.reporting.persistence.entities.WSGenericMetierEntity;

/**
 * Classe de service pour l'export des journaux d'échanges
 *
 *
 */
@Service(CoreConstantes.EXPORT_SERVICE)
public class ExportService {

	@Autowired
	@Qualifier(PersistenceReportingConstantes.ELASTICSEARCH_BC)
	private ElasticsearchBC elasticsearchBC;

	public String getTextFile(final Date from, final Date to) {
		Page<WSGenericMetierEntity> result = elasticsearchBC.findEchange(from, to);

		Iterator<WSGenericMetierEntity> echange = result.iterator();

		StringBuffer reponse = new StringBuffer();

		while (echange.hasNext()) {
			WSGenericMetierEntity WSGenericMetierEntity = echange.next();
			reponse.append(WSGenericMetierEntity.getMessage() + "\n");

		}

		return reponse.toString();
	}
}
