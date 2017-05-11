package com.sirh.mqd.supplier.core.sirh;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.sirh.mqd.supplier.core.constantes.CoreConstantes;

/**
 * Classe de service pour la gestion des entrants du SIRH MSO
 *
 * @author alexandre
 */
@Service(CoreConstantes.SIRH_MSO_SERVICE)
public class SirhMsoService {

	// @Autowired
	// @Qualifier(PersistenceSupplierConstantes.ELASTICSEARCH_BC)
	// private ElasticsearchBC elasticsearchBC;

	public void dropSIRHDatasource(final InputStream multipleFiles) {

	}

}
