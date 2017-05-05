package com.thalesgroup.stif.commons.tampon.dao.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sirh.mqd.commons.exchanges.enums.TypeObjetReflex;
import com.sirh.mqd.commons.storage.bc.CodifLigneTamponBC;
import com.sirh.mqd.commons.storage.constantes.PersistenceConstantes;
import com.sirh.mqd.commons.storage.dao.impl.ParticipantTamponDAO;
import com.sirh.mqd.commons.storage.dao.impl.ReflexTamponDAO;
import com.thalesgroup.stif.commons.echange.dto.reflex.ReflexTamponDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistence-datasource.xml")
@Ignore
public class ReflexTamponDAOTest {

	@Autowired
	@Qualifier(PersistenceConstantes.REFLEX_TAMPON_DAO)
	private ReflexTamponDAO reflexTamponDAO;

	@Autowired
	@Qualifier(PersistenceConstantes.PARTICIPANT_TAMPON_DAO)
	private ParticipantTamponDAO producerTamponDAO;

	@Autowired
	@Qualifier(PersistenceConstantes.CODIFLIGNE_BC)
	private CodifLigneTamponBC codifLigneTamponBC;

	@Test
	public void testAdd() throws Exception {
		for (int i = 0; i < 20; i++) {
			ReflexTamponDTO reflexGDL = new ReflexTamponDTO();
			reflexGDL.setTypeObjetReflex(TypeObjetReflex.GDL);
			reflexGDL.setIdRefA("identifiantGDL");
			reflexGDL.setIdVersion("versionGDL");
			reflexGDL.setNom("nomGDL");
			reflexTamponDAO.add(reflexGDL, Long.valueOf(86460));

			ReflexTamponDTO reflexLDA = new ReflexTamponDTO();
			reflexLDA.setTypeObjetReflex(TypeObjetReflex.LDA);
			reflexLDA.setIdRefA("identifiantLDA");
			reflexLDA.setIdVersion("versionLDA");
			reflexLDA.setNom("nomLDA");
			reflexLDA.setIdTypeArret("idTypeArretLDA");
			reflexLDA.setLibelleTypeArret("libelleTypeArretLDA");
			reflexTamponDAO.add(reflexLDA, Long.valueOf(86460));

			ReflexTamponDTO reflexZDL = new ReflexTamponDTO();
			reflexZDL.setTypeObjetReflex(TypeObjetReflex.ZDL);
			reflexZDL.setIdRefA("identifiantZDL");
			reflexZDL.setIdVersion("versionZDL");
			reflexZDL.setNom("nomZDL");
			reflexZDL.setIdTypeArret("idTypeArretZDL");
			reflexZDL.setLibelleTypeArret("libelleTypeArretZDL");
			reflexTamponDAO.add(reflexZDL, Long.valueOf(86460));

			ReflexTamponDTO reflexZDE = new ReflexTamponDTO();
			reflexZDE.setTypeObjetReflex(TypeObjetReflex.ZDE);
			reflexZDE.setIdRefA("identifiantZDE" + i);
			reflexZDE.setIdVersion("versionZDE");
			reflexZDE.setNom("nomZDE");
			reflexZDE.setIdTypeArret("idTypeArretZDE");
			reflexZDE.setLibelleTypeArret("libelleTypeArretZDE");
			reflexTamponDAO.add(reflexZDE, Long.valueOf(86460));
		}
	}

	@Test
	public void testAdd2() throws Exception {
		ReflexTamponDTO reflexGDL = new ReflexTamponDTO();
		reflexGDL.setTypeObjetReflex(TypeObjetReflex.GDL);
		reflexGDL.setIdRefA("identifiantGDL");
		reflexGDL.setIdVersion("versionGDL");
		reflexGDL.setNom("nomGDL");
		reflexTamponDAO.add(reflexGDL, Long.valueOf(86460));

		ReflexTamponDTO reflexLDA = new ReflexTamponDTO();
		reflexLDA.setTypeObjetReflex(TypeObjetReflex.LDA);
		reflexLDA.setIdRefA("identifiantLDA");
		reflexLDA.setIdVersion("versionLDA");
		reflexLDA.setNom("nomLDA");
		reflexLDA.setIdTypeArret("idTypeArretLDA");
		reflexLDA.setLibelleTypeArret("libelleTypeArretLDA");
		reflexTamponDAO.add(reflexLDA, Long.valueOf(86460));

		ReflexTamponDTO reflexZDL = new ReflexTamponDTO();
		reflexZDL.setTypeObjetReflex(TypeObjetReflex.ZDL);
		reflexZDL.setIdRefA("identifiantZDL");
		reflexZDL.setIdVersion("versionZDL");
		reflexZDL.setNom("nomZDL");
		reflexZDL.setIdTypeArret("idTypeArretZDL");
		reflexZDL.setLibelleTypeArret("libelleTypeArretZDL");
		reflexTamponDAO.add(reflexZDL, Long.valueOf(86460));

		ReflexTamponDTO reflexZDE = new ReflexTamponDTO();
		reflexZDE.setTypeObjetReflex(TypeObjetReflex.ZDE);
		reflexZDE.setIdRefA("identifiantZDE");
		reflexZDE.setIdVersion("versionZDE");
		reflexZDE.setNom("nomZDE");		
		reflexZDE.setIdTypeArret("idTypeArretZDE");
		reflexZDE.setLibelleTypeArret("libelleTypeArretZDE");
		reflexTamponDAO.add(reflexZDE, Long.valueOf(86460));
	}
}
