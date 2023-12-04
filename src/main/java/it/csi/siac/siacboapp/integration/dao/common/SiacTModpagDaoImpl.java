/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTModpag;

@Component
public class SiacTModpagDaoImpl extends BoBaseDaoImpl<SiacTModpag> implements SiacTModpagDao {

	@Override
	public List<Map<String, Object>> findModalitaPagamentoSoggetto(int idEnte, String codiceSoggetto)
	{
		// FIXME 
		String[] columns = new String[]{
				 "stm.modpag_id AS uid",
				 "srmo.ordine AS codice",
				 "COALESCE(sdrt.relaz_tipo_code, sdat.accredito_tipo_code) AS codiceTipoAccredito",
				 "COALESCE(UPPER(sdrt.relaz_tipo_desc), sdat.accredito_tipo_desc) AS descrizioneTipoAccredito",
				 "stm.iban AS iban",
				 "stm.bic AS bic",
				 "stm.note AS note",
				 "stm.contocorrente AS contoCorrente"
		};
		
		List<String> cols = new ArrayList<String>();

		for (String s : columns) {
			String[] tmp = StringUtils.splitByWholeSeparator(s, " AS ");
			cols.add(tmp[1]);
		}
		
		String sql = 
				"SELECT " + StringUtils.join(columns, ", ") + 
				" FROM siac_t_modpag stm, siac_d_ambito sda, siac_t_soggetto sts, " +
				" siac_d_accredito_tipo sdat, siac_r_modpag_stato srms, siac_d_modpag_stato sdms, " + 
				" siac_r_modpag_ordine srmo " +
				" LEFT OUTER JOIN siac_r_soggrel_modpag srsm ON srmo.soggrelmpag_id=srsm.soggrelmpag_id " +
				" LEFT OUTER JOIN siac_r_soggetto_relaz srsr ON srsm.soggetto_relaz_id=srsr.soggetto_relaz_id " +
				" LEFT OUTER JOIN siac_d_relaz_tipo sdrt ON sdrt.relaz_tipo_id=srsr.relaz_tipo_id " +
				" WHERE sts.soggetto_code = :codiceSoggetto " + 
				" AND sts.ambito_id = sda.ambito_id  " + 
				" AND sda.ambito_code = 'AMBITO_FIN' " + 
				" AND sda.ente_proprietario_id = :idEnte " + 
				" AND sdat.accredito_tipo_id = stm.accredito_tipo_id " +
				" AND srmo.soggetto_id = sts.soggetto_id " +
				" AND srms.modpag_id = stm.modpag_id " + 
				" AND sdms.modpag_stato_id = srms.modpag_stato_id " + 
				" AND sdms.ente_proprietario_id = :idEnte " + 
				" AND sdms.modpag_stato_code = 'VALIDO' " + 
				" AND (stm.modpag_id=srmo.modpag_id OR stm.modpag_id=srsm.modpag_id) " + 
				" AND stm.data_cancellazione IS NULL  " + 
				" AND stm.validita_inizio < CURRENT_TIMESTAMP  " + 
				" AND (stm.validita_fine IS NULL OR stm.validita_fine > CURRENT_TIMESTAMP)  " + 
				" ORDER BY srmo.ordine";
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idEnte", idEnte);
		params.put("codiceSoggetto", codiceSoggetto);
		
		Query query = super.createNativeQuery(sql, params);
	
		
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for (Object[] ol : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols.size(); i++) {
				map.put(cols.get(i), ol[i]);
			}
			mapList.add(map);
		}
		
		return mapList;
	}

}
