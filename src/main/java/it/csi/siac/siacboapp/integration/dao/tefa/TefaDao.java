/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.tefa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class TefaDao extends NamedParameterJdbcDaoSupport {
	@Autowired
	public TefaDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	public List<Map<String, Object>> estraiVersamenti(int idEnte, Integer idFile, Integer anno) {

		Map<String, Object> par = new HashMap<String, Object>();

		par.put("idEnte", idEnte);
		par.put("idFile", idFile);
		par.put("anno", anno);

		try {
			return getNamedParameterJdbcTemplate()
					.queryForList("select * from fnc_tefa_trib_versamenti_estrai(:idEnte, :idFile, :anno)", par);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Map<String, Object>> estraiComuneAnnoRif(int idEnte, Integer idFile, Integer anno) {

		Map<String, Object> par = new HashMap<String, Object>();

		par.put("idEnte", idEnte);
		par.put("idFile", idFile);
		par.put("anno", anno);

		try {
			return getNamedParameterJdbcTemplate()
					.queryForList("select * from fnc_tefa_trib_comune_anno_rif_estrai(:idEnte, :idFile, :anno)", par);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
