/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.limiteimpegnabile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;

@Component
public class SiacTBilElemDaoImpl extends BoBaseDaoImpl<SiacTBilElem> implements SiacTBilElemDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTBilElem> readElencoCapitoliLimiteImpegnabile(Integer idEnte, String annoBilancio,
			String numeroCapitolo, String numeroArticolo, String numeroUeb,
			List<Integer> idStruttureAmministrativoContabili) {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT DISTINCT be ");
		jpql.append(" FROM SiacTBilElem be ");
		jpql.append(" LEFT OUTER JOIN FETCH be.classificatori sac ");
		jpql.append(" WHERE be.enteProprietario.uid = :idEnte ");
		jpql.append(" AND be.tipo.codice = 'CAP-UG' ");
		jpql.append(" AND be.dataCancellazione IS NULL ");
		jpql.append(" AND (be.dataFineValidita IS NULL OR be.dataFineValidita > CURRENT_TIMESTAMP) ");
		jpql.append(" AND be.bilancio.enteProprietario.uid = :idEnte ");
		jpql.append(" AND be.bilancio.periodo.enteProprietario.uid = :idEnte ");
		jpql.append(" AND sac.tipo.codice IN ('CDC', 'CDR') ");
		jpql.append(" AND sac.enteProprietario = be.enteProprietario ");
		jpql.append(" AND sac.dataCancellazione IS NULL ");
		jpql.append(" AND (sac.dataFineValidita IS NULL OR sac.dataFineValidita>CURRENT_TIMESTAMP) ");
		params.put("idEnte", idEnte);
		
		if(StringUtils.isNotBlank(annoBilancio)) {
			jpql.append(" AND be.bilancio.periodo.anno = :annoBilancio ");
			params.put("annoBilancio", annoBilancio);
		}
		if(StringUtils.isNotBlank(numeroCapitolo)) {
			jpql.append(" AND be.codice = :numeroCapitolo ");
			params.put("numeroCapitolo", numeroCapitolo);
		}
		if(StringUtils.isNotBlank(numeroArticolo)) {
			jpql.append(" AND be.codiceArticolo = :numeroArticolo ");
			params.put("numeroArticolo", numeroArticolo);
		}
		if(StringUtils.isNotBlank(numeroUeb)) {
			jpql.append(" AND be.codiceUeb = :numeroUeb ");
			params.put("numeroUeb", numeroUeb);
		}
		if(idStruttureAmministrativoContabili != null && !idStruttureAmministrativoContabili.isEmpty()) {
			jpql.append(" AND (:idSac=-1 OR sac.uid IN (:idSac) OR sac.parent.uid IN (:idSac)) ");
			params.put("idSac", idStruttureAmministrativoContabili);
		}
		jpql.append(" ORDER BY be.codice, be.codiceArticolo, be.codiceUeb ");
		
		return createQuery(jpql.toString(), params).getResultList();
	}

}
