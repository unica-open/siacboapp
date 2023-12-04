/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.movimentigestione;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTMovgest;

@Component
public  class SiacTMovgestDaoImpl extends BoBaseDaoImpl<SiacTMovgest> implements SiacTMovgestDao {

	@Override
	public void insertSiacRMovgestTsAttr(int idEnte, Integer idMovgest, String codiceAttributo, String valoreBoolean, String valoreTesto, String loginOperazione) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idEnte", idEnte);
		params.put("idMovgest", idMovgest);
		params.put("codiceAttributo", codiceAttributo);
		params.put("valoreBoolean", valoreBoolean);
		params.put("valoreTesto", valoreTesto);
		params.put("loginOperazione", loginOperazione);
		
		executeNativeQuery(
				"INSERT INTO siac_r_movgest_ts_attr "
				+ " (movgest_ts_id, attr_id, \"boolean\", testo, ente_proprietario_id, validita_inizio, login_operazione) "
				+ " VALUES "
				+ " ((SELECT tmt.movgest_ts_id FROM siac_t_movgest_ts tmt, siac_d_movgest_ts_tipo dmtt "
				+ "    WHERE tmt.movgest_id=:idMovgest "
				+ "    AND tmt.movgest_ts_tipo_id=dmtt.movgest_ts_tipo_id "
				+ "    AND dmtt.movgest_ts_tipo_code='T'), "
				+ "  (SELECT ta.attr_id FROM siac_t_attr ta " 
				+ "	   WHERE ta.attr_code=:codiceAttributo "
				+ "    AND ta.ente_proprietario_id=:idEnte), "
				+ "  :valoreBoolean, "
				+ "  :valoreTesto, "
				+ "  :idEnte, "
				+ "  CURRENT_TIMESTAMP, "
				+ "  :loginOperazione "
				+ " )", params);
	}

	@Override
	public void insertSiacRMovgestTsSog(int idEnte, Integer idMovgest, Integer idSoggetto, String loginOperazione) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idEnte", idEnte);
		params.put("idMovgest", idMovgest);
		params.put("idSoggetto", idSoggetto);
		params.put("loginOperazione", loginOperazione);
		
		executeNativeQuery(
				"INSERT INTO siac_r_movgest_ts_sog "
				+ " (movgest_ts_id, soggetto_id, ente_proprietario_id, validita_inizio, login_operazione) "
				+ " VALUES "
				+ " ((SELECT tmt.movgest_ts_id FROM siac_t_movgest_ts tmt, siac_d_movgest_ts_tipo dmtt "
				+ "    WHERE tmt.movgest_id=:idMovgest "
				+ "    AND tmt.movgest_ts_tipo_id=dmtt.movgest_ts_tipo_id "
				+ "    AND dmtt.movgest_ts_tipo_code='T'), "
				+ "  :idSoggetto, "
				+ "  :idEnte, "
				+ "  CURRENT_TIMESTAMP, "
				+ "  :loginOperazione "
				+ " )", params);
	}

	@Override
	public void insertSiacRMovgestTsSogClasse(int idEnte, Integer idMovgest, Integer idClasseSoggetto, String loginOperazione) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idEnte", idEnte);
		params.put("idMovgest", idMovgest);
		params.put("idClasseSoggetto", idClasseSoggetto);
		params.put("loginOperazione", loginOperazione);
		
		executeNativeQuery(
				"INSERT INTO siac_r_movgest_ts_sogclasse "
				+ " (movgest_ts_id, soggetto_classe_id, ente_proprietario_id, validita_inizio, login_operazione) "
				+ " VALUES "
				+ " ((SELECT tmt.movgest_ts_id FROM siac_t_movgest_ts tmt, siac_d_movgest_ts_tipo dmtt "
				+ "    WHERE tmt.movgest_id=:idMovgest "
				+ "    AND tmt.movgest_ts_tipo_id=dmtt.movgest_ts_tipo_id "
				+ "    AND dmtt.movgest_ts_tipo_code='T'), "
				+ "  :idClasseSoggetto, "
				+ "  :idEnte, "
				+ "  CURRENT_TIMESTAMP, "
				+ "  :loginOperazione "
				+ " )", params);
	}

	@Override
	public void insertSiacRMovgestClass(int idEnte, Integer idMovgestTs, Integer classifId, String loginOperazione) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("idEnte", idEnte);
		params.put("idMovgestTs", idMovgestTs);
		params.put("classifId", classifId);
		params.put("loginOperazione", loginOperazione);
		
		executeNativeQuery(
				"INSERT INTO siac_r_movgest_class "
				+ " (movgest_ts_id, classif_id, ente_proprietario_id, validita_inizio, login_operazione) "
				+ " VALUES "
				+ " (:idMovgestTs, "
				+ "  :classifId, "
				+ "  :idEnte, "
				+ "  CURRENT_TIMESTAMP, "
				+ "  :loginOperazione "
				+ " )", params);
	}
}
