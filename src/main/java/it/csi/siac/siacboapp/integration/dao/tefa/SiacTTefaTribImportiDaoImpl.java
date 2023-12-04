/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.tefa;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.repository.SiacTTefaTribImporti;

@Component
public  class SiacTTefaTribImportiDaoImpl extends BoBaseDaoImpl<SiacTTefaTribImporti> implements SiacTTefaTribImportiDao {

	@Override
	public String uno(int idEnte, String loginOperazione, String codiceTipoOrdinativo, String elencoIdOrdinativi) {
		// TODO Auto-generated method stub
		return null;
	}

//
//	@Override
//	public String uno(int idEnte, String loginOperazione, String codiceTipoOrdinativo,
//			String elencoIdOrdinativi) {
////		fnc_tefa_trib_versamenti_estrai(p_ente_proprietario_id integer,p_tefa_trib_upload_id integer, p_tefa_trib_comune_anno integer);
//		Query q = entityManager.createNativeQuery("SELECT fnc_tefa_trib_versamenti_estrai(?, ?, ?)");
//
//		q.setParameter(1, 2).setParameter(2, 9999).setParameter(2, 2021);
//		entityManager.createNativeQuery("SELECT tmp_tefa(:aa, :bb, :cc)").setParameter("aa", new Integer(2)).setParameter("bb", new Integer(9999)).setParameter("cc", new Integer(2021)).getResultList();
//
//		return (String) q.getSingleResult();
//	}

	
}


/* fnc_tefa_trib_versamenti_estrai

fnc_tefa_trib_comune_anno_rif_estrai */