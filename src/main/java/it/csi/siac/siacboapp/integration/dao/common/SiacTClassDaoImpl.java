/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.common;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTClass;

@Component
public class SiacTClassDaoImpl extends BoBaseDaoImpl<SiacTClass> implements SiacTClassDao {

	@Override
	public List<SiacTClass> getElencoClassificatoriByTipoFamiglia(int enteId, String codiceTipoFamiglia, Integer anno)
	{
		Query query = entityManager
				.createQuery("FROM SiacTClass c WHERE c.enteProprietario.uid=:enteId  AND c.dataCancellazione IS NULL"
				+ " AND c.famigliaClassificatori.tipoFamigliaClassificatori.codice=:codiceTipoFamiglia "
				+ " AND c.famigliaClassificatori.dataFineValidita IS NULL "
				+ " AND c.famigliaClassificatori.dataCancellazione IS NULL "
				
				+ getSiacTClassDataValiditaSql("c", "anno")
				
				+ " ORDER BY c.codice ");
		
		query.setParameter("enteId", enteId);
		query.setParameter("codiceTipoFamiglia", codiceTipoFamiglia);
		query.setParameter("anno", anno);
		
		@SuppressWarnings("unchecked")
		List<SiacTClass> list = query.getResultList();
		
		return list;
	}

}
