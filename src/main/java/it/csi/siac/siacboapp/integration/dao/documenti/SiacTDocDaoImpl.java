/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.documenti;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTDoc;

@Component
public  class SiacTDocDaoImpl extends BoBaseDaoImpl<SiacTDoc> implements SiacTDocDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTDoc> ricercaDocumenti(int idEnte, String codiceTipologia, String codiceTipo, Integer anno, String numero, Date dataEmissione, String codiceSoggetto) {
			
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("idEnte", idEnte);
						
			StringBuilder sql = new StringBuilder("SELECT DISTINCT d FROM SiacTDoc d ")
				.append(" WHERE d.enteProprietario.uid=:idEnte ")
				.append(" AND EXISTS (")
				.append("    SELECT 1 FROM SiacRDocStato rdst ")
				.append("       WHERE rdst.stato.codice <> 'A'")
				.append("       AND rdst.documento=d ")
				.append("       AND rdst.enteProprietario.uid=:idEnte ")
				.append(getDateValiditaCancellazioneClauses("rdst"))
				.append(" ) ");
			
			if (StringUtils.isNotEmpty(codiceTipologia)) {
				sql.append(" AND d.tipo.tipoFamigliaDocumenti.codice=:codiceTipologia AND d.tipo.tipoFamigliaDocumenti.enteProprietario.uid=:idEnte ");
				params.put("codiceTipologia", codiceTipologia);
			}

			if (StringUtils.isNotEmpty(codiceTipo)) {
				sql.append(" AND d.tipo.codice=:codiceTipo AND d.tipo.enteProprietario.uid=:idEnte ");
				params.put("codiceTipo", codiceTipo);
			}

			if (anno != null) {
				sql.append(" AND d.anno=:anno ");
				params.put("anno", anno);
			}
			
			if (StringUtils.isNotEmpty(numero)) {
				sql.append(" AND d.numero=:numero ");
				params.put("numero", numero);
			}  
			
			if (dataEmissione != null) {
				sql.append(" AND d.dataEmissione=:dataEmissione ");
				params.put("dataEmissione", dataEmissione);
			}
			
			if (StringUtils.isNotEmpty(codiceSoggetto)) {
				sql.append(" AND EXISTS (")
				.append(" 		SELECT 1 FROM SiacRDocSog rds ")
				.append(" 			WHERE rds.documento=d ")
				.append("			AND rds.soggetto.codice=:codiceSoggetto")
				.append(getDateValiditaCancellazioneClauses("rds"))
				.append(" ) ");

				params.put("codiceSoggetto", codiceSoggetto);
			}
			
			sql.append(getDateValiditaCancellazioneClauses("d"));
		
		return createQuery(sql.toString(), params).getResultList();
	}
	
}
