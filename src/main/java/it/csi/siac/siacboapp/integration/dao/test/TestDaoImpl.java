/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.test;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;

@Component
@Transactional
public class TestDaoImpl extends BoBaseDaoImpl<SiacTEnteProprietario> implements TestDao {

	@Override
	public void test() {
//		javax.persistence.Query query;
//		
//		query = entityManager.createQuery(
//				
//				"SELECT pc FROM SiacTProvCassa pc "
//			+ " WHERE pc.enteProprietario.uid=:enteProprietarioId "
//			+ " AND (:annoDa IS NULL OR pc.anno>=CAST(CAST(:annoDa AS string) AS integer)) "
//			+ " AND (:annoA IS NULL OR pc.anno<=CAST(CAST(:annoA AS string) AS integer)) "
//			+ " AND (:numeroDa IS NULL OR pc.numero>=CAST(CAST(:numeroDa AS string) AS integer)) "
//			+ " AND (:numeroA IS NULL OR pc.numero<=CAST(CAST(:numeroA AS string) AS integer)) "
//			+ " AND pc.tipo.codice=:codiceTipo "
//			+ " AND (:dataEmissioneDa IS NULL AND :dataEmissioneA IS NULL AND pc.dataEmissione IS NULL "
//			+ "      OR ( "
//			+ "     		(:dataEmissioneDa IS NULL OR DATE_TRUNC('day', pc.dataEmissione)>=CAST(CAST(:dataEmissioneDa AS string) AS date)) "
//			+ "          	AND (:dataEmissioneA IS NULL OR DATE_TRUNC('day', pc.dataEmissione)<=CAST(CAST(:dataEmissioneA AS string) AS date))"
//			+ "         ) "
//			+ " )"
//			+ " AND (:importoDa IS NULL OR pc.importo>=CAST(CAST(:importoDa AS string) AS big_decimal)) "
//			+ " AND (:importoA IS NULL OR pc.importo<=CAST(CAST(:importoDa AS string) AS big_decimal)) "
//			+ " ORDER BY pc.anno, pc.numero"
//		);
//		
//		
//		query.setParameter("enteProprietarioId", 2);
//		query.setParameter("annoDa", null);
//		query.setParameter("annoA", null);
//		query.setParameter("codiceTipo", "S");
//		query.setParameter("numeroDa", null);
//		query.setParameter("numeroA", null);
//		query.setParameter("dataEmissioneDa", null);
//		query.setParameter("dataEmissioneA", null);
//		query.setParameter("importoDa", null);
//		query.setParameter("importoA", null);
//		
//		query.getResultList();
		
		System.out.println();
	}

}
