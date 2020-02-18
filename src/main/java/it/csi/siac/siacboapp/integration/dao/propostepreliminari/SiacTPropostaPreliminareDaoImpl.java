/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.dao.propostepreliminari;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.dao.base.BoBaseDaoImpl;
import it.csi.siac.siacboapp.integration.entity.SiacTBilElem;
import it.csi.siac.siacboapp.integration.entity.SiacTEnteProprietario;
import it.csi.siac.siacboapp.integration.entity.SiacTPropostaPreliminare;

@Component
public class SiacTPropostaPreliminareDaoImpl extends BoBaseDaoImpl<SiacTPropostaPreliminare> implements SiacTPropostaPreliminareDao {
	@SuppressWarnings("unchecked") 
	@Override
	public List<SiacTBilElem> readElencoCapitoliPerPropostePreliminari(SiacTEnteProprietario ente,
			List<Integer> idStruttureAmministrativoContabili, List<Integer> idProgrammi, Map<String, Integer> idClassificatoriGenerici) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("ente", ente);
		params.put("idSac", idStruttureAmministrativoContabili);
		params.put("idProgrammi", idProgrammi);
		
		String[] sqlClassificatoriGenerici = getSqlClassificatoriGenerici(idClassificatoriGenerici);
	
		String sql = "SELECT be FROM SiacTBilElem be, IN (be.classificatori) sac "
				+ " LEFT OUTER JOIN be.classificatori programma "
				+ sqlClassificatoriGenerici[0]
				+ " WHERE be.enteProprietario=:ente " 
				+ sqlClassificatoriGenerici[1]
				+ " AND be.tipo.codice IN ('CAP-UP') "
				+ " AND sac.uid IN (-1, :idSac) "
				+ " AND sac.enteProprietario=be.enteProprietario "  
				+ " AND sac.dataCancellazione IS NULL "
				+ " AND (sac.dataFineValidita IS NULL OR sac.dataFineValidita>CURRENT_TIMESTAMP) "
				
				+ " AND (-1 IN (:idProgrammi) OR programma.uid IN (-1, :idProgrammi))"
				+ " AND programma.tipo.codice='PROGRAMMA' "
				+ " AND programma.enteProprietario=be.enteProprietario "  
				+ " AND programma.dataCancellazione IS NULL "
				+ " AND (programma.dataFineValidita IS NULL OR programma.dataFineValidita>CURRENT_TIMESTAMP) "
				
				+ " AND be.dataCancellazione IS NULL "
				+ " AND (be.dataFineValidita IS NULL OR be.dataFineValidita>CURRENT_TIMESTAMP) "
				;        
		
		return createQuery(sql, params).getResultList();
	}
	
	
	// problema timeout: https://www.postgresql.org/message-id/BLU0-SMTP166A927FE28F4564E83E0DDCF890@phx.gbl
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SiacTBilElem> readElencoCapitoliPerPropostePreliminariNonSelezionati(List<SiacTBilElem> elencoCapitoli) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("elencoCapitoli", elencoCapitoli);
		
	
		String sql = "SELECT be FROM SiacTBilElem be "
				+ " WHERE be IN (:elencoCapitoli) " 
				+ " AND NOT EXISTS (SELECT 1 FROM SiacTPropostaPreliminare pp WHERE pp.capitolo=be)"
				;        
		
		return createQuery(sql, params).getResultList();
	}
	
	
	private String[] getSqlClassificatoriGenerici(Map<String, Integer> idClassificatoriGenerici) {
		StringBuilder joinSql = new StringBuilder("");
		StringBuilder whereSql = new StringBuilder("");
		
		int i = 0;
		
		for (Entry<String, Integer> e : idClassificatoriGenerici.entrySet())
			if (e.getValue() != null) {
				i++;
			
				joinSql.append(String.format(" , IN (be.classificatori) cg%d ", i));
				
				whereSql.append(String.format( " AND cg%d.enteProprietario=be.enteProprietario "  
											+ " AND cg%d.dataCancellazione IS NULL "
											+ " AND (cg%d.dataFineValidita IS NULL OR cg%d.dataFineValidita>CURRENT_TIMESTAMP) "
											+ " AND cg%d.uid=%d "
											+ " AND cg%s.tipo.codice='%s' "
											, i, i, i, i, i, e.getValue(), i, e.getKey()));
			}
		
		return new String[]{joinSql.toString(), whereSql.toString()};
	}

}
